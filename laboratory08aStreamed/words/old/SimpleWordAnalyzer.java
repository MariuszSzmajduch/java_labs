package laboratory08aStreamed.words.old;

/**
 * Class search a textfile for longest word and/or
 * word with most occurrences of specified character.<br>
 * Class tested, works as expected. No bugs found.
 * <p>
 *  
 * @since JSE8
 * @version 1.0 
 * @author msz
 * @see WordAnalyzer        
 */
public class SimpleWordAnalyzer implements WordAnalyzer {   
    private String fileName;
    CachingWordAnalyzer cache;
    
    /**
     * 
     * @param fileName - input text file. 
     */
    public SimpleWordAnalyzer(String fileName) { this.fileName = fileName; }

    @Override
    public String getFileName() { return this.fileName; }
   
    @Override
    public String longestWordStartingWith(char c) {
        validateChar(c);
        if (cache == null) 
            cache = CachingWordAnalyzer.getInstance(fileName);
        return cache.longestWordStartingWith(c);
    }    

    @Override
    public String wordWithMostOccurrencesOf(char c) { 
        validateChar(c);
        if (cache == null) 
            cache = CachingWordAnalyzer.getInstance(fileName);
        return cache.wordWithMostOccurrencesOf(c);
    }
    
    /**
     * Accepts lower-case letter characters only.
     * Otherwise throws an exception.
     * 
     * @param c - character to validate
     * @throws IllegalArgumentException
     */
    void validateChar(char c) throws IllegalArgumentException {
        if (!Character.isLetter(c) || Character.isUpperCase(c)) 
            throw new IllegalArgumentException(" is not a letter or"
            		+ " it does not start with a lower-case letter.");
    }
}
