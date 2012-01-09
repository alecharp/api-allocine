package org.lecharpentier.api.allocine;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SearchEngineTest {
    private SearchEngine searchEngine;

    @Before
    public void before() {
        searchEngine = new SearchEngine();
    }

    @Test(timeout = 1000)
    public void testSearchMovieWithBadCode() throws Exception {
        Assert.assertNull(searchEngine.movie(-1));
    }

    @Test(timeout = 1000)
    public void testSearchMovieWithInvictusCode() throws Exception {
        Assert.assertNotNull(searchEngine.movie(129694));
    }

    @Test(timeout = 1000)
    public void testSearchTVSerieWithBadCode() throws Exception {
        Assert.assertNull(searchEngine.tvserie(-1));
    }

    @Test(timeout = 1000)
    public void testSearchTVSerieWithBonesCode() throws Exception {
        Assert.assertNotNull(searchEngine.tvserie(452));
    }
}
