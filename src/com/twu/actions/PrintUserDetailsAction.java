package com.twu.actions;

import data.Constants;
import com.twu.library.Library;

/**
 * Created by Sreeja on 25/02/2016.
 */
public class PrintUserDetailsAction extends LibraryAction {
	public PrintUserDetailsAction() {
		super();
	}

	@Override
	public String execute(Library library, Object... args) {
		if (!library.isInLoginMode() || library.getCurrentUser() == null) {
			return Constants.MUST_BE_LOGGED_IN;
		}
		return library.getCurrentUser().toString();
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
