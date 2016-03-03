package com.twu.library.titles;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by sreeja on 3/03/2016.
 */

public class MovieRatingTest {

    @Test
    public void shouldReturnCorrectMovieRatingGivenRating() {
        MovieRating movieRating = MovieRating.toRating(2);

        assertEquals(MovieRating.TWO, movieRating);
    }

    @Test
    public void shouldReturnNullGivenNonexistentRating() {
        MovieRating movieRating = MovieRating.toRating(100);

        assertNull(movieRating);
    }
}