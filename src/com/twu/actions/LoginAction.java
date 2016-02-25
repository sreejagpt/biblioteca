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
		if (args.length != 1 || !((String) args[0]).trim().contains(" ")) {
			return getLibrary().getLoginPrompt();
		}
		String libraryId = ((String) args[0]).trim().split(" ")[0];
		String password = ((String) args[0]).trim().split(" ")[1];

		if (getLibrary().isInLoginMode()) {
			return getLibrary().getAlreadyLoggedInMessage();
		}
		LibraryUser libraryUser = getLibrary().authenticateDetails(libraryId, password);
		if (libraryUser != null) {
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
