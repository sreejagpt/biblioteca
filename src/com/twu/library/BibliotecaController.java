package com.twu.library;

import com.twu.actions.DisplayMenuAction;
import com.twu.actions.LibraryAction;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class BibliotecaController {

	private final Library library;

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

	public String runCommand(int option, Object... args) {
		return library.executeActionByInputCode(option).execute(args);
	}
}
