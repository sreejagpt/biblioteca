package com.twu.actions;

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
		return getLibrary().getQuitMessage();
	}

	@Override
	public String getActionDescription() {
		return "Quit";
	}

}