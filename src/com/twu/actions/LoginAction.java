package com.twu.actions;

import data.Messages;
import com.twu.library.Library;
import com.twu.library.LibraryUser;

/**
 * Created by Sreeja on 25/02/2016.
 */
public class LoginAction extends LibraryAction {
	public LoginAction() {
		super();
	}

	@Override
	public String execute(Library library, Object... args) {
		if (args.length != 1 || !((String) args[0]).trim().contains(" ")) {
			return Messages.LOGIN_PROMPT;
		}
		String libraryId = ((String) args[0]).trim().split(" ")[0];
		String password = ((String) args[0]).trim().split(" ")[1];

		if (library.isInLoginMode()) {
			return Messages.ALREADY_LOGGED_IN;
		}
		LibraryUser libraryUser = library.authenticateDetails(libraryId, password);
		if (libraryUser != null) {
            return String.format(Messages.SUCCESSFUL_LOGIN_MESSAGE, libraryUser.getName());
		}

		return Messages.UNSUCCESSFUL_LOGIN_MESSAGE;
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
