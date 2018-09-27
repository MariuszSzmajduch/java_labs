package laboratory08Streamed.Submission8_1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

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
	private static String[] longestWords, mostOccurrences;
	private static int[] charOccurrences;
	
    /**
     * @param args[0] - name of the input text file.
     */
    public static void main(String [] args) {
    	setup();
    	fetchData();    	
    	printResult();
    }

    /**
     * Fills arrays with default values.
     * 
     */
	private static void setup() {
		longestWords = new String[ALPHABET_SIZE];
		mostOccurrences = new String[ALPHABET_SIZE];
		charOccurrences = new int[ALPHABET_SIZE];
		Arrays.fill(longestWords, "");
    	Arrays.fill(mostOccurrences, "");
	}

	/**
	 * Fetches words from a given input file, then for each letter in the alphabet
	 * finds the longest word starting with the letter and words with most occurrences
	 * of the letter.
	 * 
	 */
	private static void fetchData() {
		try(BufferedReader br = new BufferedReader(new FileReader(
				"labs/laboratory08Streamed/Submission8_1/words.txt"))) {
			String word;
			while((word = br.readLine()) != null) { 
				findLongest(word); 
				findMostOccurrences(word);
			}
		} catch (FileNotFoundException e) { System.out.println(e.getMessage()); System.exit(-1);
		} catch (IOException e) { e.printStackTrace(); }
	}


	/**
	 * For each letter i the alphabet finds and stores the longest
     * starting with the letter.
     * 
     * @param word - candidate word
     */
    private static void findLongest(String word) {
    	int idx = word.chars().limit(1)				// equivalent to word.charAt(0)
	    					.map(Character::toLowerCase)
	    					.map(i -> i - 'a')
	    					.sum();
    	if(word.length() > longestWords[idx].length()) longestWords[idx] = word;
    }
    	
	/**
	 * tests input word for the presence of a letter with the most occurrence. 
	 * 
	 * @param word - tested word.
	 */
    private static void findMostOccurrences(String word) {
    	preprocess(word).forEach((k, v) -> {
				    		if(v > charOccurrences[k]) {
				    			charOccurrences[k] = v.intValue();
				    			mostOccurrences[k] = word;
				    		}
    	});
	}

    /**
     * Maps each character in the input word to its occurrence in the word.
     * 
     * @param word
     * @return Collection of tuples consisting of a character converted to index value
     * and its number of occurrences in input word.
     */
	private static Map<Character, Long> preprocess(String word) {
		return word.chars()										// integer stream
					.filter(Character::isLetter)				// letters only
					.map(Character::toLowerCase)		
					.mapToObj(c -> (char)(c - 'a'))				// converts to index
					.collect(Collectors.groupingBy(c -> c, Collectors.counting()));	
	}
	
	/**
	 * Send longest words and words with most occurrences to standard output.
	 * 
	 */
	private static void printResult() {
		Stream.of(longestWords)
		.forEach(s -> System.out.printf("%s:\t%s\n", s.charAt(0), s));
		System.out.println("\n-----------------------------\n"); 	
		IntStream.range(0, ALPHABET_SIZE)
		.forEach(i -> System.out.printf("%c: %s\n", 'a' + i, mostOccurrences[i]));
	}

}
