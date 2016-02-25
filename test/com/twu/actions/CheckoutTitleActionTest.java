package com.twu.actions;

import com.twu.library.Library;
import com.twu.library.titles.LibraryBook;
import com.twu.library.titles.LibraryMovie;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Sreeja on 24/02/2016.
 */
public class CheckoutTitleActionTest {
	private LibraryAction checkoutBookAction;
	private LibraryAction checkoutMovieAction;
	private Library library;

	@Before
	public void setup() {
		library = new Library(true);
		checkoutBookAction = new CheckoutTitleAction(library, LibraryBook.class);
		checkoutMovieAction = new CheckoutTitleAction(library, LibraryMovie.class);
	}

	@Test
	public void canCheckoutAValidBook() {
		Assert.assertEquals("Thank you! Enjoy the book.\n", checkoutBookAction.execute("HP"));
		Assert.assertEquals(true, library.getLibraryTitleById("HP").isCheckedOut());
	}

	@Test
	public void cannotCheckoutBookIfNoIDProvided() {
		Assert.assertEquals("Please enter a title ID with your request\n", checkoutBookAction.execute(""));
	}

	@Test
	public void cannotCheckoutAlreadyCheckedOutBook() {
		Assert.assertEquals("Thank you! Enjoy the book.\n", checkoutBookAction.execute("HP"));
		Assert.assertEquals(true, library.getLibraryTitleById("HP").isCheckedOut());
		Assert.assertEquals("That book is not available.\n", checkoutBookAction.execute("HP"));
		Assert.assertEquals(true, library.getLibraryTitleById("HP").isCheckedOut());
	}

	@Test
	public void incorrectArgumentLengthError() {
		Assert.assertEquals("Please enter a title ID with your request\n", checkoutBookAction.execute());
	}

	@Test
	public void incorrectArgumentLengthError2() {
		Assert.assertEquals("Please enter a title ID with your request\n", checkoutBookAction.execute("HP", "HW"));
	}

	@Test
	public void canCheckoutAValidMovie() {
		Assert.assertEquals("Thank you! Enjoy the movie.\n", checkoutMovieAction.execute("TI"));
		Assert.assertEquals(true, library.getLibraryTitleById("TI").isCheckedOut());
	}

	@Test
	public void cannotCheckoutMovieIfNoIDProvided() {
		Assert.assertEquals("Please enter a title ID with your request\n", checkoutMovieAction.execute(""));
	}

	@Test
	public void cannotCheckoutAlreadyCheckedOutMovie() {
		Assert.assertEquals("Thank you! Enjoy the movie.\n", checkoutMovieAction.execute("TI"));
		Assert.assertEquals(true, library.getLibraryTitleById("TI").isCheckedOut());
		Assert.assertEquals("That movie is not available.\n", checkoutMovieAction.execute("TI"));
		Assert.assertEquals(true, library.getLibraryTitleById("TI").isCheckedOut());
	}

	@Test
	public void incorrectArgumentLengthErrorForMovie() {
		Assert.assertEquals("Please enter a title ID with your request\n", checkoutMovieAction.execute());
	}

	@Test
	public void incorrectArgumentLengthErrorForMovie2() {
		Assert.assertEquals("Please enter a title ID with your request\n", checkoutBookAction.execute("TI", "SH"));
	}
}
