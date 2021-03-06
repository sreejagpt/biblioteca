package com.twu.actions;

import com.twu.library.Library;
import com.twu.library.titles.Title;

/**
 * Created by Sreeja on 19/02/2016.
 */
public abstract class LibraryAction {
	private final Class<? extends Title> type;

	LibraryAction() {
		this.type = null;
	}

	LibraryAction(Class<? extends Title> type) {
		this.type = type;
	}

	Class<? extends Title> getType() {
		return type;
	}

	public abstract String execute(Library library, Object... args);

	public abstract String getActionDescription();

	public abstract boolean onlyAvailableWhenLoggedIn();
}
