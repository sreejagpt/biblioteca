package com.twu.actions;

import com.twu.library.Library;
import data.Actions;
import data.LibraryArchive;
import data.UserBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import util.TestUtil;

/**
 * Created by Sreeja on 25/02/2016.
 */
public class LoginActionTest {
	private LibraryAction loginAction;
    @InjectMocks
	private Library library;
	private TestUtil util;

	@Before
	public void setup() {
        library = new Library(true, new UserBase(), new Actions(), new LibraryArchive());
		loginAction = new LoginAction();
		util = new TestUtil();
	}

	@Test
	public void notLoggedInAtStart() {
		Assert.assertEquals(false, library.isInLoginMode());
		Assert.assertEquals(util.readFile("printedmenu_prelogin.txt", false), new DisplayMenuAction().execute(library));
	}

	@Test
	public void loggedInMenuContainsAllOptions() {
		library.setLoginMode(true);
		Assert.assertEquals(util.readFile("printedmenu_postlogin.txt", false), new DisplayMenuAction().execute(library));
	}

	@Test
	public void cannotLoginWithoutUsernameOrPassword() {
		Assert.assertEquals(false, library.isInLoginMode());
		Assert.assertEquals("Please enter a Library Number and Password with your request.\n", loginAction.execute(library));
		Assert.assertEquals(false, library.isInLoginMode());
	}

	@Test
	public void performValidLogin() {
		Assert.assertEquals(false, library.isInLoginMode());
		Assert.assertEquals("Welcome Sreeja. You have now logged in.\n", loginAction.execute(library, "123-4567 " +
				"password1"));
		Assert.assertEquals(true, library.isInLoginMode());
	}

	@Test
	public void cannotLoginIfAlreadyLoggedIn() {
		library.setLoginMode(true);
		Assert.assertEquals("User is already logged in.\n", loginAction.execute(library, "123-4567 password1"));
		Assert.assertEquals(true, library.isInLoginMode());
	}

	@Test
	public void useInvalidLoginDetails() {
		Assert.assertEquals(false, library.isInLoginMode());
		Assert.assertEquals("Incorrect Library Number/Password Combination.\n", loginAction.execute(library,
				"100-4567 " +
				"password1"));
		Assert.assertEquals(false, library.isInLoginMode());
	}
}
