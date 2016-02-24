package com.twu.com.twu.actions;

import com.twu.library.Library;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class QuitAction implements LibraryAction {
	@Override
	public String execute(Library model, Object... args) {
		model.setEnabled(false);
		return model.getQuitMessage();
	}

}
