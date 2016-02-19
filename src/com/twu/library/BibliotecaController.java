package com.twu.library;

import com.twu.com.twu.actions.*;

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

	public String runCommand(int option) {
		switch(option) {
			case 1:
				return new ListBooksAction().execute(model);
			case 99:
				return new QuitAction().execute(model);
			default:
				return new DefaultAction().execute(model);
		}
	}
}
