
/* 
 * A random grouping of 4 measures will be generated and then the user will rate those 4 measures. 
 * This processes will repeat until the song is satisfactory.
 */
public class AISoundsMain {
	    public static void main(String[] args) {

	        // Create an initial population
	        Population myPop = new Population(5, true);
	        
	        // Evolve our population until we reach an optimum solution
	        int generationCount = 0;
	        System.out.println("Test");
	        // Keep going until the fittest individual is not perfect
	        while (myPop.getFittest().getFitness() < myPop.getMaxFitness()) {
	        	
	            generationCount++;
	            System.out.println("Generation: " + generationCount + " Fittest: " + myPop.getFittest().getFitness());
	            myPop.print();
	            
	            myPop = Algorithm.evolvePopulation(myPop);
	        }
	        System.out.println("Solution found!");
	        System.out.println("Generation: " + generationCount);
	        System.out.println("Genes:");
	        System.out.println(myPop.getFittest());

	    }
	}
