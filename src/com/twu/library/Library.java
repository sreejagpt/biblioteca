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

		actionMapper.put(Constants.LIST_BOOKS_ACTION, new ListTitlesAction<>(this, LibraryBook.class));
		actionMapper.put(Constants.CHECKOUT_BOOK_ACTION, new CheckoutTitleAction<>(this, LibraryBook.class));
		actionMapper.put(Constants.RETURN_BOOK_ACTION, new ReturnTitleAction<>(this, LibraryBook.class));
		actionMapper.put(Constants.LIST_MOVIES_ACTION, new ListTitlesAction<>(this, LibraryMovie.class));
		actionMapper.put(Constants.CHECKOUT_MOVIE_ACTION, new CheckoutTitleAction<>(this, LibraryMovie.class));
		actionMapper.put(Constants.RETURN_MOVIE_ACTION, new ReturnTitleAction<>(this, LibraryMovie.class));
		actionMapper.put(Constants.QUIT_BOOKS_ACTION, new QuitAction(this));
		actionMapper.put(Constants.LOGIN_ACTION, new LoginAction(this));
		actionMapper.put(Constants.PRINT_USER_DETAILS_ACTION, new PrintUserDetailsAction(this));

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

	public <T extends Title> List<T> getTitlesByType(Type t) {
		return libraryTitles.values().stream()
				.filter(title -> title.getClass().equals(t))
				.map(title -> (T) title)
				.collect(Collectors.toList());
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

	public String getUnavailableTitle(Title title) {
		return title.getInvalidCheckoutMessage();
	}

	public String getSuccessfulCheckout(Title title) {
		return title.getValidCheckoutMessage();
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

	public String getInvalidReturn(Title title) {
		return title.getInvalidReturnMessage();
	}

	public String getValidReturn(Title title) {
		return title.getValidReturnMessage();
	}

	public LibraryAction executeActionByInputCode(int option) {
		return (actionMapper.get(option) == null) ? new DisplayInvalidOptionAction(this) : actionMapper.get(option);
	}

	public boolean isInLoginMode() {
		return loginMode;
	}

	public void setLoginMode(boolean userLoggedIn) {
		this.loginMode = userLoggedIn;
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

}
