package laboratory08aStreamed.words.newer;

import java.util.function.Function;

/**
 * 
 * Further improvement of {@link SimpleWordAnalyzer} achieved by implementing lambda expressions.
 * This class searches a text file for longest word and for 
 * a word with most occurrences of some specified character.<br>
 * <p>
 * The class contains no no-arg constructor. Each instantiation must provide valid 
 * file url.<br>
 * Class tested, works as expected. No bugs found.
 * 
 * @see SimpleWordAnalyzer, CachingWordAnalyzer
 * 
 * @author msz
 * @date 05.06.2018
 */
public class LambdaWordAnalyser extends CachingWordAnalyzer {
	/**
	 * Contains predefined algorithms of finding a word
	 * 
	 * @author user
	 *
	 */
	private enum wordAnalyzer{
		LONGEST_WORD(LambdaWordAnalyser::getLongestWord),
		MOST_OCCURRENCES(LambdaWordAnalyser::getMostOccurrences);
		
		private Function<Character, String> find;
		
		wordAnalyzer(Function<Character, String> find){
			this.find = find;
		}
	}
	
	public LambdaWordAnalyser(String fileName) { super(fileName); }
	
	@Override
	public String longestWordStartingWith(char c) throws IllegalArgumentException {
		return wordSearcher(wordAnalyzer.LONGEST_WORD.find, c);
	}

	@Override
	public String wordWithMostOccurrencesOf(char c) throws IllegalArgumentException {
		return wordSearcher(wordAnalyzer.MOST_OCCURRENCES.find, c);
	}
	
	/**
	 * This methods tests correctness of character input, then applies the character to a word search
	 * algorithm.
	 * 
	 * @param charFunction - algorithm to be applied in the process of finding a word
	 * @param c - an input to provided charFunction.
	 * @return a word which is a result of applying input character to input function.
	 * @throws IllegalArgumentException
	 */
	private String wordSearcher (Function<Character, String> charFunction, char c) throws IllegalArgumentException{
		this.test(c);
		this.memoize();
		return charFunction.apply(c);
	}
	
	private static String getLongestWord(Character c) { return longestWords[idx(c)]; }
	private static String getMostOccurrences(Character c) { return mostOccurrences[idx(c)]; }
}
