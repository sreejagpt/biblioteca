package com.twu.actions;

import com.twu.library.Library;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Sreeja on 24/02/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class DisplayMenuActionTest {
	private LibraryAction action;
    @Mock
	private Library library;

	@Before
	public void setup() {
        when(library.isInLoginMode()).thenReturn(false);
		action = new DisplayMenuAction();
	}

	@Test
	public void getMenuListPreLogin() {
		action.execute(library);
        verify(library).getPreLoginMenuList();
	}

	@Test
	public void getMenuListPostLogin() {
		when(library.isInLoginMode()).thenReturn(true);
		action.execute(library);
        verify(library).getPostLoginMenuList();
	}
}