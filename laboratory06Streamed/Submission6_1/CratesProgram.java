package laboratory06Streamed.Submission6_1;

/**
 * JP2 Lab 6
 * bin-packing algorithms. 
 * This class is the program entry point.
 * It initializes some packing strategies
 * (defined by students) and then
 * tests them out on a known
 * sequence of inputs.
 * @author jsinger
 */
public class CratesProgram {

    public static void main(String [] args) {
        int [] items = { 75, 50, 20, 60, 40, 50 };
        PackingStrategy [] strategies = { new FirstFit(), new BestFit(), new WorstFit() };
        
        for (int item : items) {
            for (PackingStrategy s : strategies) {
                s.addAmount(item);
            }
        }
        
        for (PackingStrategy s : strategies) {
            System.out.println(s.toString());
        }
    }
}