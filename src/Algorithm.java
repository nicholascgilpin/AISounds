import jm.music.data.Note;
import jm.music.data.Phrase;


public class Algorithm {
	  /* GA parameters */
    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 4;
    // Determines whether the fittest individual is immortal
    private static final boolean elitism = true;

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
        // Loop through notes
        int track2 = 0;
    	int currentNote = 0;
    	double dur1 = 0;
    	double dur2 = 0;
    	Note buffer;	// Maximum note size for temp1 comparisons
        Note buffer2; //Maximum note size for temp2 comparisons
    	Phrase phr = new Phrase();
        for (int i = 0; i < indiv1.genes[0].length(); i++) {
        // Crossover
    	// Get 1st note in 1st phrase
    	// Get notes from 2nd phrse until == duration 1st note
		buffer = indiv1.genes[0].getNote(i);
		buffer2 = indiv2.genes[0].getNote(i);
    	dur1 = buffer.getRhythmValue();
    	dur2 = buffer2.getRhythmValue();
    	
    	if (Math.random() <= uniformRate) {
    		phr.addNote(buffer);
    		System.out.println("Chose Note 1 " + buffer.toString());
    	} else {
    		phr.addNote(buffer2);
    		System.out.println("Chose Note 2 " + buffer2.toString());
    	}
    	
    }
    newSol.setGene(0, phr);
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
