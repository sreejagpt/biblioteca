package com.twu.actions;

import com.twu.library.Library;
import com.twu.library.LibraryUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

/**
 * Created by Sreeja on 25/02/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class LoginActionTest {
	private LibraryAction loginAction;
    @Mock
	private Library library;

	@Before
	public void setup() {
		loginAction = new LoginAction();
        LibraryUser libraryUser = mock(LibraryUser.class);
        when(library.authenticateDetails("123-4567", "password1")).thenReturn(libraryUser);
        when(library.authenticateDetails("100-4567", "password1")).thenReturn(null);
	}

	@Test
	public void loginWithNoUserNameOrPwReturnsPrompt() {
        loginAction.execute(library);
        verify(library).getLoginPromptMessage();
	}

    @Test
    public void loginWithUserNameAndNoPwReturnsPrompt() {
        loginAction.execute(library, "username");
        verify(library).getLoginPromptMessage();
    }

	@Test
	public void performValidLogin() {
		loginAction.execute(library, "123-4567 password1");
        verify(library).authenticateDetails("123-4567", "password1");
        verify(library).getSuccessfulLoginMessage();
	}

	@Test
	public void cannotLoginIfAlreadyLoggedIn() {
        when(library.isInLoginMode()).thenReturn(true);
		loginAction.execute(library, "123-4567 password1");
        verify(library).getUserAlreadyLoggedInMessage();
	}

	@Test
	public void invalidCredentialsReturnsPrompt() {
		loginAction.execute(library, "100-4567 password1");
        verify(library).authenticateDetails("100-4567", "password1");
        verify(library).getFaultyCredentialsMessage();
	}
}
