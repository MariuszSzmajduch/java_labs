package laboratory09.Submission9_1;

/**
 * JP2 lab 9
 * A palindrome reads the same forwards and
 * backwards, e.g. 'oxo' or 'racecar'
 * @author jsinger, msz
 */
public class Palindrome {
	private String palindrome;
	
	public Palindrome(String palindrome) throws NotPalindromicException {
		if(!isPalindromic(palindrome)) throw new NotPalindromicException(palindrome);
		this.palindrome = palindrome;
	}

	public static boolean isPalindromic(String line) {
		return (new StringBuilder(line)).reverse()
										.toString()
										.equals(line);
	}
	
	@Override
	public String toString() { return this.palindrome; }
}
