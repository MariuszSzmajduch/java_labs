package laboratory05.Submission5_1;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * set of JUnit tests for JP2 lab 5
 * LotteryNumbers class defined by
 * students
 *
 * NOTE because the draw generation is 
 * pseudo-random, sometimes your tests may
 * pass, other times they may fail...
 * You want them to pass all the time!
 *
 * @author jsinger
 */
public class TestLotteryNumbers {

    private static LotteryNumbers draw;
    private static LotteryNumbers ticket;

    @Before
    public void setup() {

    }

    @After
    public void teardown() {
        draw = null;
        ticket = null;
    }

    /**
     * check there are six int values
     * in the draw
     */
    @Test
    public void testDrawHasSixNumbers() {
        draw = LotteryNumbers.makeDraw();
        String drawString = draw.toString();
        int numberOfNumbers = 0;
        for (String s: drawString.split("[\\s]+")) {
            try {
                Integer.parseInt(s);
                numberOfNumbers++;
            }
            catch (Exception e) {
                // s was not a number?
                continue;
            }
        }
        assertTrue("draw does not have six ints", numberOfNumbers==6);
    }

    /**
     * test the numbers are output in ascending
     * numerical order
     */
    @Test
    public void testSortedNumbers() {
        draw = LotteryNumbers.makeDraw();
        String drawString = draw.toString();
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        for (String s: drawString.split("[\\s]+")) {
            try {
                int number = Integer.parseInt(s);
                numbers.add(number);
            }
            catch (Exception e) {
                // s was not a number?
                continue;
            }
        }
        for (int i=1; i<numbers.size(); i++) {
            assertTrue("draw output is not sorted in ascending order", numbers.get(i-1) < numbers.get(i));
        }
    }

    /**
     * test the numbers in the draw are all distinct
     */
    @Test
    public void testDistinctNumbers() {
        draw = LotteryNumbers.makeDraw();
        String drawString = draw.toString();
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        for (String s: drawString.split("[\\s]+")) {
            try {
                int number = Integer.parseInt(s);
                numbers.add(number);
            }
            catch (Exception e) {
                // s was not a number?
                continue;
            }
        }
        while(numbers.size() > 0) {
            int i = numbers.remove(0);
            assertFalse("draw output contains duplicate numbers", numbers.contains(i));
        }
    }

    /**
     * test the numbers in the draw are
     * all in range [1,49]
     */
    @Test
    public void testNumbersInRange() {
        draw = LotteryNumbers.makeDraw();
        String drawString = draw.toString();
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        for (String s: drawString.split("[\\s]+")) {
            try {
                int number = Integer.parseInt(s);
                numbers.add(number);
            }
            catch (Exception e) {
                // s was not a number?
                continue;
            }
        }
        for (int i : numbers) {
            assertTrue("number out of range", i>=1 && i<=49);
        }
    }

    /**
     * test for winning result
     */
    @Test
    public void testWinner() {
        draw = new LotteryNumbers("1 2 3 4 5 6");
        ticket = new LotteryNumbers("1 2 3 4 5 6");
        String result = ticket.status(draw);
        assertTrue("6 matching numbers should win", result.contains("winner"));
    }

    /**
     * test for runner up result
     */
    @Test
    public void testRunnerUp() {
        draw = new LotteryNumbers("1 2 3 4 5 6");
        ticket = new LotteryNumbers("1 2 3 4 5 49");
        String result = ticket.status(draw);
        assertTrue("5 matching numbers should be runner up", result.contains("runner up"));
    }

    /**
     * test for third prize result
     */
    @Test
    public void testThirdPrize() {
        draw = new LotteryNumbers("1 2 31 42 45 46");
        ticket = new LotteryNumbers("1 2 33 34 45 46");
        String result = ticket.status(draw);
        assertTrue("4 matching numbers should be third prize", result.contains("third prize"));
    }
    /**
     * test for consolation result
     */
    @Test
    public void testConsolation() {
        draw = new LotteryNumbers("1 2 31 42 45 46");
        ticket = new LotteryNumbers("1 2 33 34 43 46");
        String result = ticket.status(draw);
        assertTrue("3 matching numbers should be consolation prize", result.contains("consolation"));
    }

    /**
     * test for loser
     */
    @Test
    public void testLoser() {
        draw = new LotteryNumbers("1 2 3 4 5 6");
        ticket = new LotteryNumbers("21 22 23 24 25 26");
        String result = ticket.status(draw);
        assertTrue("0 matching numbers should lose", result.contains("loser"));
    }
        
                   
}
