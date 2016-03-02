package com.twu.actions;

import com.twu.library.Constants;
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
		return Constants.INVALID_OPTION_MESSAGE;
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
