package com.twu.biblioteca;

import com.twu.library.LibraryMenu;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class BibliotecaTest {
	@Test
	public void getWelcomeMessage() {
		LibraryMenu menu = new LibraryMenu();
		Assert.assertEquals("Welcome to Bibioteca. Application is now ready to use.", menu.getWelcomeMessage());
	}

	@Test
	public void getListOfBooks() {
		LibraryMenu menu = new LibraryMenu();
		Assert.assertEquals("[id='HP', name='Harry Potter 1', author='J.K Rowling', yearOfPublication=1991]\n" +
		"[id='HW', name='Henri's Walk to Paris', author='Saul Bass', yearOfPublication=1964]", menu.getListOfBooks());
	}
}
