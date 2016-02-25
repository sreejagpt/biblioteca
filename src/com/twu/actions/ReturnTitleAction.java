package com.twu.actions;

import com.twu.library.Library;
import com.twu.library.titles.LibraryBook;
import com.twu.library.titles.Title;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class ReturnTitleAction<T extends Title> extends LibraryAction {

	public ReturnTitleAction(Library library, Class<T> type) {
		super(library, type);
	}

	@Override
	public String execute(Object... args) {
		if (args.length != 1) {
			return getLibrary().getTitleIdPrompt();
		}
		String titleId = (String) args[0];
		Title title = getLibrary().getLibraryTitleById(titleId);
		if (title == null) {
			return getLibrary().getTitleNotFound();
		}
		if (!title.isCheckedOut()) {
			return getLibrary().getInvalidReturn(title.getClass());
		}
		getLibrary().updateCheckoutStatus(titleId, false);
		return getLibrary().getValidReturn(title.getClass());
	}

	@Override
	public String getActionDescription() {
		if (getType().equals(LibraryBook.class)) {
			return "Return Book [ID]";
		} else {
			return "Return Movie [ID]";
		}
	}

}
