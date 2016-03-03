package com.twu.actions;

import com.twu.library.Library;
import com.twu.library.LibraryUser;
import data.UserBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import util.TestUtil;

import static org.mockito.Mockito.when;

/**
 * Created by Sreeja on 25/02/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class PrintUserDetailsActionTest {
	private LibraryAction action;
    @Mock
    private UserBase userBase;
    @Mock
    private Library library;
	private TestUtil util;

	@Before
	public void setup() {
        when(library.isInLoginMode()).thenReturn(false);
        action = new PrintUserDetailsAction();
		util = new TestUtil();
	}

	@Test
	public void notAvailableWhenNotLoggedIn() {
		Assert.assertEquals("You must be logged in to view user details.\n", action.execute(library));
	}

	@Test
	public void printUserDetailsWhenLoggedIn() {
		when(library.isInLoginMode()).thenReturn(true);
		LibraryUser user = new LibraryUser("123-4567", "Sreeja", "sreeja@fun.com", "34534534");
		when(library.getCurrentUser()).thenReturn(user);
		Assert.assertEquals(util.readFile("userdetails.txt", true), action.execute(library));
	}
}
