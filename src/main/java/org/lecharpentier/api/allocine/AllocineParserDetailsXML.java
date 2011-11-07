package org.lecharpentier.api.allocine;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.SAXOutputter;
import org.jdom.xpath.XPath;
import org.lecharpentier.api.allocine.MovieDetails.MovieDetailsFactory;
import org.lecharpentier.api.allocine.Person.PersonFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Adrien Lecharpentier
 */
class AllocineParserDetailsXML extends AllocineParserDetails {
    private static final String XMLNS = "http://www.allocine.net/v6/ns/";

    AllocineParserDetailsXML() {
    }

    @Override
    MovieDetails parse(String content) throws ParserConfigurationException, SAXException, IOException, JDOMException {
        MovieDetailsFactory mdf = new MovieDetailsFactory();

        SAXBuilder sxb = new SAXBuilder();
        Document doc = sxb.build(new StringReader(content));

        Element root = doc.getRootElement();
        XPath xpa = XPath.newInstance("//allocine:movie");
        xpa.addNamespace("allocine", XMLNS);

        Element movie = (Element) xpa.selectSingleNode(root);
        SAXOutputter output = new SAXOutputter(new DetailsHandler(mdf));
        output.output(movie);

        return mdf.validate();
    }

}

class DetailsHandler extends DefaultHandler {
    private final MovieDetailsFactory mdf;
    private final PersonFactory pf = new PersonFactory();
    private StringBuilder sb;
    private long activity;

    private final Logger logger = Logger.getAnonymousLogger();

    DetailsHandler(MovieDetailsFactory mdf) {
        this.mdf = mdf;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        sb = new StringBuilder();
        if (AllocineConstants.MOVIE.equals(qName)) {
            mdf.create();
            try {
                mdf.setFilmCode(Long.parseLong(attributes.getValue(AllocineConstants.CODE)));
            } catch (NumberFormatException e) {
                logger.log(Level.WARNING, "Error with film code");
            }
        }
        if (AllocineConstants.PERSON.equals(qName)) {
            pf.create();
            try {
                pf.setCode(Long.parseLong(attributes.getValue(AllocineConstants.CODE)));
            } catch (NumberFormatException e) {
                logger.log(Level.WARNING, "Error with person code");
            }
        }
        if (AllocineConstants.PICTURE.equals(qName)) {
            pf.setImg(attributes.getValue(AllocineConstants.HREF));
        }
        if (AllocineConstants.ACTIVITY.equals(qName)) {
            try {
                activity = Long.parseLong(attributes.getValue(AllocineConstants.CODE));
            } catch (NumberFormatException e) {
                activity = 0;
                logger.log(Level.WARNING, "Error with activity");
            }
        }
        if (AllocineConstants.POSTER.equals(qName)) {
            mdf.setPoster(attributes.getValue(AllocineConstants.HREF));
        }
        if (AllocineConstants.LINK.equals(qName)) {
            mdf.setLink(attributes.getValue(AllocineConstants.HREF));
        }
        if (AllocineConstants.TRAILER.equals(qName)) {
            mdf.setTrailer(attributes.getValue(AllocineConstants.HREF));
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        sb.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (AllocineConstants.MOVIE_TYPE.equals(qName)) {
            mdf.setMovieType(sb.toString());
        }
        if (AllocineConstants.ORIGINAL_TITLE.equals(qName)) {
            mdf.setOriginalTitle(sb.toString());
        }
        if (AllocineConstants.TITLE.equals(qName)) {
            mdf.setTitle(sb.toString());
        }
        if (AllocineConstants.PRODUCTION_YEAR.equals(qName)) {
            try {
                mdf.setProductionYear(Long.parseLong(sb.toString()));
            } catch (NumberFormatException e) {
                logger.log(Level.WARNING, "Error with production year");
            }
        }
        if (AllocineConstants.NATIONALITY.equals(qName)) {
            mdf.setNationality(sb.toString());
        }
        if (AllocineConstants.GENRE.equals(qName)) {
            mdf.addGenre(sb.toString());
        }
        if (AllocineConstants.RELEASE_DATE.equals(qName)) {
            mdf.setReleaseDate(sb.toString());
        }
        if (AllocineConstants.RUNTIME.equals(qName)) {
            try {
                mdf.setRuntime(Long.parseLong(sb.toString()));
            } catch (NumberFormatException e) {
                logger.log(Level.WARNING, "Error with runtime");
            }
        }
        if (AllocineConstants.SYNOPSIS.equals(qName)) {
            mdf.setSynopsis(sb.toString().trim().replaceAll("[\n\t ]+", " "));
        }
        if (AllocineConstants.PERSON.equals(qName)) {
            try {
                pf.parseString(sb.toString());
            } catch (IllegalStateException e) {
                logger.log(Level.WARNING, "Error while parsing a person name");
                logger.log(Level.FINER, "" + e);
            }
        }
        if (AllocineConstants.CAST_MEMBER.equals(qName)) {
            if (activity == AllocineConstants.ACTOR_CODE) {
                mdf.addActor(pf.validate());
            }
            if (activity == AllocineConstants.DIRECTOR_CODE) {
                mdf.addDirector(pf.validate());
            }
        }
        if (AllocineConstants.PRESS_RATING.equals(qName)) {
            try {
                mdf.putStatistic(qName, Long.parseLong(sb.toString()));
            } catch (NumberFormatException e) {
                logger.log(Level.WARNING, "Error with press rating");
            }
        }
        if (AllocineConstants.USER_RATING.equals(qName)) {
            try {
                mdf.putStatistic(qName, Long.parseLong(sb.toString()));
            } catch (NumberFormatException e) {
                logger.log(Level.WARNING, "Error with user rating");
            }
        }
    }
}