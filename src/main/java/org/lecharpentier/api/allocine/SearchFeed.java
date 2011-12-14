package org.lecharpentier.api.allocine;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

final class SearchFeed {
    @JsonProperty
    private int totalResults;
    @JsonProperty
    private int page;
    @JsonProperty
    private int count;

    @JsonProperty
    private List<Movie> movie = new ArrayList<Movie>();
    @JsonProperty
    private List<Map<String, String>> results = new ArrayList<Map<String, String>>();
    @JsonProperty
    private List<TVSerie> tvseries = new ArrayList<TVSerie>();

    public int getTotalResults() {
        return totalResults;
    }

    public int getPage() {
        return page;
    }

    public int getCount() {
        return count;
    }

    public List<Movie> getMovie() {
        return movie;
    }

    public List<TVSerie> getTVSeries() {
        return tvseries;
    }

    public List<MediaInfo> getAllResults() {
        List<MediaInfo> results = new ArrayList<MediaInfo>(movie);
        results.addAll(tvseries);
        return results;
    }
}
