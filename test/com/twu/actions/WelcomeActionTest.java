package com.twu.actions;

import com.twu.library.Library;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * Created by Sreeja on 24/02/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class WelcomeActionTest {
	private LibraryAction action;
    @Mock
	private Library library;

	@Before
	public void setup() {
		action = new WelcomeAction();
	}

	@Test
	public void displayWelcomeMessage() {
        action.execute(library);
        verify(library).getWelcomeMessage();
    }
}