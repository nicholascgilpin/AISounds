import jm.JMC;
import jm.music.data.*;
import jm.music.tools.*;
import jm.util.*;

public class MidiConverter {
	//class variables


			public Phrase[] music_import(String filename){ // music importer: takes in (midi) file,
			//interprets file and exports as matrix for us to use
				
				//functions associated with Score class
				Score theScore = new Score("Imported score"); //make new score from midi file
				Read.midi(theScore, filename); //read midi file called "inputfle.midi"
				Part part = theScore.getPart(0); //gets part in score
				

				//functions associated with Part class
				double partend = part.getEndTime();
				Phrase[] phrase = part.getPhraseArray();
					
				return phrase;

			}

			public void music_play(String filename){ //plays music given a file?
				Score theScore = new Score("Imported score"); //make new score from midi file
				Read.midi(theScore, filename); //read midi file called "inputfle.midi"
				Play.midi(theScore);
			}
}
