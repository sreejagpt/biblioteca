package com.twu.actions;

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
		return getLibrary().getInvalidOptionMessage();
	}

}
