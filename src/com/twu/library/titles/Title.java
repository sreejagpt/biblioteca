package com.twu.library.titles;

import java.time.Year;

/**
 * Created by Sreeja on 25/02/2016.
 */
public abstract class Title {
	private final String id;
	private final String name;
	private final Year yearOfCreation;
	private boolean isCheckedOut;

	public Title(String id, String name, Year yearOfCreation, boolean isCheckedOut) {
		this.id = id;
		this.name = name;
		this.yearOfCreation = yearOfCreation;
		this.isCheckedOut = isCheckedOut;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Year getYearOfCreation() {
		return yearOfCreation;
	}

	public boolean isCheckedOut() {
		return isCheckedOut;
	}

	public void setCheckedOut(boolean checkedOut) {
		isCheckedOut = checkedOut;
	}

	public abstract String toString();

	public abstract boolean equals(Object title);

	public abstract int hashCode();

	public abstract String getInvalidReturnMessage();

	public abstract String getValidReturnMessage();

    public abstract String getInvalidCheckoutMessage();

    public abstract String getValidCheckoutMessage();
}
