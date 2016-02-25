package com.twu.actions;

import com.twu.library.Library;
import com.twu.library.titles.LibraryBook;
import com.twu.library.titles.Title;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class CheckoutTitleAction extends LibraryAction {

	public CheckoutTitleAction(Library library, Class<? extends Title> type) {
		super(library, type);
	}

	@Override
	public String execute(Object... args) {
		if (args.length != 1) {
			return getLibrary().getTitleIdPrompt();
		}
		String titleId = (String) args[0];
		if (titleId == null || titleId.isEmpty()) {
			return getLibrary().getTitleIdPrompt();
		}
		Title title = getLibrary().getLibraryTitleById(titleId);

		if (title == null) {
			return getLibrary().getUnavailableTitle(LibraryBook.class);
		}
		if (title.isCheckedOut()) {
			return getLibrary().getUnavailableTitle(title.getClass());
		}
		getLibrary().updateCheckoutStatus(titleId, true);
		return getLibrary().getSuccessfulCheckout(title.getClass());
	}

	@Override
	public String getActionDescription() {
		if (getType().equals(LibraryBook.class)) {
			return "Checkout Book [ID]";
		} else {
			return "Checkout Movie [ID]";
		}
	}
}
