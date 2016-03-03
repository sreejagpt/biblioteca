package com.twu.actions;

import com.twu.library.Library;
import com.twu.library.titles.LibraryBook;
import com.twu.library.titles.LibraryMovie;
import data.Actions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import util.TestConfig;

import static org.mockito.Mockito.when;

/**
 * Created by Sreeja on 24/02/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class CheckoutTitleActionTest {
	private LibraryAction checkoutBookAction;
	private LibraryAction checkoutMovieAction;
    @Mock
    private Actions actions;
    @InjectMocks
	private Library library;

	@Before
	public void setup() {
        when(actions.getActions()).thenReturn(TestConfig.actionMapper);
		checkoutBookAction = new CheckoutTitleAction<>(LibraryBook.class);
		checkoutMovieAction = new CheckoutTitleAction<>(LibraryMovie.class);
	}

	@Test
	public void canCheckoutAValidBook() {
		Assert.assertEquals(TestConfig.CHECKOUT_THANK_YOU_BOOK_MESSAGE, checkoutBookAction.execute(library, "HP"));
		Assert.assertEquals(true, library.getLibraryTitleById("HP").isCheckedOut());
	}

	@Test
	public void cannotCheckoutBookIfNoIDProvided() {
		Assert.assertEquals("Please enter a title ID with your request.\n", checkoutBookAction.execute(library, ""));
	}

	@Test
	public void cannotCheckoutAlreadyCheckedOutBook() {
		Assert.assertEquals(TestConfig.CHECKOUT_THANK_YOU_BOOK_MESSAGE, checkoutBookAction.execute(library, "HP"));
		Assert.assertEquals(true, library.getLibraryTitleById("HP").isCheckedOut());
		Assert.assertEquals("That book is not available.\n", checkoutBookAction.execute(library, "HP"));
		Assert.assertEquals(true, library.getLibraryTitleById("HP").isCheckedOut());
	}

	@Test
	public void incorrectArgumentLengthError() {
		Assert.assertEquals("Please enter a title ID with your request.\n", checkoutBookAction.execute(library));
	}

	@Test
	public void incorrectArgumentLengthError2() {
		Assert.assertEquals("Please enter a title ID with your request.\n", checkoutBookAction.execute(library, "HP", "HW"));
	}

	@Test
	public void canCheckoutAValidMovie() {
		Assert.assertEquals("Thank you! Enjoy the movie.\n", checkoutMovieAction.execute(library, "TI"));
		Assert.assertEquals(true, library.getLibraryTitleById("TI").isCheckedOut());
	}

	@Test
	public void cannotCheckoutMovieIfNoIDProvided() {
		Assert.assertEquals("Please enter a title ID with your request.\n", checkoutMovieAction.execute(library, ""));
	}

	@Test
	public void cannotCheckoutAlreadyCheckedOutMovie() {
		Assert.assertEquals("Thank you! Enjoy the movie.\n", checkoutMovieAction.execute(library, "TI"));
		Assert.assertEquals(true, library.getLibraryTitleById("TI").isCheckedOut());
		Assert.assertEquals("That movie is not available.\n", checkoutMovieAction.execute(library, "TI"));
		Assert.assertEquals(true, library.getLibraryTitleById("TI").isCheckedOut());
	}

	@Test
	public void incorrectArgumentLengthErrorForMovie() {
		Assert.assertEquals("Please enter a title ID with your request.\n", checkoutMovieAction.execute(library));
	}

	@Test
	public void incorrectArgumentLengthErrorForMovie2() {
		Assert.assertEquals("Please enter a title ID with your request.\n", checkoutBookAction.execute(library, "TI", "SH"));
	}
}
