package laboratory02a.PalindromeFinder;

import java.util.*;

/**
 * Alternative implementation of palindrome finding using for-loop
 * instead of recursion. <br>
 * Tested, no bugs found. Works as expected.
 * 
 * @version 2.0
 * @author m.sz.
 * April 2018
 */
public class PalindromesForEach {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String possiblePalindrome = preprocess(sc.nextLine());
		System.out.println("is "
				+ (isPalindrome(possiblePalindrome) ? "" : "not ")
				+ "a palindrome");
		sc.close();
		}		

		public static boolean isPalindrome(String s) {
			return s.contentEquals(new StringBuffer(s).reverse());
		}

		private static String preprocess(String s) {
			StringBuffer sb = new StringBuffer();
			for (char c : s.toCharArray())
				sb.append(Character.isLetter(c) ? c : "");
		return sb.toString().toLowerCase();
  }
}
