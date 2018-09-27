package Laboratory05a.Submission5_1;

import java.util.List;

import org.junit.Before;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * JUnit tests for the {@link Country} class
 * 
 * @author jsinger
 */
public class CountryTest {

	private static Country c;

	@Before
	public void setup() {
		c = new Country("Scotland");
	}

	@After
	public void teardown() {
		c = null;
	}

	// check toString returns string with name
	@Test
	public void testToString() {
		assertTrue("Country.toString() should return the country's name", c
				.toString().equals("Scotland"));
	}

	// check equals for diff countries
	@Test
	public void testEqualsDiffCountries() {
		assertFalse("equals should return false for different countries",
				c.equals(new Country("England")));
	}

	// check equals for same country
	@Test
	public void testEqualsSameObject() {
		assertTrue("equals should return true for same Country object",
				c.equals(c));
	}

	// check equals for diff countries same name
	@Test
	public void testEqualsSameCountry() {
		assertTrue(
				"equals should return true for diff Country objects with same name",
				c.equals(new Country("Scotland")));
	}

	// check getCountries returns a non-empty arraylist
	@Test
	public void testGetCountries1() {
		assertTrue("getCountries should return non-empty list", Country
				.getCountries().size() > 0);
	}

	// check getCountries returns a list of Country objects
	@Test
	public void testGetCountries2() {
		for (Object o : Country.getCountries()) {
			assertTrue("each object in list of countries should be a Country",
					o instanceof Country);
		}
	}

	// check getCountries includes various well-known countries...
	@Test
	public void testGetCountries3() {
		List<?> countries = Country.getCountries();
		assertTrue("UK should be in countries list",
				countries.contains(new Country("United Kingdom")));
		assertTrue("France should be in countries list",
				countries.contains(new Country("France")));
		assertTrue("China should be in countries list",
				countries.contains(new Country("China")));
		assertTrue("Portugal should be in countries list",
				countries.contains(new Country("Portugal")));

	}
}
