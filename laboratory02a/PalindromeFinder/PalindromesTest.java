package laboratory02a.PalindromeFinder;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test the student's submission
 * for Java lab 2, palindrome program
 */
public class PalindromesTest {
    

  @Before
    public void setup() {
    
  }
  
  @After
    public void teardown() {
    
  }

    @Test
    public void testEmptyString() throws Exception {
      assertTrue("incorrect result (The empty string is a palindrome)", Palindromes.isPalindrome("")); 
  }
  
    @Test
    public void testSingleWordPalindromes() throws Exception {
      String pal1 = "hannah";
      String pal2 = "xxx";
      String pal3 = "o";
      String pal4 = "noon";
      String pal5 = "madam";
      String pal6 = "";

      assertTrue("incorrect result (" + pal1 + " is a palindrome)", Palindromes.isPalindrome(pal1)); 
      assertTrue("incorrect result (" + pal2 + " is a palindrome)", Palindromes.isPalindrome(pal2)); 
      assertTrue("incorrect result (" + pal3 + " is a palindrome)", Palindromes.isPalindrome(pal3)); 
      assertTrue("incorrect result (" + pal4 + " is a palindrome)", Palindromes.isPalindrome(pal4)); 
      assertTrue("incorrect result (" + pal5 + " is a palindrome)", Palindromes.isPalindrome(pal5));
      assertTrue("incorrect result, empty string is a palindrome)", Palindromes.isPalindrome(pal6));
    }


    @Test
    public void testSingleWordNonPalindromes() throws Exception {
      String nonpal1 = "hanna";
      String nonpal2 = "xxyx";
      String nonpal3 = "oe";
      String nonpal4 = "afternoon";
      String nonpal5 = "madame";
      
      assertFalse("incorrect result (" + nonpal1 + " is not a palindrome)", Palindromes.isPalindrome(nonpal1)); 
      assertFalse("incorrect result (" + nonpal2 + " is not a palindrome)", Palindromes.isPalindrome(nonpal2)); 
      assertFalse("incorrect result (" + nonpal3 + " is not a palindrome)", Palindromes.isPalindrome(nonpal3)); 
      assertFalse("incorrect result (" + nonpal4 + " is not a palindrome)", Palindromes.isPalindrome(nonpal4)); 
      assertFalse("incorrect result (" + nonpal5 + " is not a palindrome)", Palindromes.isPalindrome(nonpal5)); 
    }
  
  
    @Test
    public void testMultiWordPalindromes() throws Exception {
      String pal1 = "never odd or even";
      String pal2 = "Madam, I'm Adam";
      String pal3 = "A man, a plan, a canal - Panama!";
      String pal4 = "Rise to vote, sir";
      String pal5 = "...e!!??";
      
      assertTrue("incorrect result (" + pal1 + " is a palindrome)", Palindromes.isPalindrome(Palindromes.preprocess(pal1))); 
      assertTrue("incorrect result (" + pal2 + " is a palindrome)", Palindromes.isPalindrome(Palindromes.preprocess(pal2))); 
      assertTrue("incorrect result (" + pal3 + " is a palindrome)", Palindromes.isPalindrome(Palindromes.preprocess(pal3))); 
      assertTrue("incorrect result (" + pal4 + " is a palindrome)", Palindromes.isPalindrome(Palindromes.preprocess(pal4))); 
      assertTrue("incorrect result (" + pal5 + " is a palindrome)", Palindromes.isPalindrome(Palindromes.preprocess(pal5))); 
    }
  
    @Test
    public void testMultiWordNonPalindromes() throws Exception {
      String nonpal1 = "once upon a time";
      String nonpal2 = "xxx yx xxx";
      String nonpal3 = "oooh oooh";
      String nonpal4 = "fee fi fo fum";
      String nonpal5 = "madame, j'ai faim!";
      
      assertFalse("incorrect result (" + nonpal1 + " is not a palindrome)", Palindromes.isPalindrome(Palindromes.preprocess(nonpal1))); 
      assertFalse("incorrect result (" + nonpal2 + " is not a palindrome)", Palindromes.isPalindrome(Palindromes.preprocess(nonpal2))); 
      assertFalse("incorrect result (" + nonpal3 + " is not a palindrome)", Palindromes.isPalindrome(Palindromes.preprocess(nonpal3))); 
      assertFalse("incorrect result (" + nonpal4 + " is not a palindrome)", Palindromes.isPalindrome(Palindromes.preprocess(nonpal4))); 
      assertFalse("incorrect result (" + nonpal5 + " is not a palindrome)", Palindromes.isPalindrome(Palindromes.preprocess(nonpal5))); 
    }
  
}