package laboratory02;

import java.util.Scanner;

/**
 * Exercise for JP2 lab 2
 * Compute credit totals and GPA scores
 * for students.
 * Read student results from standard input in form:
 * ^STUDENTID (COURSEID CREDITS GRADE)
 * Output summary result to standard output in form:
 * ^STUDENTID CREDITS_TOTAL GPA
 * 
 * <p>
 * bug:
 * 3 provided tests fail, however the code produces expected results 
 * when test values provided manually.
 * No bugs found.
 * 
 * @author m.sz.
 */
public class CreditsAndGPAs {
    static int credits;
	
    public static void main(String[] args) {
    	getLine();
    } 
    
    private static void getLine() {
    	String studentID = "";
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Input your data, then \'ENTER\' and ctrl+d (ctrl+z on Windows)");
    	boolean valid = sc.hasNext();
    	if(valid) studentID = sc.next();
    	int total = 0, credit = 0;
		
		while(valid) {
			if(!sc.hasNext()) break;
			
			valid = false;
			sc.next(); // to skip course code
			
			if(!sc.hasNextInt()) { error(2); break; }
			credit = sc.nextInt();
			
			if(!sc.hasNext()) { error(3); break; }
			String band = sc.next();
			
			if(!band.equals("CW") && !band.equals("CR")) {
				total += (decode(band) * credit);
				credits += credit;
			}
			valid = true;
		}
		
		if(sc != null) sc.close();
		if(total < 0 || studentID.isEmpty()) { error(5); return; }
    	if(valid) System.out.printf("%s %d %.2f", studentID, credits, 
    			credits == 0? 0.00 : (double)total/credits);
	}

    /**
     * Utility methods. Generates an error message that
     * includes the error identifier.
     * 
     * @param i, error identifier
     */
	private static void error(int i) { System.out.println("badly formatted input, " + i); }
	
	/**
	 * Converts provided band into its numerical equivalent.
	 * 
	 * @param band
	 * @return numerical equivalent of the band
	 */
	private static int decode(String band) {
		char letter = Character.toUpperCase(band.charAt(0));
		return band.equals("CR") || band.equals("CW") ? 0 :
			letter == 'G'? 2 : (int)('H' - letter + 1) * 2; 
	}
}
