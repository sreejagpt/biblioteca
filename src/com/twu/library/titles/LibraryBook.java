package com.twu.library.titles;

import data.Constants;

import java.time.Year;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class LibraryBook extends Title {

	private final String author;

	public LibraryBook(String id, String name, Year yearOfPublication, String author, boolean isCheckedOut) {
		super(id, name, yearOfPublication, isCheckedOut);
		this.author = author;
	}

	@Override
	public String toString() {
		return "[" +
				"id='" + getId() + '\'' +
				", name='" + getName() + '\'' +
				", author='" + author + '\'' +
				", yearOfPublication=" + getYearOfCreation() +
				']';
	}

	@Override
	public boolean equals(Object book) {
		if (this == book) return true;
		if (!(book instanceof LibraryBook)) return false;

		LibraryBook that = (LibraryBook) book;

		if (isCheckedOut() != that.isCheckedOut()) return false;
		if (!getId().equalsIgnoreCase(that.getId())) return false;
		if (!getName().equalsIgnoreCase(that.getName())) return false;
		if (!author.equalsIgnoreCase(that.author)) return false;
		return getYearOfCreation().equals(that.getYearOfCreation());

	}

	@Override
	public int hashCode() {
		int result = getId().hashCode();
		result = 31 * result + getName().hashCode();
		result = 31 * result + author.hashCode();
		result = 31 * result + getYearOfCreation().hashCode();
		result = 31 * result + (isCheckedOut() ? 1 : 0);
		return result;
	}

	@Override
	public String getInvalidReturnMessage() {
		return Constants.INVALID_RETURN_BOOK;
	}

	@Override
	public String getValidReturnMessage() {
		return Constants.VALID_RETURN_BOOK;
	}

    @Override
    public String getInvalidCheckoutMessage() {
        return Constants.BOOK_NOT_AVAILABLE;
    }

    @Override
    public String getValidCheckoutMessage() {
        return Constants.SUCCESSFUL_CHECKOUT_BOOK;
    }
}
