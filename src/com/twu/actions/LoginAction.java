package com.twu.actions;

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
            return library.getLoginPromptMessage();
		}
		String libraryId = ((String) args[0]).trim().split(" ")[0];
		String password = ((String) args[0]).trim().split(" ")[1];
        if (libraryId == null || password == null) {
            return library.getLoginPromptMessage();
        }
		if (library.isInLoginMode()) {
            return library.getUserAlreadyLoggedInMessage();
		}
		LibraryUser libraryUser = library.authenticateDetails(libraryId, password);
		if (libraryUser != null) {
            return library.getSuccessfulLoginMessage();
		}
        return library.getFaultyCredentialsMessage();
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
