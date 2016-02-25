package com.twu.library;

/**
 * Created by Sreeja on 25/02/2016.
 */
public class LibraryUser {
	private String libraryId;
	private String name;
	private String email;
	private String phoneNumber;

	public LibraryUser(String libraryId, String name, String email, String phoneNumber) {
		this.libraryId = libraryId;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public String getLibraryId() {
		return libraryId;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof LibraryUser)) return false;

		LibraryUser that = (LibraryUser) o;

		if (!getLibraryId().equalsIgnoreCase(that.getLibraryId())) return false;
		if (!getName().equalsIgnoreCase(that.getName())) return false;
		if (getEmail() != null ? !getEmail().equalsIgnoreCase(that.getEmail()) : that.getEmail() != null) return false;
		return !(getPhoneNumber() != null ? !getPhoneNumber().equalsIgnoreCase(that.getPhoneNumber()) : that.getPhoneNumber()
				!= null);

	}

	@Override
	public int hashCode() {
		int result = getLibraryId().hashCode();
		result = 31 * result + getName().hashCode();
		result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
		result = 31 * result + (getPhoneNumber() != null ? getPhoneNumber().hashCode() : 0);
		return result;
	}
}
