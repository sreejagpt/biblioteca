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
 * Created by Sreeja on 25/02/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class PrintUserDetailsActionTest {
	private LibraryAction action;
    @Mock
    private Library library;

	@Before
	public void setup() {
        action = new PrintUserDetailsAction();
	}

	@Test
	public void notAvailableWhenNotLoggedIn() {
        when(library.isInLoginMode()).thenReturn(false);
        action.execute(library);
        verify(library).getMustBeLoggedInMessage();
    }

	@Test
	public void printUserDetailsWhenLoggedIn() {
		when(library.isInLoginMode()).thenReturn(true);
        action.execute(library);
        verify(library).getCurrentUser();
    }
}
