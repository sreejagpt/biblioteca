package com.twu.actions;

import com.twu.library.Library;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Sreeja on 24/02/2016.
 */
public class WelcomeActionTest {
	private LibraryAction action;
	private Library library;

	@Before
	public void setup() {
		library = new Library(true);
		action = new WelcomeAction(library);
	}

	@Test
	public void displayWelcomeMessage() {
		Assert.assertEquals("Welcome to Biblioteca. Application is now ready to use.\n", action.execute());
	}
}