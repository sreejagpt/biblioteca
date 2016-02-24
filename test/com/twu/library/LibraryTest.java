package com.twu.library;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Year;

/**
 * Created by Sreeja on 24/02/2016.
 */
public class LibraryTest {

	private Library library;

	@Before
	public void setup() {
		library = new Library(true);
	}

	@Test
	public void findExistingLibraryBookById() {
		Assert.assertEquals(new LibraryBook("HP", "Harry Potter 1", Year.of(1991), "J.K Rowling", false), library
				.findLibraryBookById("HP"));

	}

	@Test
	public void cannotFindNonExistentLibraryBook() {
		Assert.assertEquals(null, library.findLibraryBookById("DOESNTEXIST"));
	}

	@Test
	public void checkoutBook() {
		library.updateCheckoutStatus("HP", true);
		Assert.assertEquals(true, library.findLibraryBookById("HP").isCheckedOut());
	}
}