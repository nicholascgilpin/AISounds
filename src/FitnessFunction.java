import java.util.Scanner;

public class FitnessFunction {
	
    // Calculate inidividual's fitness by comparing it to our candidate solution
    static short getFitness(Individual individual) {
    	MidiConverter midi = new MidiConverter();
        short fitness = -1;
        Scanner sc = new Scanner(System.in);
        while(fitness == -1){
            System.out.println("Rate this on a scale from 1-10:");
            midi.music_export(individual.genes, "indiv.mid");
            midi.music_play("indiv.mid");
            short input = sc.nextShort();
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
    
}
