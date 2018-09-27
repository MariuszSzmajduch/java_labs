package laboratory08aStreamed.words.old;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Java lab 8
 * JUnit test cases for the
 * WordAnalyzer
 * @author jsinger
 */

public class TestWordAnalyzer {

  private static WordAnalyzer wa;

  @Before
  public void setup() {
    // replace with CachingWordAnalyzer if 
    // you implement this too...
    wa = new SimpleWordAnalyzer("words.txt");
  }

  @After
  public void teardown() {
    wa = null;
  }

  /**
   * check correct outputs for 'a'
   */
  @Test
  public void testLongestWord1() {
    String s = wa.longestWordStartingWith('a');
    assertTrue("longest A word is alphabet", s.equals("alphabet"));
  }

  @Test
  public void testMostCharsWord1() {
    String s = wa.wordWithMostOccurrencesOf('a');
    assertTrue("word with most A's is aardvark", s.equals("aardvark"));
  }

  /**
   * check empty string (not null string) outputs for 'z'
   */
  @Test
  public void testLongestWord2() {
    String s = wa.longestWordStartingWith('z');
    assertTrue("longest Z word is empty string", s.equals(""));
  }

  @Test
  public void testMostCharsWord2() {
    String s = wa.wordWithMostOccurrencesOf('z');
    assertTrue("word with most Z's is empty string", s.equals(""));
  }
  

  /**
   * check incorrect inputs
   */
  @SuppressWarnings("unused")
@Test(expected=IllegalArgumentException.class)
    public void testIllegalInputCaps1() {
    String s = wa.longestWordStartingWith('A');
  }

  @SuppressWarnings("unused")
@Test(expected=IllegalArgumentException.class)
    public void testIllegalInputPunctuation1() {
    String s = wa.longestWordStartingWith('!');
  }

  @SuppressWarnings("unused")
@Test(expected=IllegalArgumentException.class)
    public void testIllegalInputCaps2() {
    String s = wa.wordWithMostOccurrencesOf('Z');
  }

  @Test(expected=IllegalArgumentException.class)
    public void testIllegalInputPunctuation2() {
    @SuppressWarnings("unused")
    String s = wa.wordWithMostOccurrencesOf('?');
  }

}