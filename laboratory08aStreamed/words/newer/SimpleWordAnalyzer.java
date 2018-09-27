package laboratory08aStreamed.words.newer;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * 
 * Class searches a text file for longest word and for 
 * a word with most occurrences of some specified character.<br>
 * <p>
 * The class contains no no-arg constructor. Each instantiation must provide valid 
 * file url.<br>
 * Class tested, works as expected. No bugs found.
 * 
 * @author msz
 * @date 25.05.2018
 */
public class SimpleWordAnalyzer implements WordAnalyzer {
	private String fileName;
	
	public SimpleWordAnalyzer(String fileName) {
		this.fileName = "labs/laboratory08a/" + fileName;
	}
	
	@Override
	public String getFileName() { return this.fileName; }

	/**
	 * 
	 * @throws IllegalArgumentException (unchecked) when 
	 * input character is not a lower-case letter.
	 */
	@Override
	public String longestWordStartingWith(char c) throws IllegalArgumentException {
		this.test(c); // throws exception
		String line = "", curr = "";
		try(BufferedReader br = new BufferedReader(new FileReader( this.fileName))){
			while((curr = br.readLine()) != null) {
				char d = curr.toLowerCase().charAt(0);
				line = (d == c) && (curr.length() > line.length()) ? curr : line;
				if(d > c) return line; // early exit
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}

	/**
	 * 
	 * @throws IllegalArgumentException (unchecked) when input character is 
	 * not a lower-case letter.
	 */
	@Override
	public String wordWithMostOccurrencesOf(char c) throws IllegalArgumentException {
		this.test(c);
		String line = "", curr = "";
		int occurrences = 0;
		try(BufferedReader br = new BufferedReader(new FileReader(this.fileName))){
			while((curr = br.readLine()) != null) {
				int currOcc = this.countOccurrences(c, curr.toLowerCase());
				if(occurrences < currOcc) {
					occurrences = currOcc;
					line = curr;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return line;
	}

	/**
	 * 
	 * @param c
	 * @param curr
	 * @return number of occurrences of character <code>c</code>
	 * in String <code>curr</code>.
	 */
	protected int countOccurrences(char c, String curr) {
		if(curr.isEmpty()) return 0;
		return (curr.charAt(0) == c ? 1 : 0) +
				countOccurrences(c, curr.substring(1, curr.length()));
	}
	
	/**
	 * Utility method. Tests that input character is a lower case letter.
	 * 
	 * @param c - a character to test.
	 * @throws IllegalArgumentException (unchecked) when the test fails.
	 */
	protected void test(char c) throws IllegalArgumentException {
		if(!Character.isLetter(c) || !Character.isLowerCase(c))
			throw new IllegalArgumentException();
	}
}
