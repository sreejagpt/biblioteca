package com.twu.actions;

import com.twu.library.Library;
import com.twu.library.titles.LibraryBook;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class ReturnBookAction extends LibraryAction {

	public ReturnBookAction(Library library) {
		super(library);
	}

	@Override
	public String execute(Object... args) {
		String bookId = (String) args[0];
		LibraryBook libraryBook = getLibrary().findLibraryBookById(bookId);
		if (libraryBook == null || !libraryBook.isCheckedOut()) {
			return getLibrary().getInvalidReturn();
		}
		getLibrary().updateCheckoutStatus(bookId, false);
		return getLibrary().getValidReturn();
	}

	@Override
	public String getActionDescription() {
		return "Return Book [ID]";
	}

}
