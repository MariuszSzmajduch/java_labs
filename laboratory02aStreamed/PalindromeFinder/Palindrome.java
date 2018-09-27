package laboratory02aStreamed.PalindromeFinder;

import java.util.Scanner;
import java.util.stream.Collectors;
/**
 * Palindromes class Java lab 2, 2014.
 * 
 * Program takes user input and displays a message
 * whether the input is a palindrome or not
 * Tested, no bugs found. Works as expected.
 *
 * @version 2.0 - uses lambda expressions.
 * @author Mariusz Szmajduch
 * April 2018
 */
public class Palindrome {; 
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String possiblePalindrome = preprocess(sc.nextLine());
		System.out.printf("%s is %s a palindrome", sc, isPalindrome(possiblePalindrome)? "" : "not");
		sc.close();
	}

	/**
	 * Method tests whether passed argument is a palindrome or not
	 * 
	 * @param s - string to be tested 
	 * @return true if the input string is a palindrome, false otherwise.
	 */
	public static boolean isPalindrome(String s) {
		return s.isEmpty() || s.contentEquals((new StringBuffer(s)).reverse());
		}

	/**
	 * This method strips out spaces and punctuation. It also normalises all
	 * characters to lower case.
	 * 
	 * @param s - string variable to preprocess
	 * @return new String with only lower-case characters.
	 */
	public static String preprocess(String s) {
		return s.chars().mapToObj(i -> (char)i)				// int-stream to char-stream
				.filter(Character::isLetter)
				.map(Character::toLowerCase)
				.map(String::valueOf)
				.collect(Collectors.joining());
	}
}
