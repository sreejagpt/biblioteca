package com.twu.actions;

import com.twu.library.Library;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Sreeja on 24/02/2016.
 */
public class DisplayInvalidOptionActionTest {
	private LibraryAction action;
	private Library library;

	@Before
	public void setup() {
		library = new Library(true);
		action = new DisplayInvalidOptionAction(library);
	}

	@Test
	public void getInvalidMessageOnExecution() {
		Assert.assertEquals("Select a valid option!\n", action.execute());
	}
}