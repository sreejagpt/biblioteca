package com.twu.library.titles;

/**
 * Created by Sreeja on 25/02/2016.
 */
public enum MovieRating {
	ONE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	UNRATED(-1);

	private final int value;

	MovieRating(int value) {
		this.value = value;
	}

	public static MovieRating toRating(int rating) {
		for (MovieRating movieRating : values()) {
			if (movieRating.getValue() == rating) {
				return movieRating;
			}
		}
		return null;
	}

	public int getValue() {
		return value;
	}
}
