package com.twu.actions;

import data.Messages;
import com.twu.library.Library;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class QuitAction extends LibraryAction {

	public QuitAction() {
		super();
	}

	@Override
	public String execute(Library library, Object... args) {
		library.setEnabled(false);
		return Messages.QUIT_MESSAGE;
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
