package org.lecharpentier.api.allocine;

import org.codehaus.jackson.annotate.JsonProperty;

public class Statistics {
    @JsonProperty(value = "pressRating")
    private double press;
    @JsonProperty(value = "userRating")
    private double user;

    public double getPressRating() {
        return press;
    }

    public double getUserRating() {
        return user;
    }
}
