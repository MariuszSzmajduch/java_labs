package laboratory05aStreamed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * This class represents a country.
 * Each instance holds the name of the country.
 * Class maintains also the list of all unique
 * <code>Country</code> objects.<br>
 * Class tested and works as expected. No bugs found.
 * 
 * @author <li>jsinger
 *         <li>mszmajduch<br>
 */
public class Country {
	/**
	 * Creates a list of unique UK-localised countries.
	 * 
	 * @return a list of created {@link Country} objects;
	 */
	public static ArrayList<Country> getCountries(){ 
		return (ArrayList<Country>) Arrays.asList(Locale.getAvailableLocales()).stream()
					.map(locale -> locale.getDisplayCountry(Locale.UK))
					.filter(country -> !country.isEmpty())
					.distinct()
					.map(Country::new)
					.collect(Collectors.toList());
		}
	
	private String name;

	/**
	 * Creates {@link Country} object for specified country name.
	 * 
	 * @param countryName
	 */
	public Country(String countryName) { this.name = countryName; }

	/**
	 * @return <code>name</code> of the country.
	 */
	@Override 
	public String toString() { return this.name; }

	/**
	 * Compares this {@link Country} object with another object.
	 * 
	 * @return <code>true</code> if an enclosed <code>name</code> 
	 * variables have the same value, <code>false</code> otherwise.
	 */
	@Override 
	public boolean equals(Object other) { return this.name == ((Country) other).name;
	}
}
