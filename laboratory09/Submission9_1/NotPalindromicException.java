package laboratory09.Submission9_1;

/**
 * JP2 lab 9
 * This error is thrown to show that 'word'
 * is not a palindrome
 * @author jsinger
 */
@SuppressWarnings("serial")
public class NotPalindromicException extends Exception {

	public NotPalindromicException() {
		this("This is not a palindrome");
	}
	
	public NotPalindromicException(String mess) {
		super(mess + " is not a palindrome.");
	}
}