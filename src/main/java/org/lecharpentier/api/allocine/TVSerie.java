package org.lecharpentier.api.allocine;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TVSerie extends MediaInfo {

    @JsonProperty
    private Map<String, String> seriesType = new HashMap<String, String>();
    @JsonProperty
    private int yearStart;
    @JsonProperty
    private int yearEnd;
    @JsonProperty
    private Map<String, String> castingShort = new HashMap<String, String>();
    @JsonProperty
    private int seasonCount;
    @JsonProperty
    private int episodeCount;
    @JsonProperty
    private int formatType;
    @JsonProperty
    private Map<String, String> originalBroadcast = new HashMap<String, String>();
    @JsonProperty
    private List<Map<String, String>> genre = new ArrayList<Map<String, String>>();
    @JsonProperty
    private List<Map<String, String>> nationality = new ArrayList<Map<String, String>>();
    @JsonProperty
    private String synopsis;
    @JsonProperty
    private Map<String, String> trailer = new HashMap<String, String>();
    @JsonProperty
    private List<Season> season = new ArrayList<Season>();


    @Override
    public String getProductionYear() {
        StringBuilder sb = new StringBuilder(yearStart + "");
        if (yearEnd != 0) {
            sb.append(" - " + yearEnd);
        }
        return sb.toString();
    }

    @Override
    public String getType() {
        if (seriesType.get("$") == null)
            return SearchFilter.TVSeries.toString();
        return seriesType.get("$");
    }

    public Map<String, String> getCastingShort() {
        return castingShort;
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

    public String getTrailerHref() {
        return trailer.get("href");
    }

    public String getSynopsis() {
        return synopsis;
    }

    public List<Season> getSeasons() {
        return season;
    }

    public static class Season extends MediaInfo {

        private int seasonNumber;
        private int episodeCount;
        private int yearStart;
        private int yearEnd;
        private Map<String, String> parentSeries = new HashMap<String, String>();

        @Override
        public String getProductionYear() {
            StringBuilder sb = new StringBuilder(yearStart + "");
            if (yearEnd != 0) {
                sb.append(" - " + yearEnd);
            }
            return sb.toString();
        }

        @Override
        public String getType() {
            return "Season";
        }

        @Override
        public String getOriginalTitle() {
            return getParentSerieName();
        }

        @Override
        public String getPosterHref() {
            return null;
        }

        public int getSeasonNumber() {
            return seasonNumber;
        }

        public int getEpisodeCount() {
            return episodeCount;
        }

        public String getParentSerieName() {
            return parentSeries.get("name");
        }

        public int getParentSerieCode() {
            return Integer.parseInt(parentSeries.get("code"));
        }

        public static class Episode extends MediaInfo {

            @Override
            public String getProductionYear() {
                return null;
            }

            @Override
            public String getType() {
                return null;
            }
        }
    }
}
