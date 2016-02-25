package com.twu.actions;

import com.twu.library.Library;
import com.twu.library.titles.LibraryBook;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class CheckoutBookAction extends LibraryAction {

	public CheckoutBookAction(Library library) {
		super(library);
	}

	@Override
	public String execute(Object... args) {
		if (args.length != 1) {
			return getLibrary().getBookIdPrompt();
		}
		String bookId = (String) args[0];
		if (bookId == null || bookId.isEmpty()) {
			return getLibrary().getBookIdPrompt();
		}
		LibraryBook libraryBook = getLibrary().findLibraryBookById(bookId);
		if (libraryBook == null || libraryBook.isCheckedOut()) {
			return getLibrary().getUnavailableBook();
		}
		getLibrary().updateCheckoutStatus(bookId, true);
		return getLibrary().getSuccessfulCheckout();
	}

	@Override
	public String getActionDescription() {
		return "Checkout Book [ID]";
	}
}
