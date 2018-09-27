package laboratory05.Submission5_1;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * JP2 Lab 5
 * encapsulates a single ticket
 * or result for a Lottery draw
 * i.e. has 6 distinct int values
 * in range 1..49.
 */
public class LotteryNumbers {

	/**
	 * Contains lottery numbers.
	 */
	private int[] ln;
	
	/**
	 * Describes the method used to generate a ticket. 
	 */
	private String typeOfDraw;
   
   /**
    * Returns new {@link LotteryNumber} object 
    * containing an array of six distinct integer 
    * values in range 1..49;
    * 
    * @return {@link LotteryNumber} object.
    */
   public static LotteryNumbers makeDraw() {
	   return new LotteryNumbers();
   }

   /**
    * Creates new {@link LotteryNumbers} object 
    * with six distinct integer values.
    * 
    */
   private LotteryNumbers() {
	   this.ln = new int[6];
	   this.generateTicket();
	   this.typeOfDraw = "random";
   }
   
   /**
    * Creates {@link LotteryNumbers} object from a textual
    * representation of a ticket with lottery numbers.
    * The proper input format is assumed.
    * 
    * @param ticket
    */
   public LotteryNumbers(String ticket) {
	   this.ln = new int[6]; 
	   this.convert(ticket);
	   this.typeOfDraw = "fixed";
   }
   
   /**
    * Evaluates and prints a lottery result
    * for a ticket.
    * 
    * @param draw - {@link LotteryNumbers} object
    * @return
    */
   public String status(LotteryNumbers draw) {
	   int match = 0;
	   for(int i = 0; i < this.ln.length; i++)
		   if(this.contains(draw.ln[i])) match++;
	   return "Ticket:\t" + this.printLotteryNumbers() + 
			   (match == 6 ? "\twinner" :
			   		match == 5 ? "\trunner up" :
			   			match == 4 ? "\tthird prize" :
			   				match == 3 ? "\tconsolation" : 
			   					"\tloser");
	} 

/**
    * A helper methods. Assigns six distinct random integer values
    * to the array holding lottery numbers.
    */
   private void generateTicket() {
	   int i = 0;
	   while(i < this.ln.length) {
		   int j = (int)(Math.random()* 49 + 1);
		   if(!this.contains(j)) this.ln[i++] = j;
	   }
	   Arrays.sort(this.ln);
	}
   
   /**
    * A helper method. Generates <code>lotteryNumbers</code>
    * from a textual input.
    * 
    * @param ticket - six lottery numbers in string format.
    */
   private void convert(String ticket) {
	   Scanner sc = new Scanner(ticket);
	   int i = 0;
	   while(sc.hasNextInt()) 
		   this.ln[i++] = sc.nextInt();
	   Arrays.sort(this.ln);
	   sc.close();
   }
   
   /**
    * A helper method. Tests a presence of given value in
    * the <code>lotteryNumbers</code>. 
    * 
    * @param i - tested value
    * @return <code>true</code> if the value is present,
    * <code>false</code> otherwise.
    */
   private boolean contains(int i) {
	   boolean contains = false;
	   for(int j : this.ln) contains = contains || j == i;
	   return contains;
   }
   
   /**
    * Returns type of draw and lottery numbers.
    */
   @Override
   public String toString() {
   	return String.format("The %s lottery draw is:\t%s", this.typeOfDraw,
   			this.printLotteryNumbers());
   }

   /**
    * 
    * @return textual representation of lottery numbers.
    */
   private String printLotteryNumbers() {
	   return IntStream.of(this.ln)
			   .mapToObj(String::valueOf)
			   .collect(Collectors.joining(" "));
   }
}
