package org.lecharpentier.api.allocine;

import org.apache.log4j.Logger;

import java.lang.reflect.Method;

public enum SearchFilter {
    Movie {
        @Override
        public String getValue() {
            return "movie";
        }

        @Override
        public Method getResultGetter() {
            try {
                return SearchFeed.class.getMethod("getMovie", null);
            } catch (NoSuchMethodException e) {
                logger.warn("Error while accessing getMovie method on SearchFeed class.", e);
                return null;
            }
        }
    },
    TVSeries {
        @Override
        public String getValue() {
            return "tvseries";
        }

        @Override
        public Method getResultGetter() {
            try {
                return SearchFeed.class.getMethod("getTVSeries", null);
            } catch (NoSuchMethodException e) {
                logger.warn("Error while accessing getTVSeries method on SearchFeed class.", e);
                return null;
            }
        }
    };

    private static final Logger logger = Logger.getLogger(SearchFilter.class.getName());

    /**
     * Returns the value of the SearchFilter in Allocine API friendly format.
     *
     * @return the value of the SearchFilter in Allocine API friendly format.
     */
    abstract String getValue();

    /**
     * Returns the method which returns the list of elements of the filter.
     * The method must be one of {@link SearchFeed} class
     *
     * @return the method which returns the list of elements of the filter.
     */
    abstract Method getResultGetter();
}
