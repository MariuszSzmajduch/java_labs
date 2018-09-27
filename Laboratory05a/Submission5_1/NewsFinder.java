package Laboratory05a.Submission5_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * This class reads the news from a supplied URL and
 * caches the content, ready for searches.<br>
 * Class tested and works as expected. No bugs found.
 * 
 * @author <li>jsinger
 *         <li>Mariusz Szmajduch<br>
 *         2089488S@student.gla.ac.uk<br>
 *         <center>28 Oct 2014
 */
public class NewsFinder {
   private String url, content;
   
   public NewsFinder(String url) {
	   this.url = url;
	   setContent();
   }
   
   /**
    * stores text fetched from <code>url</code> in <code>content</code>.
    */
   private void setContent() {
	   try {
		BufferedReader bf = new BufferedReader(new InputStreamReader(new URL(this.url).openStream()));
		StringBuilder sb = new StringBuilder();
		while(bf.ready()) {
			sb.append(bf.readLine());
		}
		this.content = sb.toString();
	   } catch (MalformedURLException e) {}
	   catch (IOException e) {}
   }
   
   /**
    * Tests that string representation of input object is present in
    * fetched <code>this.content</code>.
    * 
    * @param o
    * @return <code>true</code> if a string representing input object is fetched,
    * <code>false</code> otherwise.
    */
   public boolean isInNews(Object o) {
	   return this.content.indexOf(o.toString()) >= 0;
   }
}
