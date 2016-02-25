package com.twu.actions;

import com.twu.library.Library;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class WelcomeAction extends LibraryAction {

	public WelcomeAction(Library library) {
		super(library);
	}

	@Override
	public String execute(Object... args) {
		return getLibrary().getWelcomeMessage();
	}

	@Override
	public String getActionDescription() {
		return "";
	}

	@Override
	public boolean onlyAvailableWhenLoggedIn() {
		return false;
	}

}
