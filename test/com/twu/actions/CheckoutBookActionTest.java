package com.twu.actions;

import com.twu.library.Library;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Sreeja on 24/02/2016.
 */
public class CheckoutBookActionTest {
	private CheckoutBookAction action;
	private Library library;

	@Before
	public void setup() {
		library = new Library(true);
		action = new CheckoutBookAction(library);
	}

	@Test
	public void canCheckoutAValidBook() {
		Assert.assertEquals("Thank you! Enjoy the book.\n", action.execute("HP"));
		Assert.assertEquals(true, library.findLibraryBookById("HP").isCheckedOut());
	}

	@Test
	public void cannotCheckoutBookIfNoIDProvided() {
		Assert.assertEquals("Please enter a book ID with your request\n", action.execute(""));
	}

	@Test
	public void cannotCheckoutAlreadyCheckedOutBook() {
		Assert.assertEquals("Thank you! Enjoy the book.\n", action.execute("HP"));
		Assert.assertEquals(true, library.findLibraryBookById("HP").isCheckedOut());
		Assert.assertEquals("That book is not available.\n", action.execute("HP"));
		Assert.assertEquals(true, library.findLibraryBookById("HP").isCheckedOut());
	}
}
