package com.twu.actions;

import com.twu.library.Library;
import com.twu.library.titles.LibraryBook;
import com.twu.library.titles.LibraryMovie;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

/**
 * Created by Sreeja on 24/02/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ListTitlesActionTest {
	private LibraryAction listBooksAction;
	private LibraryAction listMoviesAction;
    @Mock
	private Library library;

	@Before
	public void setup() {
		listBooksAction = new ListTitlesAction<>(LibraryBook.class);
		listMoviesAction = new ListTitlesAction<>(LibraryMovie.class);
	}

	@Test
	public void listAllBooks() {
		listBooksAction.execute(library);
        verify(library).getUncheckedOutTitlesOfType(LibraryBook.class);
	}

	@Test
	public void listAllMovies() {
		listMoviesAction.execute(library);
        verify(library).getUncheckedOutTitlesOfType(LibraryMovie.class);
	}
}