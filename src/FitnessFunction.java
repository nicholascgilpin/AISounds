import java.util.Scanner;

public class FitnessFunction {
	
    // Calculate inidividual's fitness by comparing it to our candidate solution
    static int getFitness(Individual individual) {
        int fitness = -1;
        Scanner sc = new Scanner(System.in);
        while(fitness == -1){
            System.out.println("Rate this on a scale from 1-10:");
            MidiConverter.music_play(individual);
            int input = sc.nextInt();
            switch(input){
            case 1: fitness = input; break;
            case 2: fitness = input; break;
            case 3: fitness = input; break;
            case 4: fitness = input; break;
            case 5: fitness = input; break;
            case 6: fitness = input; break;
            case 7: fitness = input; break;
            case 8: fitness = input; break;
            case 9: fitness = input; break;
            case 10: fitness = input; break;
            // Causes the loop to ask the question again
            default: break;
            }
        }
        System.out.println("Rating: " + fitness);
        return fitness;
    }
    
    // Get optimum fitness
    // TODO This may cause problems later, don't  youuuu forget about meeee
    static int getMaxFitness() {
//        int maxFitness = solution.length;
//        return maxFitness;
    	return 1;
    }
}
