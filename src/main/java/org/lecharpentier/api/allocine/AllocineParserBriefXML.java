package org.lecharpentier.api.allocine;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.SAXOutputter;
import org.jdom.xpath.XPath;
import org.lecharpentier.api.allocine.MovieBrief.MovieBriefFactory;
import org.lecharpentier.api.allocine.Person.PersonFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Adrien Lecharpentier <adrien@lecharpentier.org>
 */
class AllocineParserBriefXML extends AllocineParserBrief {
	private static final String XMLNS = "http://www.allocine.net/v6/ns/";

	AllocineParserBriefXML() {
	}

	@Override
	List<MovieBrief> parse(String content) throws JDOMException, IOException {
		ArrayList<MovieBrief> mbs = new ArrayList<MovieBrief>();
		SAXBuilder sxb = new SAXBuilder();
		Document doc = sxb.build(new StringReader(content));

		Element root = doc.getRootElement();
		XPath xpa = XPath.newInstance("//allocine:movie");
		xpa.addNamespace("allocine", XMLNS);

		List<?> entries = xpa.selectNodes(root);
		Iterator<?> it = entries.iterator();

		SAXOutputter output = new SAXOutputter(new BriefHandler(mbs));
		while (it.hasNext()) {
			Element movie = (Element) it.next();
			output.output(movie);
		}
		return mbs;
	}
}

class BriefHandler extends DefaultHandler {
	private final ArrayList<MovieBrief> mbs;
	private final MovieBriefFactory mbf = new MovieBriefFactory();
	private final PersonFactory pf = new PersonFactory();
	private StringBuilder sb;

	private final Logger logger = Logger.getLogger(BriefHandler.class.getName());

	BriefHandler(ArrayList<MovieBrief> mbs) {
		this.mbs = mbs;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		sb = new StringBuilder();
		if (AllocineConstants.MOVIE.equals(qName)) {
			mbf.create();
			try {
				mbf.setFilmCode(Long.parseLong(attributes.getValue(AllocineConstants.CODE)));
			} catch (NumberFormatException e) {
				logger.warn("Error with film code", e);
			}
		}
		if (AllocineConstants.POSTER.equals(qName)) {
			mbf.setPoster(attributes.getValue(AllocineConstants.HREF));
		}
		if (AllocineConstants.LINK.equals(qName)) {
			mbf.setPageUrl(attributes.getValue(AllocineConstants.HREF));
		}
		if (AllocineConstants.PERSON.equals(qName)) {
			pf.create();
		}
		if (AllocineConstants.ACTIVITY.equals(qName)) {
			try {
				long code = Long.parseLong(attributes.getValue(AllocineConstants.CODE));
				if (code == AllocineConstants.ACTOR_CODE) {
					mbf.addActor(pf.validate());
				}
				if (code == AllocineConstants.DIRECTOR_CODE) {
					mbf.addDirector(pf.validate());
				}
			} catch (NumberFormatException e) {
				logger.warn("Error with person type", e);
			}
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		sb.append(ch, start, length);
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (AllocineConstants.ORIGINAL_TITLE.equals(qName)) {
			mbf.setOriginalTitle(sb.toString());
		}
		if (AllocineConstants.TITLE.equals(qName)) {
			mbf.setTitle(sb.toString());
		}
		if (AllocineConstants.PRODUCTION_YEAR.equals(qName)) {
			try {
				mbf.setProductionYear(Long.parseLong(sb.toString()));
			} catch (NumberFormatException e) {
				logger.warn("Error with production year", e);
			}
		}
		if (AllocineConstants.RELEASE_DATE.equals(qName)) {
			mbf.setReleaseDate(sb.toString());
		}
        if(AllocineConstants.DIRECTORS_SHORT.equals(qName)) {
            for(String s : sb.toString().split(",")) {
                try {
                    pf.create();
                    pf.parseString(s.toString().trim());
                    mbf.addDirector(pf.validate());
                } catch (IllegalStateException e) {
                    logger.warn("Error while parsing a person name", e);
                }
            }
        }
        if(AllocineConstants.ACTORS_SHORT.equals(qName)) {
            for(String s : sb.toString().split(",")) {
                try {
                    pf.create();
                    pf.parseString(s.toString().trim());
                    mbf.addActor(pf.validate());
                } catch (IllegalStateException e) {
                    logger.warn("Error while parsing a person name", e);
                }
            }
        }
		if (AllocineConstants.PRESS_RATING.equals(qName)) {
			try {
				mbf.setPressRating(Long.parseLong(sb.toString()));
			} catch (NumberFormatException e) {
				logger.warn("Error with press rating", e);
			}
		}
		if (AllocineConstants.USER_RATING.equals(qName)) {
			try {
				mbf.setUsersRating(Long.parseLong(sb.toString()));
			} catch (NumberFormatException e) {
				logger.warn("Error with user rating", e);
			}
		}
		if (AllocineConstants.MOVIE.equals(qName)) {
			mbs.add(mbf.validate());
		}
	}
}