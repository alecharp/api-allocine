package org.lecharpentier.api.allocine;

import java.util.ArrayList;
import java.util.Iterator;

public class SearchFilterList extends ArrayList<SearchFilter> {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<SearchFilter> it = super.iterator();
        while (it.hasNext()) {
            sb.append(it.next().getValue());
            if (it.hasNext()) {
                sb.append(",");
            }
        }
        return sb.toString();
    }

    public SearchFilterList append(SearchFilter sf) {
        this.add(sf);
        return this;
    }
}
