package com.twu.library;

import com.twu.com.twu.actions.DisplayMenuAction;
import com.twu.com.twu.actions.LibraryAction;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class LibraryController {
	private boolean on;

	public LibraryController() {
		this.on = true;
	}

	public boolean isOn() {
		return on;
	}

	public void displayMenu() {
		LibraryAction displayMenu = new DisplayMenuAction();
		displayMenu.execute();
	}

	public void runCommand(int option) {

	}
}
