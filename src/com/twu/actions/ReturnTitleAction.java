package com.twu.actions;

import com.twu.library.Library;
import com.twu.library.titles.LibraryBook;
import com.twu.library.titles.Title;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class ReturnTitleAction<T extends Title> extends LibraryAction {

	public ReturnTitleAction(Class<T> type) {
		super(type);
	}

	@Override
	public String execute(Library library, Object... args) {
		if (args.length != 1) {
            return library.getEnterATitleIdMessage();
        }
		String titleId = (String) args[0];
		Title title = library.getLibraryTitleById(titleId);
		if (title == null) {
            return library.getTitleNotFoundMessage();
        }
		if (!title.isCheckedOut()) {
            return title.getInvalidReturnMessage();
        }
		library.updateCheckoutStatus(titleId, false);
		return library.getValidReturn(title);
	}

	@Override
	public String getActionDescription() {
		if (getType().equals(LibraryBook.class)) {
			return "Return Book [ID]";
		} else {
			return "Return Movie [ID]";
		}
	}

	@Override
	public boolean onlyAvailableWhenLoggedIn() {
		return true;
	}

}
