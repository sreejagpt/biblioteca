package com.twu.com.twu.actions;

import com.twu.library.BibliotecaModel;
import com.twu.library.LibraryBook;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class CheckoutBookAction implements LibraryAction {
	@Override
	public String execute(BibliotecaModel model, Object... args) {
		String bookId = (String) args[0];
		LibraryBook libraryBook = model.findLibraryBookById(bookId);
		if (libraryBook == null || libraryBook.isCheckedOut()) {
			return model.getUnavailableBook();
		}
		model.updateCheckoutStatus(bookId, true);
		return model.getSuccessfulCheckout();
	}

}
