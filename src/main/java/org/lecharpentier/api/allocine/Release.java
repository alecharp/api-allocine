package org.lecharpentier.api.allocine;

import org.apache.log4j.Logger;
import org.codehaus.jackson.annotate.JsonProperty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class Release {
    private static final Logger logger = Logger.getLogger(Release.class.getName());
    @JsonProperty(value = "releaseDate")
    private String date;

    public Date getDate() {
        try {
            return new SimpleDateFormat().parse(date);
        } catch (ParseException e) {
            logger.warn("Exception while parsing " + date + " to java.util.Date.", e);
            return null;
        }
    }
}
