package com.twu.actions;

import com.twu.library.Constants;
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
			return Constants.LOGIN_PROMPT;
		}
		String libraryId = ((String) args[0]).trim().split(" ")[0];
		String password = ((String) args[0]).trim().split(" ")[1];

		if (getLibrary().isInLoginMode()) {
			return Constants.ALREADY_LOGGED_IN;
		}
		LibraryUser libraryUser = getLibrary().authenticateDetails(libraryId, password);
		if (libraryUser != null) {
            return String.format(Constants.SUCCESSFUL_LOGIN_MESSAGE, libraryUser.getName());
		}

		return Constants.UNSUCCESSFUL_LOGIN_MESSAGE;
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
