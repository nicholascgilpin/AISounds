import jm.music.data.Note;
import jm.music.data.Phrase;


public class Algorithm {
	  /* GA parameters */
    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    // Determines whether the fittest individual is immortal
    private static final boolean elitism = false;

    /* Public methods */
    
    // Evolve a population
    public static Population evolvePopulation(Population pop) {
        Population newPopulation = new Population(pop.size(), false);

        // Keep our best individual
        if (elitism) {
            newPopulation.saveIndividual(0, pop.getFittest());
        }

        // Crossover population
        int elitismOffset;
        if (elitism) {
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }
        // Loop over the population size and create new individuals with
        // crossover
        for (int i = elitismOffset; i < pop.size(); i++) {
            Individual indiv1 = tournamentSelection(pop);
            Individual indiv2 = tournamentSelection(pop);
            Individual newIndiv = crossover(indiv1, indiv2);
            newPopulation.saveIndividual(i, newIndiv);
        }

        // Mutate population
        for (int i = elitismOffset; i < newPopulation.size(); i++) {
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }

    // Crossover individuals
    private static Individual crossover(Individual indiv1, Individual indiv2) {
        Individual newSol = new Individual(indiv1.size());
        // Loop through genes
        for (int i = 0; i < indiv1.size(); i++) {
            // Crossover
        	// Get 1st note in 1st phrase
        	// Get notes from 2nd phrse until == duration 1st note
        	int track2 = 0;
        	int currentNote = 0;
        	double dur1 = 0;
        	double dur2 = 0;
        	Note buffer;	// Maximum note size for temp1 comparisons
            Note[] buffer2; //Maximum note size for temp2 comparisons
        	Phrase phr = new Phrase();
            for (int track = 0; track < indiv1.getGene(i).length();track++){
        		buffer = indiv1.getGene(i).getNote(track);
        		buffer2 = new Note[8];
        		buffer2[currentNote] = indiv2.getGene(i).getNote(track2);
            	dur1 = buffer.getRhythmValue();
            	dur2 = buffer2[currentNote].getRhythmValue();
            	while (dur2 < dur1) {
            		currentNote++;
            		buffer2[currentNote] = indiv2.getGene(i).getNote(track2);
            		dur2 += buffer2[currentNote].getRhythmValue();
            	}
            	if (dur2 > dur1) {
            		//Split Notes
            	}
            	if (Math.random() <= uniformRate) {
            		phr.addNote(buffer);
            	} else {
            		phr.addNoteList(buffer2);
            	}
            	
            }
            newSol.setGene(i, phr);
        }
        return newSol;
    }

    // Mutate an individual
    private static void mutate(Individual indiv) {
        // Loop through genes
        for (int i = 0; i < indiv.size(); i++) {
            if (Math.random() <= mutationRate) {
                // Create random gene
                //byte gene = (byte) Math.round(Math.random());
                //indiv.setGene(i, gene);
            }
        }
    }

    // Select individuals for crossover
    private static Individual tournamentSelection(Population pop) {
        // Create a tournament population
        Population tournament = new Population(tournamentSize, false);
        // For each place in the tournament get a random individual
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomId));
        }
        // Get the fittest
        Individual fittest = tournament.getFittest();
        return fittest;
    }
}
