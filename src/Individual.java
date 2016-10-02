
import jm.JMC;
import jm.music.data.*;
import jm.midi.*;
import jm.util.*;

// Change the bit string to be a music measure
public class Individual {

	   static int defaultGeneLength = 4;
	   //private byte[] genes = new byte[defaultGeneLength];
	   public Phrase[] genes = new Phrase[defaultGeneLength];
	   private short fitness = 0;	// Rating from 1-10 that is set by FitnessFunction.getFitness(i)
	
	public Individual(int defaultGeneLength){
		Individual.defaultGeneLength = defaultGeneLength;
	}

    // Create a random individual
    public void generateIndividual() {
		Phrase phr;
		for (int i=0; i<4; i++) {
			phr = new Phrase("Chromatic Scale", i*4.0);
			for(int j=0;j<4;j++) {
				int pitch = (int) Math.floor(Math.random()*10) + 30;
				Note n = new Note(pitch, 1);
				phr.addNote(n);
			}
			genes[i] = phr;
		}
		
		
    }

    /* Getters and setters */
    // Use this if you want to create individuals with different gene lengths
    public static void setDefaultGeneLength(int length) {
        defaultGeneLength = length;
    }
    
    public Phrase getGene(int index) {
        return genes[index];
    }

    public void setGene(int index, Phrase value) {
        genes[index] = value;
        fitness = 0;
    }

    /* Public methods */
    public int size() {
        return genes.length;
    }

    public int getFitness() {
        if (fitness == 0) {
            fitness = FitnessFunction.getFitness(this);
        }
        return fitness;
    }

    @Override
    public String toString() {
        String geneString = "";
        for (int i = 0; i < size(); i++) {
            geneString += getGene(i).toString();
        }
        return geneString;
    }
    
}
