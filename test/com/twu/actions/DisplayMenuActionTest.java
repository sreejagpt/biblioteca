package com.twu.actions;

import com.twu.library.Library;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.TestUtil;

/**
 * Created by Sreeja on 24/02/2016.
 */
public class DisplayMenuActionTest {

	TestUtil util;
	private LibraryAction action;
	private Library library;

	@Before
	public void setup() {
		library = new Library(true);
		action = new DisplayMenuAction(library);
		util = new TestUtil();
	}

	@Test
	public void getMenuListPreLogin() {
		Assert.assertEquals(util.readFile("printedmenu_prelogin.txt", false), action.execute());
	}

	@Test
	public void getMenuListPostLogin() {
		library.setLoginMode(true);
		Assert.assertEquals(util.readFile("printedmenu_postlogin.txt", false), action.execute());
	}
}