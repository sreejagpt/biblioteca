package com.twu.library;

import java.time.Year;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class BibliotecaModel {
	private boolean on;

	private static final List<LibraryBook> libraryBooks = Arrays.asList(
			new LibraryBook("HP", "Harry Potter 1", Year.of(1991), "J.K Rowling"),
			new LibraryBook("HW", "Henri's Walk to Paris", Year.of(1964),  "Saul Bass"));

	private static final String WELCOME_MESSAGE = "Welcome to Bibioteca. Application is now ready to use.\n";
	private static final String QUIT_MESSAGE = "You have successfully quit.\n";
	private static final String MENU_LIST = "1) List Books\n2) Quit\n";
	private static final String INVALID_OPTION_MESSAGE = "Select a valid option!\n";

	public BibliotecaModel(boolean on) {
		this.on = on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}

	public boolean isOn() {
		return on;
	}

	public String getWelcomeMessage() {
		return WELCOME_MESSAGE;
	}

	public List<LibraryBook> getLibraryBooks() {
		return libraryBooks;
	}

	public String getQuitMessage() {
		return QUIT_MESSAGE;
	}

	public String getMenuList() {
		return MENU_LIST;
	}

	public String getInvalidOptionMessage() {
		return INVALID_OPTION_MESSAGE;
	}
}
