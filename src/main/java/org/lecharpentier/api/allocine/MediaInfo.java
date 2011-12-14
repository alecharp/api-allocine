package org.lecharpentier.api.allocine;

import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MediaInfo {
    @JsonProperty
    private long code;
    @JsonProperty
    private String originalTitle;
    @JsonProperty
    private Map<String, String> poster = new HashMap<String, String>();
    @JsonProperty
    private List<Map<String, String>> link = new ArrayList<Map<String, String>>();
    @JsonProperty
    private Statistics statistics = new Statistics();

    public long getCode() {
        return code;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getPosterHref() {
        return poster.get("href");
    }

    public List<String> getLinkHrefs() {
        List<String> links = new ArrayList<String>();
        for (Map<String, String> m : link) {
            links.add(m.get("href"));
        }
        return links;
    }

    public abstract String getProductionYear();

    public abstract String getType();
}
