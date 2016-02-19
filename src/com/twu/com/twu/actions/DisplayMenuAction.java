package com.twu.com.twu.actions;

import com.twu.library.BibliotecaModel;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class DisplayMenuAction implements LibraryAction {
	@Override
	public String execute(BibliotecaModel model) {
		return model.getMenuList();
	}
}
