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
import util.TestUtil;

/**
 * Created by Sreeja on 24/02/2016.
 */
public class DisplayMenuActionTest {

	private TestUtil util;
	private LibraryAction action;
    @InjectMocks
	private Library library;
	private TestConfig config = new TestConfig();

	@Before
	public void setup() {
        library = new Library(true, new UserBase(), new Actions(), new LibraryArchive());
		action = new DisplayMenuAction();
		util = new TestUtil();
	}

	@Test
	public void getMenuListPreLogin() {
		Assert.assertEquals(util.readFile("printedmenu_prelogin.txt", false), action.execute(library));
	}

	@Test
	public void getMenuListPostLogin() {
		library.setLoginMode(true);
		Assert.assertEquals(util.readFile("printedmenu_postlogin.txt", false), action.execute(library));
	}
}