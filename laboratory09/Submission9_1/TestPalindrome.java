package laboratory09.Submission9_1;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * set of JUnit tests for JP2 lab 9
 * Palindrome class defined by
 * students
 *
 * @author jsinger
 */
public class TestPalindrome {

    private static Palindrome p1;

    @Before
    public void setup() {
        
    }

    @After
    public void teardown() {
        p1 = null;
    }

    /**
     * check some standard palindromes
     */
    @Test
    public void testSomePalindromes() {
        assertTrue("oxo is a palindrome", Palindrome.isPalindromic("oxo"));
        assertTrue("racecar is a palindrome", Palindrome.isPalindromic("racecar"));
        assertTrue("hannah is a palindrome", Palindrome.isPalindromic("hannah"));
        assertTrue("madam is a palindome", Palindrome.isPalindromic("madam"));
        assertTrue("empty string is a palindrome", Palindrome.isPalindromic(""));
        assertTrue("any single char is a palindrome", Palindrome.isPalindromic("a"));
        
    }

    /**
     * check some non-palindromes
     */
    @Test
    public void testNotPalindrome() {
        assertFalse("abc is not a palindrome", Palindrome.isPalindromic("abc"));
        assertFalse("ab is not a palindrome", Palindrome.isPalindromic("ab"));
        assertFalse("foo is not a palindrome", Palindrome.isPalindromic("foo"));
    }

    /**
     * construct some palindromes
     */
    @Test
    public void testPalindromeConstruction1() throws NotPalindromicException {
        String s = "malayalam";
        p1 = new Palindrome(s);
        assertTrue(s + " is a palindrome", p1.toString().equals(s));
    }

    @Test
    public void testPalindromeConstruction2() throws NotPalindromicException {
        String s = "wow";
        p1 = new Palindrome(s);
        assertTrue(s + " is a palindrome", p1.toString().equals(s));
    }

    @Test
    public void testPalindromeConstruction3() throws NotPalindromicException {
        String s = "peep";
        p1 = new Palindrome(s);
        assertTrue(s + " is a palindrome", p1.toString().equals(s));
    }

    @Test
    public void testPalindromeConstruction4() throws NotPalindromicException {
        String s = "a";
        p1 = new Palindrome(s);
        assertTrue("single char" + " is a palindrome", p1.toString().equals(s));
    }

    @Test
    public void testPalindromeConstruction5() throws NotPalindromicException {
        String s = "";
        p1 = new Palindrome(s);
        assertTrue("empty string is a palindrome", p1.toString().equals(s));
    }

}
   
    
    
        

