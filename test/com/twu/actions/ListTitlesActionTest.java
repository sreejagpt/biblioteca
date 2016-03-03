package com.twu.actions;

import com.twu.library.Library;
import com.twu.library.titles.LibraryBook;
import com.twu.library.titles.LibraryMovie;
import com.twu.library.titles.Title;
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
public class ListTitlesActionTest {
	private LibraryAction listBooksAction;
	private LibraryAction listMoviesAction;
    @Mock
    private Actions actions;
    @InjectMocks
	private Library library;
	private TestUtil util;

	@Before
	public void setup() {
        when(actions.getActions()).thenReturn(TestConfig.actionMapper);
		listBooksAction = new ListTitlesAction<>(LibraryBook.class);
		listMoviesAction = new ListTitlesAction<>(LibraryMovie.class);
		util = new TestUtil();
	}

	@Test
	public void listAllBooks() {
		Assert.assertEquals(util.readFile("booklist.txt", true), listBooksAction.execute(library));
	}

	@Test
	public void doNotDisplayCheckedOutBooks() {
		CheckoutTitleAction checkout = new CheckoutTitleAction<>(LibraryBook.class);
		Assert.assertEquals(TestConfig.CHECKOUT_THANK_YOU_BOOK_MESSAGE, checkout.execute(library, "HP"));
		Assert.assertEquals(true, library.getLibraryTitleById("HP").isCheckedOut());
		Assert.assertEquals("[id='HW', name='Henri's Walk to Paris', author='Saul Bass', yearOfPublication=1964]",
				listBooksAction.execute(library));
	}

	@Test
	public void listAllMovies() {
		Assert.assertEquals(util.readFile("movielist.txt", true), listMoviesAction.execute(library));
	}

	@Test
	public void doNotDisplayCheckedOutMovies() {
		Title titanic = library.getLibraryTitleById("TI");
		titanic.setCheckedOut(true);
		Assert.assertEquals(util.readFile("movielistwithoutTitanic.txt", true), listMoviesAction.execute(library));
	}
}