package laboratory05.Submission5_1;

import java.util.Scanner;

/**
 * JP2 Lab 5
 *
 * Driver class for {@link LotteryNumbers}
 * instantiates several LotteryNumbers
 * objects and checks them for 
 * prizes.
 * @author jsinger
 * 
 * This solution intentionally doesn't use Collections. M.Sz.
 */
public class LotterySimulation {
    
    public static void main(String[] args) {
        
        Scanner inputScanner = new Scanner(System.in);
        
        // make and report the draw
        LotteryNumbers randomDraw = LotteryNumbers.makeDraw();
        System.out.println("\nThe lottery draw: " + randomDraw + "\n");
        
        // fix a draw for test purposes
        LotteryNumbers fixedDraw = new LotteryNumbers(" 5 10 15 20 25 30");
        System.out.println("\nThe lottery fix: " + fixedDraw + "\n");
        
        
        // now process the tickets
        while (inputScanner.hasNextLine()){
            
            String nextLine = inputScanner.nextLine();
            
            // quit on empty line
            if (nextLine.length() == 0) break;

            LotteryNumbers ticket = new LotteryNumbers(nextLine);
            
            System.out.println(ticket.status(randomDraw));
            System.out.println(ticket.status(fixedDraw));
            System.out.println();
        }
        inputScanner.close();
    }
}
