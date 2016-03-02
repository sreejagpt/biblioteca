package com.twu.library;

import com.twu.actions.DisplayInvalidOptionAction;
import com.twu.actions.LibraryAction;
import com.twu.actions.LoginAction;
import com.twu.library.titles.LibraryBook;
import com.twu.library.titles.LibraryMovie;
import com.twu.library.titles.MovieRating;
import com.twu.library.titles.Title;
import data.Actions;
import data.Users;

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

        Actions.mapActions(this);
        Users.mapUsersAndPasswords();
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
		for (Integer option : Actions.getActions().keySet()) {
			LibraryAction action = Actions.getActions().get(option);
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
		return (Actions.getActions().get(option) == null) ? new DisplayInvalidOptionAction(this) : Actions.getActions().get(option);
	}

	public boolean isInLoginMode() {
		return loginMode;
	}

	public void setLoginMode(boolean userLoggedIn) {
		this.loginMode = userLoggedIn;
	}


	public LibraryUser authenticateDetails(String libraryId, String password) {
		LibraryUser user = Users.getUserById(libraryId);
		if (user != null && Users.doesPasswordMatch(user.getLibraryId(), password)) {
			loginMode = true;
			currentUser = user;
			return currentUser;
		}
		return null;
	}

	public LibraryUser getCurrentUser() {
		return currentUser;
	}

}
