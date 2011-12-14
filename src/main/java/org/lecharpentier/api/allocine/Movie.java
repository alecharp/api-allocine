package org.lecharpentier.api.allocine;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.*;

public final class Movie extends MediaInfo {

    @JsonProperty
    private Map<String, String> movieType = new HashMap<String, String>();
    @JsonProperty
    private String title;
    @JsonProperty
    private int productionYear;
    @JsonProperty
    private List<Map<String, String>> nationality = new ArrayList<Map<String, String>>();
    @JsonProperty
    private List<Map<String, String>> genre = new ArrayList<Map<String, String>>();
    @JsonProperty
    private Release release = new Release();
    @JsonProperty
    private Map<String, String> castingShort = new HashMap<String, String>();
    @JsonProperty
    private Map<String, Map<String, String>> movieCertificate = new HashMap<String, Map<String, String>>();
    @JsonProperty
    private long runtime;
    @JsonProperty
    private Map<String, String> trailer = new HashMap<String, String>();
    @JsonProperty
    private String synopsis;

    public Date getRelease() {
        return release.getDate();
    }

    @Override
    public String getType() {
        if (movieType.get("$") == null)
            return SearchFilter.Movie.toString();
        return movieType.get("$");
    }

    public String getTitle() {
        if (title == null) return getOriginalTitle();
        return title;
    }

    @Override
    public String getProductionYear() {
        return "" + productionYear;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public List<String> getNationalities() {
        List<String> nat = new ArrayList<String>();
        for (Map<String, String> e : nationality) {
            nat.add(e.get("$"));
        }
        return nat;
    }

    public List<String> getGenres() {
        List<String> nat = new ArrayList<String>();
        for (Map<String, String> e : genre) {
            nat.add(e.get("$"));
        }
        return nat;
    }

    public Map<String, String> getCastingShort() {
        return castingShort;
    }

    public Map<String, Map<String, String>> getMovieCertificate() {
        return movieCertificate;
    }

    public long getRuntime() {
        return runtime;
    }

    public String getTrailerHref() {
        return trailer.get("href");
    }
}
