package com.twu.actions;

import com.twu.library.Library;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Sreeja on 24/02/2016.
 */
public class DisplayMenuActionTest {

	private LibraryAction action;
	private Library library;

	@Before
	public void setup() {
		library = new Library(true);
		action = new DisplayMenuAction(library);
	}

	@Test
	public void getMenuList() {
		Assert.assertEquals("1) List Books\n" +
				"2) Checkout Book [ID]\n" +
				"3) Return Book [ID]\n" +
				"99) Quit\n", action.execute());
	}
}