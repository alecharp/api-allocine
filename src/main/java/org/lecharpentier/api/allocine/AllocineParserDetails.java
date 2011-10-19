package org.lecharpentier.api.allocine;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.jdom.JDOMException;
import org.xml.sax.SAXException;

/**
 * @author Adrien Lecharpentier <adrien@lecharpentier.org>
 */
abstract class AllocineParserDetails {

	protected AllocineParserDetails() {
	}

	abstract MovieDetails parse(String content) throws ParserConfigurationException, SAXException, IOException,
			JDOMException;

	static AllocineParserDetails createParser() {
		return new AllocineParserDetailsXML();
	}
}
