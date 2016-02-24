package com.twu.library;

import com.twu.com.twu.actions.DisplayMenuAction;
import com.twu.com.twu.actions.LibraryAction;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class BibliotecaController {

	private Library library;

	public BibliotecaController() {
		this.library = new Library(true);
	}

	public boolean isEnabled() {
		return library.isEnabled();
	}

	public String displayMenu() {
		LibraryAction displayMenu = new DisplayMenuAction(library);
		return displayMenu.execute(library);
	}

	public String runCommand(int option) {
		return library.executeActionByInputCode(option).execute();
	}

	public String runCommand(int option, String bookID) {
		return library.executeActionByInputCode(option).execute(bookID);
	}
}
