package org.lecharpentier.api.allocine;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;

import junit.framework.TestCase;

import org.jdom.JDOMException;
import org.junit.Test;

/**
 * @author Adrien Lecharpentier <adrien@lecharpentier.org>
 */
public class TestAllocineParserBriefXML extends TestCase {

	private String content = "";

	@Override
	protected void setUp() throws Exception {
		InputStream is = new BufferedInputStream(TestAllocineParserBriefXML.class.getResourceAsStream("search.xml"));
		Reader reader = new InputStreamReader(is, "UTF-16");
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
		AllocineParserBriefXML parser = new AllocineParserBriefXML();
		try {
			parser.parse(content);
		} catch (JDOMException e) {
			fail(e.getMessage());
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}

	@Test(timeout = 250)
	public void testXMLParserWithOnlineResources() {
		AllocineSearchEngine ase = new AllocineSearchEngine();
		try {
			ase.search("goldeneye");
		} catch (IOException e) {
			fail(e.getMessage());
		}
	}
}
