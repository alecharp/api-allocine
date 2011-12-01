package org.lecharpentier.api.allocine;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.List;

/**
 * The Interface AllocineConnector gives methods for the communication with the
 * Allocine API.
 * 
 * @author Adrien Lecharpentier <adrien@lecharpentier.org>
 */
public abstract class AllocineSearch {
	private final Logger logger = Logger.getLogger(AllocineSearch.class.getName());

	private final String searchURL;
	private final String movieURL;

	/**
	 * Create a new instance of AllocineSearch with default values.
	 * <ul>
	 * <li>using xml</li>
	 * <li>using partner number 3</li>
	 * <li>using the medium profile</li>
	 * <li>http://api.allocine.fr/xml/search for search engine</li>
	 * <li>http://api.allocine.fr/xml/movie for details engine</li>
	 * </ul>
	 */
	public AllocineSearch() {
		this(3, "medium", "http://api.allocine.fr/xml/");
	}

	private AllocineSearch(int partnerNumber, String profile, String baseURL) {
		this.searchURL = baseURL + "search?profile=" + profile + "&partner=" + partnerNumber + "&q=";
		logger.debug(searchURL);
		this.movieURL = baseURL + "movie?profile=" + profile + "&partner=" + partnerNumber + "&code=";
		logger.debug(movieURL);
	}

	/**
	 * Gets the url for basic search.
	 * 
	 * @return the search url
	 */
	public String getSearchURL() {
		return searchURL;
	}

	/**
	 * Gets the url for specific movie search.
	 * 
	 * @return the movie url
	 */
	public String getMovieURL() {
		return movieURL;
	}

	/**
	 * Search for films based on the title.
	 * 
	 * @param title
	 *            the title of the film
	 * @return a collection of {@link MovieBrief}, each one representing a
	 *         result of the research. The returned value is never null but can
	 *         be empty.
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public abstract List<MovieBrief> search(String title) throws IOException;

	/**
	 * Details.
	 * 
	 * @param filmCode
	 *            the film code
	 * @return the movie details
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public abstract MovieDetails details(long filmCode) throws IOException;
}
