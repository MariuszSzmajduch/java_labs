package Laboratory05a.Submission5_1;

/**
 * Java lab 5 Main driver class to check which countries appear in news
 * headlines. Students should not need to modify this class.
 * 
 * @author jsinger
 */
public class CheckNews {

	public static void main(String[] args) {
//		 another news feed at
//		String urlString =  "http://mf.feeds.reuters.com/reuters/UKWorldNews";
		String urlString = "http://newsrss.bbc.co.uk/rss/newsonline_uk_edition/world/rss.xml";
		NewsFinder nf = new NewsFinder(urlString);

		for (Country c : Country.getCountries()) {
			if (nf.isInNews(c)) {
				System.out.println("" + c + " is in the news!");
			}
		}
	}
}
