package com.twu.actions;

import com.twu.library.Library;
import com.twu.library.titles.Title;

/**
 * Created by Sreeja on 19/02/2016.
 */
public abstract class LibraryAction {
	private final Class<? extends Title> type;
	private Library library;

	public LibraryAction(Library library) {
		this.library = library;
		this.type = null;
	}

	public LibraryAction(Library library, Class<? extends Title> type) {
		this.library = library;
		this.type = type;
	}

	protected Library getLibrary() {
		return library;
	}

	protected Class<? extends Title> getType() {
		return type;
	}

	public abstract String execute(Object... args);

	public abstract String getActionDescription();
}
