package com.twu.com.twu.actions;

import com.twu.library.Library;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class DisplayInvalidOptionAction implements LibraryAction {
	@Override
	public String execute(Library model, Object... args) {
		return model.getInvalidOptionMessage();
	}

}
