package com.twu.actions;

import com.twu.library.Library;
import com.twu.library.titles.Title;

/**
 * Created by Sreeja on 19/02/2016.
 */
public abstract class LibraryAction {
	private final Class<? extends Title> type;
	private final Library library;

	LibraryAction(Library library) {
		this.library = library;
		this.type = null;
	}

	LibraryAction(Library library, Class<? extends Title> type) {
		this.library = library;
		this.type = type;
	}

	Library getLibrary() {
		return library;
	}

	Class<? extends Title> getType() {
		return type;
	}

	public abstract String execute(Object... args);

	public abstract String getActionDescription();

	public abstract boolean onlyAvailableWhenLoggedIn();
}
