package com.twu.actions;

import com.twu.library.Library;
import com.twu.library.LibraryUser;

/**
 * Created by Sreeja on 25/02/2016.
 */
public class LoginAction extends LibraryAction {
	public LoginAction(Library library) {
		super(library);
	}

	@Override
	public String execute(Object... args) {
		if (args.length != 2) {
			return getLibrary().getLoginPrompt();
		}
		String libraryId = ((String) args[0]).trim();
		String password = ((String) args[1]).trim();
		if (getLibrary().isInLoginMode()) {
			return getLibrary().getAlreadyLoggedInMessage();
		}
		LibraryUser libraryUser = getLibrary().authenticateDetails(libraryId, password);
		if (libraryUser != null) {
			getLibrary().setLoginMode(true);
			getLibrary().setCurrentUser(libraryUser);
			return getLibrary().getSuccessfulLoginMessage();
		}

		return getLibrary().getUnsuccessfulLoginMessage();
	}

	@Override
	public String getActionDescription() {
		return "Login [LIBRARY_NUMBER] [PASSWORD]";
	}

	@Override
	public boolean onlyAvailableWhenLoggedIn() {
		return false;
	}
}
