package com.twu.actions;

import data.Constants;
import com.twu.library.Library;
import com.twu.library.titles.LibraryBook;
import com.twu.library.titles.Title;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class CheckoutTitleAction<T extends Title> extends LibraryAction {

	public CheckoutTitleAction(Class<T> type) {
		super(type);
	}

	@Override
	public String execute(Library library, Object... args) {
		if (args.length != 1) {
			return Constants.ENTER_A_TITLEID;
		}
		String titleId = (String) args[0];
		if (titleId == null || titleId.isEmpty()) {
			return Constants.ENTER_A_TITLEID;
		}
		Title title = library.getLibraryTitleById(titleId);

		if (title == null) {
			return Constants.TITLE_NOT_FOUND;
		}
		if (title.isCheckedOut()) {
			return library.getUnavailableTitle(title);
		}
		library.updateCheckoutStatus(titleId, true);
		return library.getSuccessfulCheckout(title);
	}

	@Override
	public String getActionDescription() {
		if (getType().equals(LibraryBook.class)) {
			return "Checkout Book [ID]";
		} else {
			return "Checkout Movie [ID]";
		}
	}

	@Override
	public boolean onlyAvailableWhenLoggedIn() {
		return true;
	}
}
