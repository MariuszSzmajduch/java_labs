package laboratory02;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Test the student's submission
 * for JP2 lab 2, credit/gpa calculations
 */
public class CreditsAndGPAsTest {
    
    InputStream stdin;
    PrintStream stdout;

    @Before
    public void setup() {
	stdin = System.in;
	stdout = System.out;
    }

    @After
    public void teardown() {
	System.setIn(stdin);
	System.setOut(stdout);
    }

    @Test
    public void testCorrectSingleCourse() throws Exception {
	String testInput = "1234567 CS1P 20 A3 \n";
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	PrintStream ps = new PrintStream(baos);
	System.setIn(new ByteArrayInputStream(testInput.getBytes()));
	System.setOut(ps);
	CreditsAndGPAs.main(null);
	System.out.flush();
	String testOutput = baos.toString();
	assertTrue("incorrect result (should be 20 credits)", testOutput.contains("20"));
	assertTrue("incorrect result (should be GPA 16.00)", testOutput.contains("16.00"));	
    }


    @Test
    public void testCorrectMultipleCourse() throws Exception {
	String testInput = "0109999 HVF7 20 C3 FRE0 10 CW SDC5 40 A4 JUI9 20 D1 DFU5 30 C2 \n";
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	PrintStream ps = new PrintStream(baos);
	System.setIn(new ByteArrayInputStream(testInput.getBytes()));
	System.setOut(ps);
	CreditsAndGPAs.main(null);
	System.out.flush();
	String testOutput = baos.toString();
	String [] testOutputs = testOutput.split("\\s+");
	assertTrue("incorrect result (should be 110 credits)", testOutputs[1].contains("110") || testOutputs[2].contains("110") || testOutputs[3].contains("110"));
	assertTrue("incorrect result (should be GPA 13.09)", testOutputs[2].contains("13.09") || testOutputs[3].contains("13.09"));
    }

    /**
     * perfectly legitimate - a student who has taken 0 courses
     */
    @Test
    public void testZeroCourse() throws Exception {
	String testInput = "0912345 \n";
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	PrintStream ps = new PrintStream(baos);
	System.setIn(new ByteArrayInputStream(testInput.getBytes()));
	System.setOut(ps);
	CreditsAndGPAs.main(null);
	System.out.flush();
	String testOutput = baos.toString();
	String [] testOutputs = testOutput.split("\\s+");
	assertTrue("incorrect result (should be 0 credits)", testOutputs[1].contains("0") || testOutputs[2].contains("0"));
	assertTrue("incorrect result (should be GPA 0.00)", testOutputs[2].contains("0.00") || testOutputs[3].contains("0.00"));
    }


    /**
     * a student who has taken one course and failed it
     */
    @Test
    public void testFailedOneCourse() throws Exception {
	String testInput = "0912345 JP02 10 CW\n";
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	PrintStream ps = new PrintStream(baos);
	System.setIn(new ByteArrayInputStream(testInput.getBytes()));
	System.setOut(ps);
	CreditsAndGPAs.main(null);
	System.out.flush();
	String testOutput = baos.toString();
	String [] testOutputs = testOutput.split("\\s+");
	assertTrue("incorrect result (should be 0 credits)", testOutputs[1].contains("0") || testOutputs[2].contains("0"));
	assertTrue("incorrect result (should be GPA 0.00)", testOutputs[2].contains("0.00") || testOutputs[3].contains("0.00"));
    }

    @Test
    public void testIncorrectInput() throws Exception {
	String testInput = "0109999  HVF7 20 \n";
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	PrintStream ps = new PrintStream(baos);
	System.setIn(new ByteArrayInputStream(testInput.getBytes()));
	System.setOut(ps);
	CreditsAndGPAs.main(null);
	System.out.flush();
	String testOutput = baos.toString();
	assertTrue("bad input, should report error", testOutput.toLowerCase().contains("badly formatted input"));
    }

    @Test
    public void testIncorrectScoreInput() throws Exception {
	String testInput = "0109999 HVF7 20 Z2\n";
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	PrintStream ps = new PrintStream(baos);
	System.setIn(new ByteArrayInputStream(testInput.getBytes()));
	System.setOut(ps);
	CreditsAndGPAs.main(null);
	System.out.flush();
	String testOutput = baos.toString();
	assertTrue("bad input, should report error", testOutput.toLowerCase().contains("badly formatted input"));
    }

    @Test
    public void testIncorrectCreditsInput() throws Exception {
	String testInput = "0109999 HVF7 X Z2\n";
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	PrintStream ps = new PrintStream(baos);
	System.setIn(new ByteArrayInputStream(testInput.getBytes()));
	System.setOut(ps);
	CreditsAndGPAs.main(null);
	System.out.flush();
	String testOutput = baos.toString();
	assertTrue("bad input, should report error", testOutput.toLowerCase().contains("badly formatted input"));
    }

    @Test
    public void testEmptyInput() throws Exception {
	String testInput = "\n";
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	PrintStream ps = new PrintStream(baos);
	System.setIn(new ByteArrayInputStream(testInput.getBytes()));
	System.setOut(ps);
	CreditsAndGPAs.main(null);
	System.out.flush();
	String testOutput = baos.toString();
	assertTrue("empty input, should report error or output nothing", testOutput.toLowerCase().contains("badly formatted input") || testOutput.length()<=1);
    }
}