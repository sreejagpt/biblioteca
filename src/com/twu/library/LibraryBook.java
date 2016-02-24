package com.twu.library;

import java.time.Year;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class LibraryBook {

	private String id;
	private String name;
	private String author;
	private Year yearOfPublication;
	private boolean isCheckedOut;

	public LibraryBook(String id, String name, Year yearOfPublication, String author, boolean isCheckedOut) {
		this.id = id;
		this.name = name;
		this.yearOfPublication = yearOfPublication;
		this.author = author;
		this.isCheckedOut = false;
	}

	public String getId() {
		return id;
	}

	public boolean isCheckedOut() {
		return isCheckedOut;
	}

	public void setCheckedOut(boolean checkedOut) {
		isCheckedOut = checkedOut;
	}

	@Override
	public String toString() {
		return "[" +
				"id='" + id + '\'' +
				", name='" + name + '\'' +
				", author='" + author + '\'' +
				", yearOfPublication=" + yearOfPublication +
				']';
	}

	@Override
	public boolean equals(Object book) {
		if (this == book) return true;
		if (!(book instanceof LibraryBook)) return false;

		LibraryBook that = (LibraryBook) book;

		if (isCheckedOut != that.isCheckedOut) return false;
		if (!id.equalsIgnoreCase(that.id)) return false;
		if (!name.equalsIgnoreCase(that.name)) return false;
		if (!author.equalsIgnoreCase(that.author)) return false;
		return yearOfPublication.equals(that.yearOfPublication);

	}

	@Override
	public int hashCode() {
		int result = id.hashCode();
		result = 31 * result + name.hashCode();
		result = 31 * result + author.hashCode();
		result = 31 * result + yearOfPublication.hashCode();
		result = 31 * result + (isCheckedOut ? 1 : 0);
		return result;
	}
}
