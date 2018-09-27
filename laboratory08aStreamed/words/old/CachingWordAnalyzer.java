package laboratory08aStreamed.words.old;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

/**
 * This class caches data fetched from a textfile
 * using two principles:
 * <ul>
 * <li> longest words
 * <li> words with the most occurrences of each character
 * </ul>
 * Class tested, works as expected. No bugs found.
 * <p>
 *  
 * @since JSE8 
 * @version 1.0 
 * @author msz
 * @see SimpleWordAnalyzer      
 */
class CachingWordAnalyzer extends SimpleWordAnalyzer {
    private final static char ALPHABET_FIRST_LETTER = 'a',
            ALPHABET_LAST_LETTER = 'z';    
    private final static int ALPHABET_SIZE = 'z' - 'a' + 1;
    private static CachingWordAnalyzer cache; 
    
    /**
     * String array caching selected words
     */
    private String[] arrLongestWord, arrWordWithMostOccurrences; 

    private CachingWordAnalyzer(String fileName) {
        super(fileName);
        setArrays();
    }
    
    /**
     * Returns the only object of this type.<br>
     * Creates new object if no object exists yet.
     * <p>
     * 
     * @param fileName - source file
     * @return {@link CachingWordAnalyzer} object
     */
    static CachingWordAnalyzer getInstance(String fileName) {
        return cache == null ? (cache = new CachingWordAnalyzer(fileName)) : cache ;
    }  
    
    @Override
    public String longestWordStartingWith(char c) {
      return LambdaWordAnalyser.getWord(
      this.arrLongestWord,
      (c - ALPHABET_FIRST_LETTER),
      (words, index) -> words[index]);
    }
    
    @Override
    public String wordWithMostOccurrencesOf(char c){
      return LambdaWordAnalyser.getWord(
              this.arrWordWithMostOccurrences,
              (c - ALPHABET_FIRST_LETTER),
              (words, index) -> words[index]);
    }

    /**
     * sets {@link #arrLongestWord} and {@link #wordWithMostOccurrencesOf}
     */
    private void setArrays() {
        initiate();        
        cache();        
    }

    /**
     * It creates arrays and fills them with empty strings.
     */
    private void initiate() {
        this.arrLongestWord = new String[ALPHABET_SIZE];
        this.arrWordWithMostOccurrences = new String[ALPHABET_SIZE];
        Arrays.fill(this.arrLongestWord, "");         
        Arrays.fill(this.arrWordWithMostOccurrences, "");
    }

    private void cache() {
        try (BufferedReader br = new BufferedReader(
                new FileReader(this.getFileName()))){            
            String wordFromFile = "";
            
            while ((wordFromFile = br.readLine()) != null) {
                try {
                    char firstLetter = wordFromFile.charAt(0);
                    validateChar(firstLetter);      // throws IllegalArgumentException 
                    
                    setArrLongestWord(wordFromFile, firstLetter);
                    setArrWordWithMostOccurrences(wordFromFile, ALPHABET_FIRST_LETTER);                 
                } catch (IllegalArgumentException e) {            // enables next loop
//                        log(wordFromFile + e.getMessage()); 
                    }
            }
        } catch (FileNotFoundException e) {
            log("file not found " + e);
        } catch (IOException e) {
            log("unexpected i/o problem - " + e);
            e.printStackTrace();
      }
    }
    
    /**
     * puts longest words into an array in alphabetical order,
     * one word for each first letter.
     * 
     * @param wordFromFile word
     * @param firstLetter
     */
    private void setArrLongestWord(String wordFromFile, char firstLetter) {
        int index = firstLetter - ALPHABET_FIRST_LETTER;
        if (wordFromFile.length() > this.arrLongestWord[index].length())
            this.arrLongestWord[index] = wordFromFile; 
    }
    
    /**
     * Puts words with most occurences of each letter 
     * into an array in alphabetical order
     * 
     * @param wordFromFile word
     * @param c - tested letter
     */
    private void setArrWordWithMostOccurrences(String wordFromFile, char c) { 
        int index = c - ALPHABET_FIRST_LETTER;
        if (calculateOccurence(wordFromFile, c, 0) 
                > calculateOccurence(this.arrWordWithMostOccurrences[index], c, 0))
            this.arrWordWithMostOccurrences[index] = wordFromFile;
        
        if (++c <= ALPHABET_LAST_LETTER) 
            setArrWordWithMostOccurrences(wordFromFile, c); 
    }
    
    /**
     * Calculates occurrence of character <tt>c</tt>
     * in given word.
     * 
     * @param word
     * @param c given character
     * @param index position in word
     * @return amount of given character in the word
     */
    private int calculateOccurence(String word, char c, int index) {
        return index == word.length() ? 0 : (word.charAt(index) == c ? 1 : 0)
                + calculateOccurence(word, c, ++index); // :-)
    }
    
    /**
     * Utility method displaying error message.
     * <p>
     * 
     * @param mes text to display.
     */
    private void log(String mes) { System.err.println(mes); }
}
