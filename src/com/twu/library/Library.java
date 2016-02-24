package com.twu.library;

import com.twu.actions.*;

import java.time.Year;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sreeja enabled 19/02/2016.
 */
public class Library {
	private static final List<LibraryBook> libraryBooks = Arrays.asList(
			new LibraryBook("HP", "Harry Potter 1", Year.of(1991), "J.K Rowling", false),
			new LibraryBook("HW", "Henri's Walk to Paris", Year.of(1964), "Saul Bass", false));

	private static final String WELCOME_MESSAGE = "Welcome to Biblioteca. Application is now ready to use.\n";
	private static final String QUIT_MESSAGE = "You have successfully quit.\n";
	private static final String MENU_LIST = "1) List Books\n2) Checkout Book [ID]\n3) Return Book [ID]\n99) Quit\n";
	private static final String INVALID_OPTION_MESSAGE = "Select a valid option!\n";
	private static final String BOOK_NOT_AVAILABLE = "That book is not available.\n";
	private static final String SUCCESSFUL_CHECKOUT = "Thank you! Enjoy the book.\n";
	private static final String INVALID_RETURN = "That is not a valid book to return.\n";
	private static final String VALID_RETURN = "Thank you for returning the book.\n";
	private static final String ENTER_A_BOOKID = "Please enter a book ID with your request\n";

	private static final int LIST_BOOKS_ACTION = 1;
	private static final int CHECKOUT_BOOK_ACTION = 2;
	private static final int RETURN_BOOK_ACTION = 3;
	private static final int QUIT_BOOKS_ACTION = 99;

	private static Map<Integer, LibraryAction> actionMapper = new HashMap<>();
	private boolean enabled;

	public Library(boolean enabled) {
		this.enabled = enabled;
		actionMapper.put(LIST_BOOKS_ACTION, new ListBooksAction(this));
		actionMapper.put(CHECKOUT_BOOK_ACTION, new CheckoutBookAction(this));
		actionMapper.put(RETURN_BOOK_ACTION, new ReturnBookAction(this));
		actionMapper.put(QUIT_BOOKS_ACTION, new QuitAction(this));
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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

	public String getUnavailableBook() {
		return BOOK_NOT_AVAILABLE;
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

	public String getInvalidReturn() {
		return INVALID_RETURN;
	}

	public String getValidReturn() {
		return VALID_RETURN;
	}

	public LibraryAction executeActionByInputCode(int option) {
		return (actionMapper.get(option) == null) ? new DisplayInvalidOptionAction(this) : actionMapper.get(option);
	}

	public String getBookIdPrompt() {
		return ENTER_A_BOOKID;
	}
}
