package com.twu.actions;

import com.twu.library.Library;
import com.twu.library.titles.LibraryBook;
import com.twu.library.titles.LibraryMovie;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.TestUtil;

/**
 * Created by Sreeja on 24/02/2016.
 */
public class ListTitlesActionTest {
	private LibraryAction listBooksAction;
	private LibraryAction listMoviesAction;
	private Library library;
	private TestUtil util;

	@Before
	public void setup() {
		library = new Library(true);
		listBooksAction = new ListTitlesAction<>(library, LibraryBook.class);
		listMoviesAction = new ListTitlesAction<>(library, LibraryMovie.class);
		util = new TestUtil();
	}

	@Test
	public void listAllBooks() {
		Assert.assertEquals(util.readFile("booklist.txt"), listBooksAction.execute());
	}

	@Test
	public void doNotDisplayCheckedOutBooks() {
		CheckoutTitleAction checkout = new CheckoutTitleAction<>(library, LibraryBook.class);
		Assert.assertEquals("Thank you! Enjoy the book.\n", checkout.execute("HP"));
		Assert.assertEquals(true, library.getLibraryTitleById("HP").isCheckedOut());
		Assert.assertEquals("[id='HW', name='Henri's Walk to Paris', author='Saul Bass', yearOfPublication=1964]",
				listBooksAction.execute());
	}

	@Test
	public void listAllMovies() {
		Assert.assertEquals(util.readFile("movielist.txt"), listMoviesAction.execute());
	}

	@Test
	public void doNotDisplayCheckedOutMovies() {
		//TODO
	}
}