package com.twu.actions;

import com.twu.library.Library;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.TestUtil;

/**
 * Created by Sreeja on 24/02/2016.
 */
public class ListBooksActionTest {
	private LibraryAction action;
	private Library library;
	private TestUtil util;

	@Before
	public void setup() {
		library = new Library(true);
		action = new ListBooksAction(library);
		util = new TestUtil();
	}

	@Test
	public void listAllBooks() {
		Assert.assertEquals(util.readFile("booklist.txt"), action.execute());
	}

	@Test
	public void doNotDisplayCheckedOutBooks() {
		CheckoutBookAction checkout = new CheckoutBookAction(library);
		Assert.assertEquals("Thank you! Enjoy the book.\n", checkout.execute("HP"));
		Assert.assertEquals(true, library.findLibraryBookById("HP").isCheckedOut());
		Assert.assertEquals("[id='HW', name='Henri's Walk to Paris', author='Saul Bass', yearOfPublication=1964]",
				action.execute());
	}
}