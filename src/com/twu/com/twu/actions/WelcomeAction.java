package com.twu.com.twu.actions;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class WelcomeAction implements LibraryAction {

	private static final String WELCOME_MESSAGE = "Welcome to Bibioteca. Application is now ready to use.";

	@Override
	public String execute() {
		return WELCOME_MESSAGE;
	}
}
