package com.twu.actions;

import com.twu.library.Library;
import com.twu.library.titles.LibraryBook;
import com.twu.library.titles.LibraryMovie;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

/**
 * Created by Sreeja on 25/02/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class ReturnTitleActionTest {
	private LibraryAction returnBookAction;
	private LibraryAction returnMovieAction;
    @Mock
	private Library library;

	@Before
	public void setup() {
		returnBookAction = new ReturnTitleAction<>(LibraryBook.class);
		returnMovieAction = new ReturnTitleAction<>(LibraryMovie.class);
	}

	@Test
    public void returningCheckedOutBookIsSuccessful() {
        LibraryBook libraryBook = mock(LibraryBook.class);
        when(libraryBook.isCheckedOut()).thenReturn(true);
        when(library.getLibraryTitleById("HP")).thenReturn(libraryBook);
        returnBookAction.execute(library, "HP");
        verify(library).updateCheckoutStatus("HP", false);
    }

	@Test
    public void returningCheckedOutMovieIsSuccessful() {
        LibraryMovie libraryMovie = mock(LibraryMovie.class);
        when(libraryMovie.isCheckedOut()).thenReturn(true);
        when(library.getLibraryTitleById("TI")).thenReturn(libraryMovie);
        returnMovieAction.execute(library, "TI");
        verify(library).updateCheckoutStatus("TI", false);
    }

	@Test
	public void cannotReturnCheckedInBook() {
        LibraryBook libraryBook = mock(LibraryBook.class);
        when(libraryBook.isCheckedOut()).thenReturn(false);
        when(library.getLibraryTitleById("HP")).thenReturn(libraryBook);
        returnBookAction.execute(library, "HP");
        verify(libraryBook).getInvalidReturnMessage();
    }

	@Test
	public void cannotReturnCheckedInMovie() {
        LibraryMovie libraryMovie = mock(LibraryMovie.class);
        when(libraryMovie.isCheckedOut()).thenReturn(false);
        when(library.getLibraryTitleById("TI")).thenReturn(libraryMovie);
        returnMovieAction.execute(library, "TI");
        verify(libraryMovie).getInvalidReturnMessage();
    }

	@Test
	public void cannotReturnNonExistentBook() {
        when(library.getLibraryTitleById("BOOKDOESNTEXIST")).thenReturn(null);
        returnBookAction.execute(library, "BOOKDOESNTEXIST");
        verify(library).getTitleNotFoundMessage();
    }

	@Test
	public void cannotReturnNonExistentMovie() {
        when(library.getLibraryTitleById("MOVIEDOESNTEXIST")).thenReturn(null);
        returnMovieAction.execute(library, "MOVIEDOESNTEXIST");
        verify(library).getTitleNotFoundMessage();
    }

	@Test
    public void notEnteringBookIdReturnsPrompt() {
        returnBookAction.execute(library);
        verify(library).getEnterATitleIdMessage();
    }

	@Test
	public void invalidArgumentsForMovie() {
        returnMovieAction.execute(library);
        verify(library).getEnterATitleIdMessage();
    }
}
