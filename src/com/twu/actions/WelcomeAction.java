package com.twu.actions;

import data.Messages;
import com.twu.library.Library;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class WelcomeAction extends LibraryAction {

	public WelcomeAction() {
		super();
	}

	@Override
	public String execute(Library library, Object... args) {
		return library.getWelcomeMessage();
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
