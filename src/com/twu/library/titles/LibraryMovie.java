package com.twu.library.titles;

import data.Messages;

import java.time.Year;

/**
 * Created by Sreeja on 25/02/2016.
 */
public class LibraryMovie extends Title {

	private final String director;
	private final MovieRating rating;

	public LibraryMovie(String id, String name, Year yearOfCreation, String director, MovieRating rating, boolean
			isCheckedOut) {
		super(id, name, yearOfCreation, isCheckedOut);
		this.director = director;
		this.rating = rating;
	}

	@Override
	public String toString() {
		return "[" +
				"id='" + getId() + '\'' +
				", name='" + getName() + '\'' +
				", director='" + director + '\'' +
				", rating='" + rating.getValue() + "" + '\'' +
				", yearOfRelease=" + getYearOfCreation() +
				']';
	}

	@Override
	public boolean equals(Object book) {
		if (this == book) return true;
		if (!(book instanceof LibraryMovie)) return false;

		LibraryMovie that = (LibraryMovie) book;

		if (isCheckedOut() != that.isCheckedOut()) return false;
		if (!getId().equalsIgnoreCase(that.getId())) return false;
		if (!getName().equalsIgnoreCase(that.getName())) return false;
		if (!director.equalsIgnoreCase(that.director)) return false;
		if (!rating.equals(that.rating)) return false;
		return getYearOfCreation().equals(that.getYearOfCreation());

	}

	@Override
	public int hashCode() {
		int result = getId().hashCode();
		result = 31 * result + getName().hashCode();
		result = 31 * result + director.hashCode();
		result = 31 * result + rating.hashCode();
		result = 31 * result + getYearOfCreation().hashCode();
		result = 31 * result + (isCheckedOut() ? 1 : 0);
		return result;
	}

	@Override
	public String getInvalidReturnMessage() {
		return Messages.INVALID_RETURN_MOVIE;
	}

	@Override
	public String getValidReturnMessage() {
		return Messages.VALID_RETURN_MOVIE;
	}

	@Override
	public String getInvalidCheckoutMessage() {
		return Messages.MOVIE_NOT_AVAILABLE;
	}

	@Override
	public String getValidCheckoutMessage() {
		return Messages.SUCCESSFUL_CHECKOUT_MOVIE;
	}
}
