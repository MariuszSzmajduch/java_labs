package laboratory02a.PalindromeFinder;

import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Palindromes class Java lab 2, 2014.
 * 
 * Program takes user input and displays a message
 * whether the input is a palindrome or not
 * Tested, no bugs found. Works as expected.
 *
 * @version 1.1
 * @author Mariusz Szmajduch
 * April 2018
 */
public class Palindromes {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
//		String possiblePalindrome = preprocess(sc.nextLine());
		String possiblePalindrome = streamedPreprocess(sc.nextLine());
		System.out.println(sc + "is "
				+ (isPalindrome(possiblePalindrome) ? "" : "not ")
				+ "a palindrome");
		sc.close();
	}

	/**
	 * Method tests whether passed argument is a palindrome or not
	 * 
	 * @param s - string to be tested 
	 * @return true if the input string is a palindrome, false otherwise.
	 */
	public static boolean isPalindrome(String s) {
		return s.contentEquals((new StringBuffer(s)).reverse());
		}

	/**
	 * This method strips out spaces and punctuation. It also normalises all
	 * characters to lower case.
	 * 
	 * @param s - string variable to preprocess
	 * @return new String with only lower-case characters.
	 */
	public static String preprocess(String s) {
		if(s.isEmpty()) return s;
		char c = s.toLowerCase().charAt(0);
		return (Character.isLetter(c) ? c : "") + preprocess(s.substring(1));
	}
	
	/**
	 * This method strips out spaces and punctuation. It also normalises all
	 * characters to lower case.
	 * 
	 * @param s - string variable to preprocess
	 * @return new String with only lower-case characters.
	 */
	public static String streamedPreprocess(String s) {
		return s.chars().mapToObj(i -> (char)i)
				.filter(Character::isLetter)
				.map(Character::toLowerCase)
				.collect(Collectors.toList()).toString();
	}
}