package org.lecharpentier.api.allocine;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

import javax.xml.parsers.ParserConfigurationException;

import junit.framework.TestCase;

import org.jdom.JDOMException;
import org.junit.Test;
import org.xml.sax.SAXException;

/**
 * @author Adrien Lecharpentier <adrien@lecharpentier.org>
 */
public class TestAllocineParserDetailsXML extends TestCase {

	private String content = "";

	@Override
	protected void setUp() throws Exception {
		InputStream is = new BufferedInputStream(TestAllocineParserBriefXML.class.getResourceAsStream("movie.xml"));
		Reader reader = new InputStreamReader(is, "UTF-8");
		StringWriter out = new StringWriter();
		int b;
		try {
			while ((b = reader.read()) != -1)
				out.write(b);
		} finally {
			out.flush();
			out.close();
			reader.close();
		}

		content = out.toString();
	}

	@Test
	public void testXMLParserWithExistingFile() {
		AllocineParserDetailsXML parser = new AllocineParserDetailsXML();
		try {
			parser.parse(content);
		} catch (IOException e) {
			fail(e.getMessage());
		} catch (ParserConfigurationException e) {
			fail(e.getMessage());
		} catch (SAXException e) {
			fail(e.getMessage());
		} catch (JDOMException e) {
			fail(e.getMessage());
		}
	}

	@Test(timeout = 250)
	public void testXMLParserResourceOnServer() {
		AllocineSearchEngine ase = new AllocineSearchEngine();
		try {
			ase.details(13665);
			ase.details(61286);
            MovieDetails md = ase.details(4327);
            //System.out.println(md.getTitle() + ", " + md.getActors() + ", " + md.getDirectors());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
}
