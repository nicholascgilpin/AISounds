 
	// Population code used from: http://www.theprojectspot.com/tutorial-post/creating-a-genetic-algorithm-for-beginners/3
	public class Population {

	    Individual[] individuals;
	    int defaultGeneLength = 4;

	    /*
	     * Constructors
	     */
	    // Create a population
	    public Population(int populationSize, boolean initialise) {
	        individuals = new Individual[populationSize];
	        // Initialize population
	        if (initialise) {
	            // Loop and create individuals
	            for (int i = 0; i < size(); i++) {
	                Individual newIndividual = new Individual(defaultGeneLength);
	                newIndividual.generateIndividual();
	                saveIndividual(i, newIndividual);
	            }
	        }
	    }

	    /* Getters */
	    // Get optimum fitness (Every section of music was rated 10/10)
	    int getMaxFitness() {
	    	return 10;
	    }
	    
	    public Individual getIndividual(int index) {
	        return individuals[index];
	    }

	    public Individual getFittest() {
	        Individual currentFittest = individuals[0];
	        // Loop through individuals to find fittest
	        for (int i = 0; i < size(); i++) {
	            if (currentFittest.getFitness() <= getIndividual(i).getFitness()) {
	            	currentFittest = getIndividual(i);
	            }
	        }
	        return currentFittest;
	    }

	    /* Public methods */
	    // Get population size
	    public int size() {
	        return individuals.length;
    }

	    // Save individual
	    public void saveIndividual(int index, Individual indiv) {
	        individuals[index] = indiv;
	    }
	    // Print the fitness of each individual in the population
	    public void print(){
	    	System.out.print("<");
	    	for(int i = 0; i < individuals.length-1; i++){
	    		System.out.print(individuals[i].getFitness());
//	    		if(i>0){
	    			System.out.print(",");
//	    		}
	    	}
	    	System.out.print(individuals[individuals.length-1].getFitness());
	    	System.out.println(">");
	    }
	}
