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
}
