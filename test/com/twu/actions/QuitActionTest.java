package com.twu.actions;

import com.twu.library.Library;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Sreeja on 24/02/2016.
 */
public class QuitActionTest {

	private LibraryAction action;
	private Library library;

	@Before
	public void setup() {
		library = new Library(true);
		action = new QuitAction(library);
	}

	@Test
	public void quitSuccessfully() {
		Assert.assertEquals(true, library.isEnabled());
		Assert.assertEquals("You have successfully quit.\n", action.execute());
		Assert.assertEquals(false, library.isEnabled());
	}
}