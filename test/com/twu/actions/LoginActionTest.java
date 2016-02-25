package com.twu.actions;

import com.twu.library.Library;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.TestUtil;

/**
 * Created by Sreeja on 25/02/2016.
 */
public class LoginActionTest {
	private LibraryAction loginAction;
	private Library library;
	private TestUtil util;

	@Before
	public void setup() {
		library = new Library(true);
		loginAction = new LoginAction(library);
		util = new TestUtil();
	}

	@Test
	public void notLoggedInAtStart() {
		Assert.assertEquals(false, library.isInLoginMode());
		Assert.assertEquals(util.readFile("printedmenu_prelogin.txt", false), new DisplayMenuAction(library).execute());
	}

	@Test
	public void loggedInMenuContainsAllOptions() {
		library.setLoginMode(true);
		Assert.assertEquals(util.readFile("printedmenu_postlogin.txt", false), new DisplayMenuAction(library).execute());
	}

	@Test
	public void cannotLoginWithoutUsernameOrPassword() {
		Assert.assertEquals(false, library.isInLoginMode());
		Assert.assertEquals("Please enter a Library Number and Password with your request.\n", loginAction.execute());
		Assert.assertEquals(false, library.isInLoginMode());
	}

	@Test
	public void performValidLogin() {
		Assert.assertEquals(false, library.isInLoginMode());
		Assert.assertEquals("Welcome Sreeja. You have now logged in.\n", loginAction.execute("123-4567", "password1"));
		Assert.assertEquals(true, library.isInLoginMode());
	}

	@Test
	public void cannotLoginIfAlreadyLoggedIn() {
		library.setLoginMode(true);
		Assert.assertEquals("User is already logged in.\n", loginAction.execute("123-4567", "password1"));
		Assert.assertEquals(true, library.isInLoginMode());
	}

	@Test
	public void useInvalidLoginDetails() {
		Assert.assertEquals(false, library.isInLoginMode());
		Assert.assertEquals("Incorrect Library Number/Password Combination.\n", loginAction.execute("100-4567", "password1"));
		Assert.assertEquals(false, library.isInLoginMode());
	}
}
