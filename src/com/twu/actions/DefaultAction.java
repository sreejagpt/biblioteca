package com.twu.actions;

import com.twu.com.twu.actions.LibraryAction;
import com.twu.library.BibliotecaModel;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class DefaultAction implements LibraryAction {
	@Override
	public String execute(BibliotecaModel model) {
		return model.getInvalidOptionMessage();
	}
}
