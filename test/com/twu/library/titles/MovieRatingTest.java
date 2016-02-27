package com.twu.library.titles;

import org.junit.Test;

import static org.junit.Assert.*;

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