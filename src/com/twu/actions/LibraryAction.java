package com.twu.actions;

import com.twu.library.Library;

/**
 * Created by Sreeja on 19/02/2016.
 */
public abstract class LibraryAction {
	private Library library;

	public LibraryAction(Library library) {
		this.library = library;
	}

	protected Library getLibrary() {
		return library;
	}

	public abstract String execute(Object... args);

	public abstract String getActionDescription();
}
