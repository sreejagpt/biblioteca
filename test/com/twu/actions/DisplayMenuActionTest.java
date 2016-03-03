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
import util.TestUtil;

import static org.mockito.Mockito.when;

/**
 * Created by Sreeja on 24/02/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class DisplayMenuActionTest {
	private TestUtil util;
	private LibraryAction action;
    @Mock
    private Actions actions;
    @InjectMocks
	private Library library;

	@Before
	public void setup() {
        when(actions.getActions()).thenReturn(TestConfig.actionMapper);
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