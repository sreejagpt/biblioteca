package com.twu.actions;

import com.twu.library.Library;
import com.twu.library.LibraryBook;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class CheckoutBookAction extends LibraryAction {

	public CheckoutBookAction(Library library) {
		super(library);
	}

	@Override
	public String execute(Object... args) {
		String bookId = (String) args[0];
		if (bookId.isEmpty()) {
			return getLibrary().getBookIdPrompt();
		}
		LibraryBook libraryBook = getLibrary().findLibraryBookById(bookId);
		if (libraryBook == null || libraryBook.isCheckedOut()) {
			return getLibrary().getUnavailableBook();
		}
		getLibrary().updateCheckoutStatus(bookId, true);
		return getLibrary().getSuccessfulCheckout();
	}
}
