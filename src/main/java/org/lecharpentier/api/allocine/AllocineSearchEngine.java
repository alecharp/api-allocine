package org.lecharpentier.api.allocine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.jdom.JDOMException;
import org.xml.sax.SAXException;

/**
 * The Class AllocineSearchEngine extends the {@link AllocineSearch} and
 * provides an implementation of the search and details methods using embedded
 * engine.
 * 
 * @author Adrien Lecharpentier <adrien@lecharpentier.org>
 */
public class AllocineSearchEngine extends AllocineSearch {
	private final Logger logger = Logger.getLogger(AllocineSearchEngine.class.getName());

	/**
	 * Search.
	 * 
	 * @param title
	 *            the title
	 * @return the collection
	 * @throws IOException
	 */
	@Override
	public List<MovieBrief> search(String title) throws IOException {
		List<MovieBrief> mbs = new ArrayList<MovieBrief>();
		try {
			String content = readURLContent(getSearchURL() + "\"" + URLEncoder.encode(title, "UTF-8") + "\"");
			AllocineParserBrief parser = AllocineParserBrief.createParser();
			try {
				mbs = parser.parse(content);
			} catch (ClassCastException e) {
				logger.error(e);
			} catch (JDOMException e) {
				logger.error("You may need to report this to the developper", e);
			}
		} catch (IOException e) {
			logger.error("You may need to report this to the developper", e);
			throw e;
		}
		Collections.sort(mbs);
		return Collections.unmodifiableList(mbs);
	}

	/**
	 * Details.
	 * 
	 * @param filmCode
	 *            the film code
	 * @return the movie details
	 * @throws IOException
	 */
	@Override
	public MovieDetails details(long filmCode) throws IOException {

		MovieDetails md = null;
		try {
			String content = readURLContent(getMovieURL() + filmCode);

			AllocineParserDetails parser = AllocineParserDetails.createParser();
			try {
				md = parser.parse(content);
			} catch (ClassCastException e) {
				logger.error(e);
			} catch (ParserConfigurationException e) {
				logger.error(e);
			} catch (SAXException e) {
				logger.error(e);
			} catch (JDOMException e) {
				logger.error(e);
			}
		} catch (MalformedURLException e1) {
			logger.error("You may need to report this to the developper", e1);
		} catch (IOException e1) {
			logger.error("You may need to report this to the developper", e1);
			throw e1;
		}
		return md;
	}

	private String readURLContent(String url) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream(),
				Charset.forName("UTF-8")));
		StringBuilder content = new StringBuilder();
		String tmpLine;

		try {
			while ((tmpLine = reader.readLine()) != null) {
				content.append(tmpLine);
			}
		} finally {
			reader.close();
		}
		return content.toString();
	}
}
