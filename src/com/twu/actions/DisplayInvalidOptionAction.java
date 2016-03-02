package com.twu.actions;

import data.Messages;
import com.twu.library.Library;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class DisplayInvalidOptionAction extends LibraryAction {

	public DisplayInvalidOptionAction(Library library) {
		super(library);
	}

	@Override
	public String execute(Object... args) {
		return Messages.INVALID_OPTION_MESSAGE;
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
