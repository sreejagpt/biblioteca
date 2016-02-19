package com.twu.biblioteca;

import com.twu.com.twu.actions.LibraryAction;
import com.twu.com.twu.actions.ListBooksAction;
import com.twu.com.twu.actions.WelcomeAction;
import com.twu.library.LibraryMenu;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class BibliotecaTest {
	@Test
	public void getWelcomeMessage() {
		LibraryAction welcomeAction = new WelcomeAction();
		Assert.assertEquals("Welcome to Bibioteca. Application is now ready to use.", welcomeAction.execute());
	}

	@Test
	public void getListOfBooks() {
		LibraryAction listBooksAction = new ListBooksAction();
		Assert.assertEquals("[id='HP', name='Harry Potter 1', author='J.K Rowling', yearOfPublication=1991]\n" +
		"[id='HW', name='Henri's Walk to Paris', author='Saul Bass', yearOfPublication=1964]", listBooksAction.execute());
	}

	@Test
	public void getMenuOptions() {
		LibraryMenu menu = new LibraryMenu();
		Assert.assertEquals("1) List Books", menu.printMenuOptions());
	}
}
