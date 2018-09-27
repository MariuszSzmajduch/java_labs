package laboratory08aStreamed.words.old;

import java.util.function.BiFunction;

/**
 * This is a utility class providing algorithm
 * to retrieve string objects from string arrays.
 * Class tested, works as expected. No bugs found.
 * <p>
 * 
 * @since JSE8 
 * @version 1.0 
 * @author msz
 */
public class LambdaWordAnalyser { 
   
   /**
    * This method returns a word from an array using
    * provided rule.
 * @param <T>
 * @param <U>
    * 
    * @param words -array of words
    * @param index - first letter
    * @param rule - function returning required word
    * @return - the word looked for
    */
    public static <T, U> T getWord(T[] t, U u,
            BiFunction<T[], U, T> rule) {
        return rule.apply(t, u);
    }
}
