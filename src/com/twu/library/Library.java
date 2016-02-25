package com.twu.library;

import com.twu.actions.*;
import com.twu.library.titles.LibraryBook;
import com.twu.library.titles.LibraryMovie;
import com.twu.library.titles.MovieRating;
import com.twu.library.titles.Title;

import java.lang.reflect.Type;
import java.time.Year;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by Sreeja enabled 19/02/2016.
 */
public class Library {
	private static final String WELCOME_MESSAGE = "Welcome to Biblioteca. Application is now ready to use.\n";
	private static final String QUIT_MESSAGE = "You have successfully quit.\n";
	private static final String INVALID_OPTION_MESSAGE = "Select a valid option!\n";
	private static final String MOVIE_NOT_AVAILABLE = "That movie is not available.\n";
	private static final String BOOK_NOT_AVAILABLE = "That book is not available.\n";
	private static final String SUCCESSFUL_CHECKOUT_MOVIE = "Thank you! Enjoy the movie.\n";
	private static final String SUCCESSFUL_CHECKOUT_BOOK = "Thank you! Enjoy the book.\n";
	private static final String INVALID_RETURN_BOOK = "That is not a valid book to return.\n";
	private static final String INVALID_RETURN_MOVIE = "That is not a valid movie to return.\n";
	private static final String VALID_RETURN_BOOK = "Thank you for returning the book.\n";
	private static final String VALID_RETURN_MOVIE = "Thank you for returning the movie.\n";
	private static final String ENTER_A_TITLEID = "Please enter a title ID with your request\n";
	private static final String TITLE_NOT_FOUND = "Title not found.\n";

	private static final int LIST_BOOKS_ACTION = 1;
	private static final int CHECKOUT_BOOK_ACTION = 2;
	private static final int RETURN_BOOK_ACTION = 3;
	private static final int LIST_MOVIES_ACTION = 4;
	private static final int QUIT_BOOKS_ACTION = 99;

	private static Map<String, Title> libraryTitles = new HashMap<>();
	private static Map<Integer, LibraryAction> actionMapper = new HashMap<>();

	private boolean enabled;

	public Library(boolean enabled) {
		this.enabled = enabled;
		libraryTitles.put("HP", new LibraryBook("HP", "Harry Potter 1", Year.of(1991), "J.K Rowling", false));
		libraryTitles.put("HW", new LibraryBook("HW", "Henri's Walk to Paris", Year.of(1964), "Saul Bass", false));
		libraryTitles.put("TI", new LibraryMovie("TI", "Titanic", Year.of(1997), "James Cameron",
				MovieRating.toRating(3), false));
		libraryTitles.put("SH", new LibraryMovie("SH", "Shrek", Year.of(2001), "Andrew Adamson",
				MovieRating.toRating(8), false));
		libraryTitles.put("CO", new LibraryMovie("CO", "Cowspiracy", Year.of(2014), "Kip Andersen",
				MovieRating.toRating(9), false));
		actionMapper.put(LIST_BOOKS_ACTION, new ListTitlesAction<>(this, LibraryBook.class));
		actionMapper.put(CHECKOUT_BOOK_ACTION, new CheckoutTitleAction<>(this, LibraryBook.class));
		actionMapper.put(RETURN_BOOK_ACTION, new ReturnTitleAction<>(this, LibraryBook.class));
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
		return libraryTitles.values().stream()
				.filter(title -> title instanceof LibraryBook)
				.map(title -> (LibraryBook) title)
				.collect(Collectors.toList());
	}

	public List<LibraryMovie> getLibraryMovies() {
		return libraryTitles.values().stream()
				.filter(title -> title instanceof LibraryMovie)
				.map(title -> (LibraryMovie) title)
				.collect(Collectors.toList());
	}

	public <T extends Title> List<T> getTitlesByType(Type t) {
		return libraryTitles.values().stream()
				.filter(title -> title.getClass().equals(t))
				.map(title -> (T) title)
				.collect(Collectors.toList());
	}

	public String getQuitMessage() {
		return QUIT_MESSAGE;
	}

	public String getMenuList() {
		String menuList = "";
		for (Integer option : actionMapper.keySet()) {
			menuList += option + ") " + actionMapper.get(option).getActionDescription() + "\n";
		}

		return menuList;
	}

	public String getInvalidOptionMessage() {
		return INVALID_OPTION_MESSAGE;
	}

	public <T extends Title> String getUnavailableTitle(Class<T> type) {
		if (type.equals(LibraryBook.class)) {
			return BOOK_NOT_AVAILABLE;
		} else {
			return MOVIE_NOT_AVAILABLE;
		}
	}

	public <T extends Title> String getSuccessfulCheckout(Class<T> type) {
		if (type.equals(LibraryBook.class)) {
			return SUCCESSFUL_CHECKOUT_BOOK;
		} else {
			return SUCCESSFUL_CHECKOUT_MOVIE;
		}
	}

	public Title getLibraryTitleById(String titleId) {
		return libraryTitles.values()
				.stream()
				.filter(title -> title.getId().equalsIgnoreCase(titleId))
				.findAny()
				.orElse(null);
	}

	public void updateCheckoutStatus(String titleId, boolean isCheckedOut) {
		Title title = getLibraryTitleById(titleId);
		if (title != null) {
			title.setCheckedOut(isCheckedOut);
		}
	}

	public <T extends Title> String getInvalidReturn(Class<T> type) {
		if (type.equals(LibraryBook.class)) {
			return INVALID_RETURN_BOOK;
		} else {
			return INVALID_RETURN_MOVIE;
		}
	}

	public <T extends Title> String getValidReturn(Class<T> type) {
		if (type.equals(LibraryBook.class)) {
			return VALID_RETURN_BOOK;
		} else {
			return VALID_RETURN_MOVIE;
		}
	}

	public LibraryAction executeActionByInputCode(int option) {
		return (actionMapper.get(option) == null) ? new DisplayInvalidOptionAction(this) : actionMapper.get(option);
	}

	public String getTitleIdPrompt() {
		return ENTER_A_TITLEID;
	}

	public String getTitleNotFound() {
		return TITLE_NOT_FOUND;
	}
}
