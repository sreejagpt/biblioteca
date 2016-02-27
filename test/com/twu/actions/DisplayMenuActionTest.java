package com.twu.actions;

import com.twu.library.Library;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


public class DisplayMenuActionTest {

	private LibraryAction action;
	private Library library;

	@Before
	public void setup() {
        library = mock(Library.class);
		action = new DisplayMenuAction(library);
	}

	@Test
	public void getMenuListPreLogin() {
		action.execute();

		verify(library).getMenuList();
	}
}