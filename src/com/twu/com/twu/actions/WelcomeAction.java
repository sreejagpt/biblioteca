package com.twu.com.twu.actions;

import com.twu.library.BibliotecaModel;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class WelcomeAction implements LibraryAction {

	@Override
	public String execute(BibliotecaModel model, Object... args) {
		return model.getWelcomeMessage();
	}

	@Override
	public int getKey() {
		return -1;
	}
}
