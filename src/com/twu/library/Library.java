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
	private static final String ENTER_A_TITLEID = "Please enter a title ID with your request.\n";
	private static final String TITLE_NOT_FOUND = "Title not found.\n";
	private static final String LOGIN_PROMPT = "Please enter a Library Number and Password with your request.\n";
	private static final int LIST_BOOKS_ACTION = 1;
	private static final int CHECKOUT_BOOK_ACTION = 2;
	private static final int RETURN_BOOK_ACTION = 3;
	private static final int LIST_MOVIES_ACTION = 4;
	private static final int CHECKOUT_MOVIE_ACTION = 5;
	private static final int RETURN_MOVIE_ACTION = 6;
	private static final int QUIT_BOOKS_ACTION = 99;
	private static final int LOGIN_ACTION = 7;
	private static final int PRINT_USER_DETAILS_ACTION = 8;
	private static final String SUCCESSFUL_LOGIN_MESSAGE = "Welcome %s. You have now logged in.\n";
	private static final String ALREADY_LOGGED_IN = "User is already logged in.\n";
	private static final String UNSUCCESSFUL_LOGIN_MESSAGE = "Incorrect Library Number/Password Combination.\n";
	private static final String MUST_BE_LOGGED_IN = "You must be logged in to view user details.\n";
	private static final Map<String, Title> libraryTitles = new HashMap<>();
	private static final Map<Integer, LibraryAction> actionMapper = new HashMap<>();
	private static final Map<String, LibraryUser> userStore = new HashMap<>();
	private static final Map<String, String> passwordStore = new HashMap<>();
	private boolean enabled;
	private boolean loginMode;
	private LibraryUser currentUser;

	public Library(boolean enabled) {
		this.enabled = enabled;
		this.loginMode = false;
		this.currentUser = null;

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
		actionMapper.put(LIST_MOVIES_ACTION, new ListTitlesAction<>(this, LibraryMovie.class));
		actionMapper.put(CHECKOUT_MOVIE_ACTION, new CheckoutTitleAction<>(this, LibraryMovie.class));
		actionMapper.put(RETURN_MOVIE_ACTION, new ReturnTitleAction<>(this, LibraryMovie.class));
		actionMapper.put(QUIT_BOOKS_ACTION, new QuitAction(this));
		actionMapper.put(LOGIN_ACTION, new LoginAction(this));
		actionMapper.put(PRINT_USER_DETAILS_ACTION, new PrintUserDetailsAction(this));

		userStore.put("123-4567", new LibraryUser("123-4567", "Sreeja", "sreeja@email.com", "23452345"));
		userStore.put("000-1234", new LibraryUser("000-1234", "Nicholas", "nicky@nicholas.com", "45662222"));
		userStore.put("111-1334", new LibraryUser("111-1334", "Joan", "joan@nicholas.com", "45664646"));

		passwordStore.put("123-4567", "password1");
		passwordStore.put("000-1234", "password2");
		passwordStore.put("111-1334", "password3");

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
			LibraryAction action = actionMapper.get(option);
			if (action.onlyAvailableWhenLoggedIn() && !isInLoginMode()) {
				continue;
			}
			if (isInLoginMode() && action instanceof LoginAction) {
				continue;
			}
			menuList += option + ") " + action.getActionDescription() + "\n";
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

	public boolean isInLoginMode() {
		return loginMode;
	}

	public void setLoginMode(boolean userLoggedIn) {
		this.loginMode = userLoggedIn;
	}

	public String getSuccessfulLoginMessage() {
		return String.format(SUCCESSFUL_LOGIN_MESSAGE, currentUser.getName());
	}

	public String getAlreadyLoggedInMessage() {
		return ALREADY_LOGGED_IN;
	}

	public String getLoginPrompt() {
		return LOGIN_PROMPT;
	}

	public String getUnsuccessfulLoginMessage() {
		return UNSUCCESSFUL_LOGIN_MESSAGE;
	}

	public LibraryUser authenticateDetails(String libraryId, String password) {
		LibraryUser user = userStore.get(libraryId);
		if (user != null && passwordStore.get(libraryId).equals(password)) {
			loginMode = true;
			currentUser = user;
			return user;
		}
		return null;
	}

	public LibraryUser getCurrentUser() {
		return currentUser;
	}

	public String getInvalidPrintCommandPrompt() {
		return MUST_BE_LOGGED_IN;
	}
}
