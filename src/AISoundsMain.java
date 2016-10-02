import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import jm.music.data.Phrase;

public class AISoundsMain {

	static double version = 0.0;
	
	static Population myPop;
	static MidiConverter midi;
	
	static boolean sec1Rated;
	static boolean sec2Rated;
	static boolean sec3Rated;
	static boolean sec4Rated;

	static int scoreCount = 0;
	static int currentGen = 1;
	static int totalGens = 1;
	static int currentSec = 1;
	static int totalSecs = 4;

	static JButton playGen;
	static JButton prevGen;
	static JButton nextGen;
	static JButton prevSec;
	static JButton nextSec;
	static JButton replaySec;
	static JButton rate;

	static JLabel genLabel;
	static JLabel secLabel;
	static JLabel ratingLabel;
	static JLabel errorLabel;

	static JTextField rating;

	static JPanel content;
	static JFrame window;

	private static class ButtonHandler implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			setErrorText(""); //Clear error messages.
			if (e.getSource() == playGen) {
				//Toggle button text between "Play"/"Pause"
				Phrase[] phrArr = new Phrase[4];
				for (int i = 0; i<myPop.size(); i++) {
					phrArr[i] = myPop.individuals[i].genes[0];
				}
				midi.music_export(phrArr, "score" + scoreCount + ".mid");
				midi.music_play("score" + scoreCount + ".mid");
			} else if (e.getSource() == prevGen) { //Old Generation.
				currentGen--;
				if (currentGen <= 1) { //Lower Limit.
					currentGen = 1;
					prevGen.setEnabled(false);
				}
				disableRating();
				updateGenCounter(currentGen);
				resetSecButtons();
			} else if (e.getSource() == nextGen) {
				prevGen.setEnabled(true);
				currentGen++;
				if (currentGen > totalGens) { //New Generation.
					enableRating();
					if (sec1Rated && sec2Rated && sec3Rated && sec4Rated) {
						breedNewGen();
						sec1Rated = false;
						sec2Rated = false;
						sec3Rated = false;
						sec4Rated = false;
					} else {
						currentGen--;
						System.out.println(currentGen + "- 2");
						enableRating();
						nextGen.setEnabled(true);
					}
				} else if (currentGen == totalGens) { //Current Generation.
					enableRating();
				} else { //Old Generation.
					disableRating();
				}
				System.out.println(currentGen);
				updateGenCounter(currentGen);
				resetSecButtons();
			} else if (e.getSource() == prevSec) {
				nextSec.setEnabled(true);
				currentSec--;
				if (currentSec <= 1) { //Lower Limit.
					currentSec = 1;
					prevSec.setEnabled(false);
				}
				System.out.println(currentSec);
				updateSecCounter();
			} else if (e.getSource() == nextSec) {
				prevSec.setEnabled(true);
				currentSec++;
				if (currentSec > totalSecs) { //Upper Limit.
					currentSec = totalSecs;
					nextSec.setEnabled(false);
				}
				updateSecCounter();
			} else if (e.getSource() == replaySec) {
				midi.music_export(myPop.individuals[0].genes[0], "sec" + 0 + ".mid");
				midi.music_play("sec" + 0 + ".mid");
				scoreCount++;
			} else if (e.getSource() == rate) {
				parseInput(rating.getText());
			} else if (e.getSource() == rating) {
				parseInput(rating.getText());
			}
		}
	}

	public static void main(String[] args) {
		// Create an initial population
        myPop = new Population(4, true);
        
        midi = new MidiConverter();
        //midi.music_export(myPop.individuals[0].genes, "indiv.mid");
        //midi.music_play("indiv.mid");
        
		//Setup Window Contents.
		ButtonHandler listener = new ButtonHandler();
		//Setup Buttons...
		playGen = new JButton("Play All");
		playGen.addActionListener(listener);
		prevGen = new JButton("Prev Gen");
		prevGen.addActionListener(listener);
		prevGen.setEnabled(false);
		nextGen = new JButton("Next Gen");
		nextGen.addActionListener(listener);
		prevSec = new JButton("Prev Sec");
		prevSec.addActionListener(listener);
		prevSec.setEnabled(false);
		nextSec = new JButton("Next Sec");
		nextSec.addActionListener(listener);
		replaySec = new JButton("Play Sec");
		replaySec.addActionListener(listener);
		rate = new JButton("Rate!");
		rate.addActionListener(listener);
		//Setup labels...
		genLabel = new JLabel("Generation: " + currentGen);
		secLabel = new JLabel("Section " + currentSec + " of " + totalSecs);
		ratingLabel = new JLabel("Rating (1-10): ");
		errorLabel = new JLabel("");
		errorLabel.setForeground(Color.red);
		//Setup text field...
		rating = new JTextField(50);
		rating.addActionListener(listener);

		//Add everything to the panel.
		content = new JPanel();
		content.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		{
			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridwidth = 1;
			c.gridx = 2;
			c.gridy = 0;
			content.add(genLabel, c);

			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.2;
			c.gridwidth = 1;
			c.gridx = 0;
			c.gridy = 1;
			content.add(prevGen, c);

			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.2;
			c.gridwidth = 1;
			c.gridx = 1;
			c.gridy = 1;
			content.add(prevSec, c);

			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.2;
			c.gridwidth = 1;
			c.gridx = 2;
			c.gridy = 1;
			content.add(secLabel, c);

			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.2;
			c.gridwidth = 1;
			c.gridx = 3;
			c.gridy = 1;
			content.add(nextSec, c);

			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.2;
			c.gridwidth = 1;
			c.gridx = 4;
			c.gridy = 1;
			content.add(nextGen, c);

			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.4;
			c.gridwidth = 2;
			c.gridx = 0;
			c.gridy = 2;
			content.add(errorLabel, c);

			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.2;
			c.gridwidth = 1;
			c.gridx = 2;
			c.gridy = 2;
			content.add(ratingLabel, c);

			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.4;
			c.gridwidth = 2;
			c.gridx = 3;
			c.gridy = 2;
			content.add(rating, c);

			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.6;
			c.gridwidth = 3;
			c.gridx = 0;
			c.gridy = 3;
			content.add(replaySec, c);

			c.fill = GridBagConstraints.HORIZONTAL;
			c.weightx = 0.4;
			c.gridwidth = 2;
			c.gridx = 3;
			c.gridy = 3;
			content.add(rate, c);

			c.fill = GridBagConstraints.HORIZONTAL;
			c.gridwidth = 5;
			c.gridx = 0;
			c.gridy = 4;
			content.add(playGen, c);//Setup Grid Bag Layout
		}
		//Setup the window.
		window = new JFrame("AI Music v" + version);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setContentPane(content);
		window.setSize(600, 150);
		window.setLocation(100, 100);
		window.setVisible(true);
	}
	
	public static void breedNewGen() {
		//System.out.println("Generation: " + totalGens + " Fittest: " + myPop.getFittest().getFitness());
        //myPop.print();
		totalGens++;
        myPop = Algorithm.evolvePopulation(myPop);
	}

	public static void resetSecButtons() {
		prevSec.setEnabled(false);
		nextSec.setEnabled(true);
		currentSec = 1;
		updateSecCounter();
	}

	public static void sectionAddRating(int val) {
		switch (currentSec) {
			case 0: sec1Rated = true;
			case 1: sec2Rated = true;
			case 2: sec3Rated = true;
			case 3: sec4Rated = true;
		}
		myPop.individuals[currentGen].fitness = val;
	}
	
	public static void parseInput(String str) {
		try {
			//Parse input.
			int val = Integer.parseInt(str);
			if (checkRating(val)) {
			sectionAddRating(val);
			rating.setText("");
			nextSec.doClick();
			rating.requestFocus();
			} else {
				setErrorText("Out of Range!");
				rating.setText("");
				rating.requestFocus();
			}
		} catch (NumberFormatException nfe) {
			setErrorText("Invalid Format!");
			rating.setText("");
			rating.requestFocus();
		}
	}

	public static void setErrorText(String msg) {
		errorLabel.setText(msg);
	}

	public static boolean checkRating(int val) {
		if (val > 0 && val < 11) {
			return true;
		} else  {
			return false;
		}
	}

	public static void enableRating() {
		rate.setEnabled(true);
		rating.setEnabled(true);
		rating.setText("");
	}

	public static void disableRating() {
		rate.setEnabled(false);
		rating.setEnabled(false);
		rating.setText("");
	}

	public static void updateGenCounter(int gen) {
		genLabel.setText("Generation: " + gen);
		window.repaint(); //Update Graphics.
		window.revalidate(); //Again.
	}

	public static void updateSecCounter() {
		secLabel.setText("Section " + currentSec + " of " + totalSecs);
	}
}