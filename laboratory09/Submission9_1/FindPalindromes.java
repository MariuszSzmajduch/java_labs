package laboratory09.Submission9_1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * JP2 lab 9 driver program
 * Read in a file containing one word per line,
 * find palindrome words and print out list of
 * all palindromes found at the end.
 * @author jsinger
 */
public class FindPalindromes {

    /**
     * take a single param - the filename
     * containing list of candidate words, 
     * detect palindrome words and print out a 
     * list of these palindromes
     */
    public static void main(String [] args) {
    	String url = "labs/labs/laboratory09/Submission9_1/" + args[0];
    	ArrayList<Palindrome> palindromes = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(url))){
        	String line = "";
        	while((line = br.readLine()) != null) {
        		try {
        			Palindrome palindrome = new Palindrome(line);
        			palindromes.add(palindrome);
        		} catch (NotPalindromicException e) {
        			System.out.println(e.getMessage());
        		}
        	}
        } catch (FileNotFoundException e) {
			System.out.println(e.getMessage() + " no proper file has been found at "
					+ url);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
        
        System.out.println(palindromes.toString());;
    }

}
