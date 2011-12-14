package org.lecharpentier.api.allocine;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.util.URIUtil;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public final class SearchEngine {
    private final Logger logger = Logger.getLogger(SearchEngine.class.getName());

    private static final String BASE_URL = "http://api.allocine.fr/rest/v3/";
    private static final String PARTNER = "partner=YW5kcm9pZC12M3M";
    private static final String FORMAT = "format=json";

    private final ObjectMapper mapper;
    private final boolean retry;

    /**
     * Instantiate a default SearchEngine with automatic retry
     * policy if the first attempt only gets a part of the results
     */
    public SearchEngine() {
        this(true);
    }

    /**
     * @param retry if true, if the query does not retrieve all elements, because of paging, then retry the query
     *              with correct parameter to retrieve all results. If false, does not do the second query
     */
    public SearchEngine(boolean retry) {
        mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.retry = retry;
    }

    public List<MediaInfo> search(String query) throws URIException, HTTPException, IOException {
        return search(query, new SearchFilterList());
    }

    public List<MediaInfo> search(String query, SearchFilterList filters) throws URIException, HTTPException,
            IOException {
        HttpClient client = new HttpClient();
        String search = URIUtil.encodeWithinAuthority(query);
        HttpMethod meth = new GetMethod(BASE_URL + "search?" + PARTNER + "&" + FORMAT +
                "&filter=" + filters + "&q=" + search + "&profile=large");

        client.executeMethod(meth);
        if (meth.getStatusCode() != HttpStatus.SC_OK) {
            throw new IllegalStateException("The HTTP request went bad..");
        }
        RootSearch root = mapper.readValue(meth.getResponseBodyAsStream(), RootSearch.class);
        if (retry) {
            int count = 0;
            for (SearchFilter sf : filters) {
                try {
                    count += ((List) sf.getResultGetter().invoke(root.getFeed(), null)).size();
                } catch (IllegalAccessException e) {
                    logger.warn("", e);
                } catch (InvocationTargetException e) {
                    logger.warn("", e);
                }
            }
            if (count != root.getFeed().getTotalResults()) {
                logger.debug("Some results are missing");
                meth = new GetMethod(BASE_URL + "search?" + PARTNER + "&" + FORMAT + "&filter=" + filters +
                        "&q=" + search + "&profile=large&count=" + root.getFeed().getTotalResults());
                client.executeMethod(meth);
                root = mapper.readValue(meth.getResponseBodyAsStream(), RootSearch.class);
            }
        }
        return root.getFeed().getAllResults();
    }

    public Movie movie(int code) throws HTTPException, IOException {
        HttpClient client = new HttpClient();
        HttpMethod meth = new GetMethod(BASE_URL + "movie?" + PARTNER + "&" + FORMAT +
                "&code=" + code);

        client.executeMethod(meth);
        if (meth.getStatusCode() != HttpStatus.SC_OK) {
            throw new IllegalStateException("The HTTP request went bad..");
        }
        return mapper.readValue(meth.getResponseBodyAsStream(), RootMovie.class).getMovie();
    }

    public TVSerie tvserie(int code) throws HTTPException, IOException {
        HttpClient client = new HttpClient();
        HttpMethod meth = new GetMethod(BASE_URL + "tvseries?" + PARTNER + "&" + FORMAT +
                "&code=" + code + "&profile=large");

        client.executeMethod(meth);
        if (meth.getStatusCode() != HttpStatus.SC_OK) {
            throw new IllegalStateException("The HTTP request went bad..");
        }
        return mapper.readValue(meth.getResponseBodyAsStream(), RootTVSerie.class).getTVSerie();
    }

    public TVSerie.Season season(int code) throws HTTPException, IOException {
        HttpClient client = new HttpClient();
        HttpMethod meth = new GetMethod(BASE_URL + "season?" + PARTNER + "&" + FORMAT +
                "&code=" + code + "&profile=large");

        client.executeMethod(meth);
        if (meth.getStatusCode() != HttpStatus.SC_OK) {
            throw new IllegalStateException("The HTTP request went bad..");
        }
        return mapper.readValue(meth.getResponseBodyAsStream(), RootTVSerieSeason.class).getSeason();
    }

    public TVSerie.Season.Episode episode(int code) throws HTTPException, IOException {
        HttpClient client = new HttpClient();
        HttpMethod meth = new GetMethod(BASE_URL + "season?" + PARTNER + "&" + FORMAT +
                "&code=" + code + "&profile=large");

        client.executeMethod(meth);
        if (meth.getStatusCode() != HttpStatus.SC_OK) {
            throw new IllegalStateException("The HTTP request went bad..");
        }
        return mapper.readValue(meth.getResponseBodyAsStream(), RootTVSerieSeasonEpisode.class).getEpisode();
    }
}
