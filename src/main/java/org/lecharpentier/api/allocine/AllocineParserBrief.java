package org.lecharpentier.api.allocine;

import java.io.IOException;
import java.util.List;

import org.jdom.JDOMException;

/**
 * @author Adrien Lecharpentier <adrien@lecharpentier.org>
 */
abstract class AllocineParserBrief {

	protected AllocineParserBrief() {
	}

	abstract List<MovieBrief> parse(String content) throws JDOMException, IOException;

	static AllocineParserBrief createParser() {
		return new AllocineParserBriefXML();
	}
}
