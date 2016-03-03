package com.twu.library;

import com.twu.actions.DisplayInvalidOptionAction;
import com.twu.actions.LibraryAction;
import com.twu.actions.LoginAction;
import com.twu.library.titles.Title;
import data.Actions;
import data.Constants;
import data.LibraryArchive;
import data.UserBase;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Sreeja enabled 19/02/2016.
 */
public class Library {
    private LibraryArchive archive;
    private Actions actions;
    private boolean enabled;
	private boolean loginMode;
	private LibraryUser currentUser;
    private UserBase userBase;

	public Library(boolean enabled, UserBase userBase, Actions actions, LibraryArchive archive) {
		this.enabled = enabled;
		this.loginMode = false;
		this.currentUser = null;
        this.archive = archive;
        this.actions = actions;
        this.userBase = userBase;
    }

    public Library() {
        this.enabled = true;
        this.loginMode = false;
        this.currentUser = null;
        this.archive = new LibraryArchive();
        this.actions = new Actions();
        this.userBase = new UserBase();
    }

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	private <T extends Title> List<T> getTitlesByType(Type t) {
		return archive.getLibraryTitles().values().stream()
				.filter(title -> title.getClass().equals(t))
				.map(title -> (T) title)
				.collect(Collectors.toList());
	}


	public String getMenuList(boolean isLoggedIn) {
		String menuList = "";
		for (Integer option : actions.getActions().keySet()) {
			LibraryAction action = actions.getActions().get(option);
			if (action.onlyAvailableWhenLoggedIn() && !isLoggedIn) {
				continue;
			}
			if (isLoggedIn && action instanceof LoginAction) {
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
		return archive.getLibraryTitles().values()
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

	public LibraryAction findActionByInputCode(int option) {
		return (actions.getActions().get(option) == null) ? new DisplayInvalidOptionAction() : actions.getActions()
                .get(option);
	}

	public boolean isInLoginMode() {
		return loginMode;
	}

	public LibraryUser authenticateDetails(String libraryId, String password) {
		LibraryUser user = userBase.getUserById(libraryId);
		if (user != null && userBase.doesPasswordMatch(user.getLibraryId(), password)) {
			loginMode = true;
			currentUser = user;
			return currentUser;
		}
		return null;
	}

	public LibraryUser getCurrentUser() {
		return currentUser;
	}

    public String getInvalidOptionMessage() {
        return Constants.INVALID_OPTION_MESSAGE;
    }

    public String getWelcomeMessage() {
        return Constants.WELCOME_MESSAGE;
    }

    public String getEnterATitleIdMessage() {
        return Constants.ENTER_A_TITLEID;
    }

    public String getTitleNotFoundMessage() {
        return Constants.TITLE_NOT_FOUND;
    }

    public String getPreLoginMenuList() {
        return getMenuList(false);
    }

    public String getPostLoginMenuList() {
        return getMenuList(true);
    }

    public <T extends Title> List<T> getUncheckedOutTitlesOfType(Class<T> type) {
        return archive.getLibraryTitles().values().stream()
                .filter(title -> title.getClass().equals(type))
                .filter(title -> !title.isCheckedOut())
                .map(title -> (T) title)
                .collect(Collectors.toList());    }

    public String getLoginPromptMessage() {
        return Constants.LOGIN_PROMPT;
    }

    public String getSuccessfulLoginMessage() {
        return String.format(Constants.SUCCESSFUL_LOGIN_MESSAGE, currentUser.getName());
    }

    public String getUserAlreadyLoggedInMessage() {
        return Constants.ALREADY_LOGGED_IN;
    }

    public String getFaultyCredentialsMessage() {
        return Constants.UNSUCCESSFUL_LOGIN_MESSAGE;
    }

    public String getMustBeLoggedInMessage() {
        return Constants.MUST_BE_LOGGED_IN;
    }

    public String getSuccessfulQuitMessage() {
        return Constants.QUIT_MESSAGE;
    }
}
