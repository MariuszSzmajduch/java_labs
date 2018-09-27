package Laboratory05a.Submission5_1;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit tests for the {@link NewsFinder} class
 * 
 * @author jsinger
 */
public class NewsFinderTest {

	@Test
	public void setupNewsFinder() throws Exception {
		NewsFinder f = null;
		f = new NewsFinder(
				"https://www.glasgow.gov.uk/RSS_Feed_Traffic_Alerts/");
		assertTrue("unable to construct NewsFinder for well-known feed",
				f != null);
	}

	@Test
	public void searchInNews1() throws Exception {
		NewsFinder f = new NewsFinder(
				"https://www.glasgow.gov.uk/RSS_Feed_Traffic_Alerts/");
		assertTrue("unable to find Glasgow in local traffic news",
				f.isInNews("Glasgow"));
	}

	@Test
	public void searchInNews2() throws Exception {
		NewsFinder f = new NewsFinder(
				"http://feeds.bbci.co.uk/news/scotland/rss.xml?edition=uk");
		assertTrue("unable to find Scotland in local BBC news",
				f.isInNews("Scot"));
	}

}
