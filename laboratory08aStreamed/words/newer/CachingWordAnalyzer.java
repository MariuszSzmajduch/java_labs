package laboratory08aStreamed.words.newer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * 
 * This is improved version of {@link SimpleWordAnalyzer}. The performance gain is achieved by reducing
 * the number of file traversing to one go. 
 * This class searches a text file for longest word and for 
 * a word with most occurrences of some specified character.<br>
 * <p>
 * The class contains no no-arg constructor. Each instantiation must provide valid 
 * file url.<br>
 * Class tested, works as expected. No bugs found.
 * 
 * @see SimpleWordAnalyzer
 * 
 * @author msz
 * @date 05.06.2018
 */
public class CachingWordAnalyzer extends SimpleWordAnalyzer {
	static final int ALPHABET_LENGTH = idx('z') + 1;
	static String[] longestWords, mostOccurrences;
	static int[] occurrences;
	
	/**
	 * Utility method indexing letters according to their position in the alphabet.
	 * 
	 * @param c - input character
	 * @return - integer number equal to the position of the character in the alphabet.
	 */
	static int idx(char c) { return c - 'a'; }
	
	public CachingWordAnalyzer(String fileName) { super(fileName); }

	@Override
	public String longestWordStartingWith(char c) throws IllegalArgumentException {
		this.test(c);
		this.memoize();
		return longestWords[idx(c)];
	}

	@Override
	public String wordWithMostOccurrencesOf(char c) throws IllegalArgumentException {
		this.test(c);
		this.memoize();
		return mostOccurrences[idx(c)];
	}
	
	/**
	 * Utility method. Creates a list of longest words and a list of words with most 
	 * occurrences if they are not present. 
	 * 
	 */
	void memoize() {
		if(longestWords != null && mostOccurrences != null) return; // runs only when not found previously
		
		this.initiate(); 
		String testWord = "";
		try(BufferedReader br = new BufferedReader(new FileReader(this.getFileName()))){
			while((testWord = br.readLine()) != null) {
				this.updateOccurrences(testWord);
				this.updateLongestWord(testWord);
			}
		} catch (FileNotFoundException e) { System.err.println(e.getMessage());
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	/**
	 * Utility method. tests whether the input word is the longest word
	 * among the words starting with the same letter. Updates the list of longest words
	 * if input word is a new longest word found.
	 * 
	 * @param testWord
	 */
	private void updateLongestWord(String testWord) {
		int i = idx(testWord.toLowerCase().charAt(0));
		longestWords[i] = testWord.length() > longestWords[i].length() ?
				testWord : longestWords[i];
	}

	/**
	 * Helper method.
	 * Tests if input word contains a character with the highest occurrence.
	 * Updates the list of words with the highest character occurrences.
	 * 
	 * @param testWord
	 */
	private void updateOccurrences(String testWord) {
		HashMap<Character, Integer> charFreq = new HashMap<>(); //  frequency of characters in testWord
		for(Character c : testWord.toLowerCase().toCharArray()) { // calculates frequency of characters
			if(!Character.isLetter(c)) continue;
			if(charFreq.get(c) == null) charFreq.put(c, 0); // create an entry if not present yet
			charFreq.put(c, charFreq.get(c) + 1);
			}
		
		charFreq.forEach((k, v) -> {		//update
			int i = idx(k);
			if(v > occurrences[i]) {	// if higher occurrences found for a character
				occurrences[i] = v;		// update occurrences for the character and...
				mostOccurrences[i] = testWord;  // record the word with the highest character occurrence
			}
		});	
	}
	
	/**
	 * Utility method. Initiates backup arrays and fills them with a default values.
	 * 
	 */
	private void initiate() {
		longestWords = new String[ALPHABET_LENGTH];
		mostOccurrences = new String[ALPHABET_LENGTH];
		occurrences = new int[ALPHABET_LENGTH];
		
		Arrays.fill(longestWords, "");
		Arrays.fill(mostOccurrences, "");
		Arrays.fill(occurrences, 0);
	}
}
