package laboratory02aStreamed.PalindromeFinder;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

public class PalindromeTest {

	  @Before
	    public void setup() {}
	  
	  @After
	    public void teardown() {}

	    @Test
	    public void testEmptyString() throws Exception {
	      assertTrue("incorrect result (The empty string is a palindrome)", Palindrome.isPalindrome("")); 
	  }
	  
	    @Test
	    public void testSingleWordLambdaPalindrome() throws Exception {
	      String pal1 = "hannah";
	      String pal2 = "xxx";
	      String pal3 = "o";
	      String pal4 = "noon";
	      String pal5 = "madam";
	      String pal6 = "";
	      String pal7 = "a a";

	      assertTrue("incorrect result (" + pal1 + " is a palindrome)", Palindrome.isPalindrome(pal7));
	      assertTrue("incorrect result (" + pal1 + " is a palindrome)", Palindrome.isPalindrome(pal1)); 
	      assertTrue("incorrect result (" + pal2 + " is a palindrome)", Palindrome.isPalindrome(pal2)); 
	      assertTrue("incorrect result (" + pal3 + " is a palindrome)", Palindrome.isPalindrome(pal3)); 
	      assertTrue("incorrect result (" + pal4 + " is a palindrome)", Palindrome.isPalindrome(pal4)); 
	      assertTrue("incorrect result (" + pal5 + " is a palindrome)", Palindrome.isPalindrome(pal5));
	      assertTrue("incorrect result, empty string is a palindrome)", Palindrome.isPalindrome(pal6));
	    }


	    @Test
	    public void testSingleWordNonLambdaPalindrome() throws Exception {
	      String nonpal1 = "hanna";
	      String nonpal2 = "xxyx";
	      String nonpal3 = "oe";
	      String nonpal4 = "afternoon";
	      String nonpal5 = "madame";
	      
	      assertFalse("incorrect result (" + nonpal1 + " is not a palindrome)", Palindrome.isPalindrome(nonpal1)); 
	      assertFalse("incorrect result (" + nonpal2 + " is not a palindrome)", Palindrome.isPalindrome(nonpal2)); 
	      assertFalse("incorrect result (" + nonpal3 + " is not a palindrome)", Palindrome.isPalindrome(nonpal3)); 
	      assertFalse("incorrect result (" + nonpal4 + " is not a palindrome)", Palindrome.isPalindrome(nonpal4)); 
	      assertFalse("incorrect result (" + nonpal5 + " is not a palindrome)", Palindrome.isPalindrome(nonpal5)); 
	    }
	  
	  
	    @Test
	    public void testMultiWordLambdaPalindrome() throws Exception {
	      String pal1 = "never odd or even";
	      String pal2 = "Madam, I'm Adam";
	      String pal3 = "A man, a plan, a canal - Panama!";
	      String pal4 = "Rise to vote, sir";
	      String pal5 = "...e!!??";
	      
	      assertTrue("incorrect result (" + pal2 + " is a palindrome)", Palindrome.isPalindrome(Palindrome.preprocess(pal1)));
	      assertTrue("incorrect result (" + pal2 + " is a palindrome)", Palindrome.isPalindrome(Palindrome.preprocess(pal2))); 
	      assertTrue("incorrect result (" + pal3 + " is a palindrome)", Palindrome.isPalindrome(Palindrome.preprocess(pal3))); 
	      assertTrue("incorrect result (" + pal4 + " is a palindrome)", Palindrome.isPalindrome(Palindrome.preprocess(pal4))); 
	      assertTrue("incorrect result (" + pal5 + " is a palindrome)", Palindrome.isPalindrome(Palindrome.preprocess(pal5))); 
	    }
	  
	    @Test
	    public void testMultiWordNonLambdaPalindrome() throws Exception {
	      String nonpal1 = "once upon a time";
	      String nonpal2 = "xxx yx xxx";
	      String nonpal3 = "oooh oooh";
	      String nonpal4 = "fee fi fo fum";
	      String nonpal5 = "madame, j'ai faim!";
	      
	      assertFalse("incorrect result (" + nonpal1 + " is not a palindrome)", Palindrome.isPalindrome(Palindrome.preprocess(nonpal1))); 
	      assertFalse("incorrect result (" + nonpal2 + " is not a palindrome)", Palindrome.isPalindrome(Palindrome.preprocess(nonpal2))); 
	      assertFalse("incorrect result (" + nonpal3 + " is not a palindrome)", Palindrome.isPalindrome(Palindrome.preprocess(nonpal3))); 
	      assertFalse("incorrect result (" + nonpal4 + " is not a palindrome)", Palindrome.isPalindrome(Palindrome.preprocess(nonpal4))); 
	      assertFalse("incorrect result (" + nonpal5 + " is not a palindrome)", Palindrome.isPalindrome(Palindrome.preprocess(nonpal5))); 
	    }
	  
	}