package com.twu.actions;

import com.twu.library.Library;
import data.Actions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import util.TestConfig;

import static org.mockito.Mockito.when;

/**
 * Created by Sreeja on 24/02/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class WelcomeActionTest {
	private LibraryAction action;
    @Mock
    private Actions actions;
    @InjectMocks
	private Library library;

	@Before
	public void setup() {
        when(actions.getActions()).thenReturn(TestConfig.actionMapper);
		action = new WelcomeAction();
	}

	@Test
	public void displayWelcomeMessage() {
		Assert.assertEquals(TestConfig.APPLICATION_READY, action.execute(library));
	}
}