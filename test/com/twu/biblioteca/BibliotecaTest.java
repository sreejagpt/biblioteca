package com.twu.biblioteca;

import com.twu.library.Library;
import com.twu.library.LibraryMenu;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class BibliotecaTest {
	@Test
	public void getWelcomeMessage() {
		Library library = new Library();
		Assert.assertEquals("Welcome to Bibioteca. Application is now ready to use.", library.getWelcomeMessage());
	}

	@Test
	public void getListOfBooks() {
		Library library = new Library();
		Assert.assertEquals("[id='HP', name='Harry Potter 1', author='J.K Rowling', yearOfPublication=1991]\n" +
		"[id='HW', name='Henri's Walk to Paris', author='Saul Bass', yearOfPublication=1964]", library.getListOfBooks());
	}

	@Test
	public void getMenuOptions() {
		LibraryMenu menu = new LibraryMenu();
		Assert.assertEquals("1) List Books", menu.getOptions());

	}
}
