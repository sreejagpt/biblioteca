package com.twu.biblioteca;

import com.twu.com.twu.actions.*;
import com.twu.library.BibliotecaModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class BibliotecaTest {

	private BibliotecaModel model;

	@Before
	public void setup() {
		model = new BibliotecaModel(true);
	}

	@Test
	public void getWelcomeMessage() {
		LibraryAction welcomeAction = new WelcomeAction();
		Assert.assertEquals("Welcome to Bibioteca. Application is now ready to use.\n", welcomeAction.execute(model));
	}

	@Test
	public void getListOfBooks() {
		LibraryAction listBooksAction = new ListBooksAction();
		Assert.assertEquals("[id='HP', name='Harry Potter 1', author='J.K Rowling', yearOfPublication=1991]\n" +
		"[id='HW', name='Henri's Walk to Paris', author='Saul Bass', yearOfPublication=1964]", listBooksAction
				.execute(model));
	}

	@Test
	public void getMenuOptions() {
		LibraryAction displayMenuAction = new DisplayMenuAction();
		Assert.assertEquals("1) List Books\n2) Quit\n", displayMenuAction.execute(model));
	}

	@Test
	public void quitSystemSuccessfully() {
		LibraryAction quitAction = new QuitAction();
		Assert.assertEquals("You have successfully quit.\n", quitAction.execute(model));
		Assert.assertEquals(false, model.isOn());
	}
}
