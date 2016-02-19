package com.twu.library;

import com.twu.actions.DefaultAction;
import com.twu.com.twu.actions.DisplayMenuAction;
import com.twu.com.twu.actions.LibraryAction;
import com.twu.com.twu.actions.ListBooksAction;
import com.twu.com.twu.actions.QuitAction;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class BibliotecaController {

	private BibliotecaModel model;

	public BibliotecaController() {
		this.model = new BibliotecaModel(true);
	}

	public boolean isOn() {
		return model.isOn();
	}

	public void displayMenu() {
		LibraryAction displayMenu = new DisplayMenuAction();
		displayMenu.execute(model);
	}

	public void runCommand(int option) {
		switch(option) {
			case 1:
				new ListBooksAction().execute(model);
				break;
			case 99:
				new QuitAction().execute(model);
				break;
			default:
				new DefaultAction().execute(model);
		}
	}
}
