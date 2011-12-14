package org.lecharpentier.api.allocine;

import java.util.Comparator;

public class MediaInfoComparatorRelease implements Comparator<MediaInfo> {
    private final int order;


    public MediaInfoComparatorRelease() {
        this(true);
    }

    /**
     * @param descending true to order MediaInfo items in most recent first order
     */
    public MediaInfoComparatorRelease(boolean descending) {
        if (descending)
            order = -1;
        else
            order = 1;
    }

    @Override
    public int compare(MediaInfo movieBrief, MediaInfo movieBrief1) {
        int c = movieBrief.getType().compareTo(movieBrief1.getType());
        if (c != 0) {
            return c;
        }
        if (movieBrief.getProductionYear() == null)
            return 1;
        if (movieBrief1.getProductionYear() == null)
            return -1;
        c = order * movieBrief.getProductionYear().compareTo(movieBrief1.getProductionYear());
        return c == 0 ? movieBrief.getOriginalTitle().compareTo(movieBrief1.getOriginalTitle()) : c;
    }
}
