
import jm.JMC;
import jm.music.data.*;
import jm.music.tools.*;
import jm.util.*;

public class MidiConverter {
	


			public Phrase music_import(String filename){ // music importer: takes in (midi) file,
			//interprets file and exports as matrix for us to use
				
				//functions associated with Score class
				Score theScore = new Score("Imported score"); //make new score from midi file
				Read.midi(theScore, filename); //read midi file 
				Part part = theScore.getPart(0); //gets part in score
				

				//functions associated with Part class
				//double partend = part.getEndTime();
				Phrase thePhrase = part.getPhrase(0); //gets phrase in part
					
				return thePhrase;

			}
			
			public void music_export(Phrase thePhrase, String filename){
				Part thePart = new Part(); //create empty part
				Score theScore = new Score("Exported score"); //make new score
				thePart.addPhrase(thePhrase); //add phrase to part
				theScore.addPart(thePart); //add part to score
				Write.midi(theScore, filename);
			}

			public void music_play(String filename){ //plays music given a file
				Score theScore = new Score("Imported score"); //make new score from midi file
				Read.midi(theScore, filename); //read midi file 
				Part part = theScore.getPart(0); //gets part in score
				part.setInstrument(79);//79 for ocarina, 3 for ht piano
				
				theScore.setTempo(105.0);
				Play.midi(theScore);
			}
}
