package com.twu.actions;

import com.twu.library.Constants;
import com.twu.library.Library;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class QuitAction extends LibraryAction {

	public QuitAction(Library library) {
		super(library);
	}

	@Override
	public String execute(Object... args) {
		getLibrary().setEnabled(false);
		return Constants.QUIT_MESSAGE;
	}

	@Override
	public String getActionDescription() {
		return "Quit";
	}

	@Override
	public boolean onlyAvailableWhenLoggedIn() {
		return false;
	}

}
