package com.twu.library;

import java.time.Year;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class BibliotecaModel {
	private static final List<LibraryBook> libraryBooks = Arrays.asList(
			new LibraryBook("HP", "Harry Potter 1", Year.of(1991), "J.K Rowling", false),
			new LibraryBook("HW", "Henri's Walk to Paris", Year.of(1964), "Saul Bass", false));
	private static final String WELCOME_MESSAGE = "Welcome to Biblioteca. Application is now ready to use.\n";
	private static final String QUIT_MESSAGE = "You have successfully quit.\n";
	private static final String MENU_LIST = "1) List Books\n2) Quit\n";
	private static final String INVALID_OPTION_MESSAGE = "Select a valid option!\n";
	private static final String NOT_VALID_BOOK = "That is not a valid book to return.\n";
	private static final String SUCCESSFUL_CHECKOUT = "Thank you! Enjoy the book.\n";
	private boolean on;

	public BibliotecaModel(boolean on) {
		this.on = on;
	}

	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
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

	public String getNotValidBook() {
		return NOT_VALID_BOOK;
	}

	public String getSuccessfulCheckout() {
		return SUCCESSFUL_CHECKOUT;
	}

	public LibraryBook findLibraryBookById(String bookId) {
		return libraryBooks.stream()
				.filter(libraryBook -> libraryBook.getId().equalsIgnoreCase(bookId))
				.findAny()
				.orElse(null);
	}

	public void updateCheckoutStatus(String bookId, boolean isCheckedOut) {
		LibraryBook book = findLibraryBookById(bookId);
		if (book != null) {
			book.setCheckedOut(isCheckedOut);
		}
	}
}
