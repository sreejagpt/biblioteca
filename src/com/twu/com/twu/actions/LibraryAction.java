package com.twu.com.twu.actions;

import com.twu.library.BibliotecaModel;

/**
 * Created by Sreeja on 19/02/2016.
 */
public interface LibraryAction {
	String execute(BibliotecaModel model, Object... args);

	int getKey();
}
