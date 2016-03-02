package com.twu.actions;

import com.twu.library.Constants;
import com.twu.library.Library;

/**
 * Created by Sreeja on 25/02/2016.
 */
public class PrintUserDetailsAction extends LibraryAction {
	public PrintUserDetailsAction(Library library) {
		super(library);
	}

	@Override
	public String execute(Object... args) {
		if (!getLibrary().isInLoginMode() || getLibrary().getCurrentUser() == null) {
			return Constants.MUST_BE_LOGGED_IN;
		}
		return getLibrary().getCurrentUser().toString();
	}

	@Override
	public String getActionDescription() {
		return "My Details";
	}

	@Override
	public boolean onlyAvailableWhenLoggedIn() {
		return true;
	}
}
