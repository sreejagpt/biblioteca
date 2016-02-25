package com.twu.actions;

import com.twu.library.Library;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class DisplayMenuAction extends LibraryAction {

	public DisplayMenuAction(Library library) {
		super(library);
	}

	@Override
	public String execute(Object... args) {
		return getLibrary().getMenuList();
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
