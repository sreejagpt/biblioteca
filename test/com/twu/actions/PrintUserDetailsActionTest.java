package com.twu.actions;

import com.twu.library.Library;
import com.twu.library.LibraryUser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.TestUtil;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Sreeja on 25/02/2016.
 */
public class PrintUserDetailsActionTest {
	private LibraryAction action;
	private Library library;
	private TestUtil util;

	@Before
	public void setup() {
		library = mock(Library.class);
		action = new PrintUserDetailsAction(library);
		util = new TestUtil();
	}

	@Test
	public void notAvailableWhenNotLoggedIn() {
		when(library.isInLoginMode()).thenReturn(false);
		when(library.getInvalidPrintCommandPrompt()).thenReturn("You must be logged in to view user details.\n");
		Assert.assertEquals("You must be logged in to view user details.\n", action.execute());
	}

	@Test
	public void printUserDetailsWhenLoggedIn() {
		when(library.isInLoginMode()).thenReturn(true);
		LibraryUser user = new LibraryUser("123-4567", "Sreeja", "sreeja@fun.com", "34534534");
		when(library.getCurrentUser()).thenReturn(user);
		Assert.assertEquals(util.readFile("userdetails.txt", true), action.execute());
	}
}
