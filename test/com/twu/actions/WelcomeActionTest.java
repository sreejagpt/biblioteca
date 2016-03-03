package com.twu.actions;

import com.twu.library.Library;
import data.Actions;
import data.LibraryArchive;
import data.UserBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import util.TestConfig;

/**
 * Created by Sreeja on 24/02/2016.
 */
public class WelcomeActionTest {
	private LibraryAction action;
    @InjectMocks
	private Library library;

	@Before
	public void setup() {
        library = new Library(true, new UserBase(), new Actions(), new LibraryArchive());
		action = new WelcomeAction();
	}

	@Test
	public void displayWelcomeMessage() {
		Assert.assertEquals(TestConfig.APPLICATION_READY, action.execute(library));
	}
}