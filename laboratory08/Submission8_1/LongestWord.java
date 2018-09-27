package laboratory08.Submission8_1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * JP2 Lab 8
 * find the longest word beginning
 * with each letter of the alphabet,
 * from an input text file and for each letter a word with most
 * occurrences of the letter.
 * 
 * @author jsinger, msz.
 */
public class LongestWord {
	private final static int ALPHABET_SIZE = 26;
	private static String[] longestWord = new String[ALPHABET_SIZE];
	private static String[] mostOccurrences = new String[ALPHABET_SIZE];
	private static int[] characterAmount = new int[ALPHABET_SIZE];
	
    /**
     * @param args[0] - name of the input text file.
     */
    public static void main(String [] args) {
    	Arrays.fill(longestWord, "");
    	Arrays.fill(mostOccurrences, "");
    	Arrays.fill(characterAmount, 0);
    	
    	try(BufferedReader br = new BufferedReader(new FileReader("labs/laboratory08/Submission8_1/words.txt"))) {
    		String word;
    		while((word = br.readLine()) != null) { 
    			findLongest(word); 
    			testCharacters(word);
    		}
    	} catch (FileNotFoundException e) { System.out.println(e.getMessage()); System.exit(-1);
		} catch (IOException e) { e.printStackTrace(); }
    	
    	char c = 'a';
    	for(String word : longestWord) {
    		System.out.printf("%c: %s\n", c++, word);
    	}
    	System.out.println("\n-----------------------------\n");
    	
    	c = 'a';
    	for(String word : mostOccurrences) {
    		System.out.printf("%c: %s\n", c++, word);
    	}
    }

	/**
     * Selects the longest words to store, for each beginning letter separately.
     * 
     * @param word - candidate word
     */
    private static void findLongest(String word) {
    	int index = word.toLowerCase().charAt(0) - 'a'; // array indexed by first character of a word.
    	if(word.length() > longestWord[index].length()) longestWord[index] = word;
    }
    	
	/**
	 * For each letter finds a word with most occurrences of the letter. 
	 * 
	 * @param word - tested word.
	 */
    private static void testCharacters(String word) {
    	String lcw = word.toLowerCase();
    	int[] charactersInThisWord = new int[ALPHABET_SIZE];
    	Arrays.fill(charactersInThisWord, 0);
    	
    	// number of letters in input word
    	int i = -1;
    	while(++i < lcw.length()) {
    		char c = lcw.charAt(i);
    		if(Character.isLetter(c)) charactersInThisWord[c - 'a'] += 1;
    	}
    	
    	// updates when higher occurrence found
    	for(i = 0; i < ALPHABET_SIZE; i++) {
    		if(charactersInThisWord[i] > characterAmount[i]) {
    			characterAmount[i] = charactersInThisWord[i];
    			mostOccurrences[i] = word;
    		}
    	}
	}
}
