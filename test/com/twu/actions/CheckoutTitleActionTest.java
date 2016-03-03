package com.twu.actions;

import com.twu.library.Library;
import com.twu.library.titles.LibraryBook;
import com.twu.library.titles.LibraryMovie;
import com.twu.library.titles.MovieRating;
import com.twu.library.titles.Title;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.Year;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Sreeja on 24/02/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class CheckoutTitleActionTest {
	private LibraryAction checkoutBookAction;
	private LibraryAction checkoutMovieAction;
    @Mock
	private Library library;

	@Before
	public void setup() {
        when(library.getLibraryTitleById("HP")).thenReturn(new LibraryBook("HP", "Harry Potter 1", Year.of(1991), "J" +
                ".K Rowling", false));
        when(library.getLibraryTitleById("TI")).thenReturn(new LibraryMovie("TI", "Titanic", Year.of(1997), "James Cameron",
                MovieRating.toRating(3), false));
		checkoutBookAction = new CheckoutTitleAction<>(LibraryBook.class);
		checkoutMovieAction = new CheckoutTitleAction<>(LibraryMovie.class);
	}

	@Test
	public void canCheckoutAValidBook() {
		checkoutBookAction.execute(library, "HP");
        verify(library).updateCheckoutStatus("HP", true);
        verify(library).getSuccessfulCheckout(any(Title.class));
    }

	@Test
	public void cannotCheckoutBookIfNoIDProvided() {
		checkoutBookAction.execute(library, "");
        verify(library).getEnterATitleIdMessage();
	}

	@Test
	public void cannotCheckoutAlreadyCheckedOutBook() {
        when(library.getLibraryTitleById("HP")).thenReturn(new LibraryBook("HP", "Harry Potter 1", Year.of(1991), "J" +
                ".K Rowling", true));
        checkoutBookAction.execute(library, "HP");
        verify(library).getUnavailableTitle(any(Title.class));
	}

	@Test
	public void incorrectArgumentLengthError() {
		checkoutBookAction.execute(library);
        verify(library).getEnterATitleIdMessage();
	}

	@Test
	public void incorrectArgumentLengthError2() {
		checkoutBookAction.execute(library, "HP", "HW");
        verify(library).getEnterATitleIdMessage();
    }

	@Test
	public void canCheckoutAValidMovie() {
		checkoutMovieAction.execute(library, "TI");
        verify(library).updateCheckoutStatus("TI", true);
        verify(library).getSuccessfulCheckout(any(Title.class));
	}

	@Test
	public void cannotCheckoutMovieIfNoIDProvided() {
		checkoutMovieAction.execute(library, "");
        verify(library).getEnterATitleIdMessage();
    }

	@Test
	public void cannotCheckoutAlreadyCheckedOutMovie() {
        when(library.getLibraryTitleById("TI")).thenReturn(new LibraryMovie("TI", "Titanic", Year.of(1997), "James Cameron",
                MovieRating.toRating(3), true));
		checkoutMovieAction.execute(library, "TI");
        verify(library).getUnavailableTitle(any(Title.class));
	}

	@Test
	public void incorrectArgumentLengthErrorForMovie() {
		checkoutMovieAction.execute(library);
        verify(library).getEnterATitleIdMessage();
    }

	@Test
	public void incorrectArgumentLengthErrorForMovie2() {
		checkoutBookAction.execute(library, "TI", "SH");
        verify(library).getEnterATitleIdMessage();
    }
}
