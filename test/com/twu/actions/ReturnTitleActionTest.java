package com.twu.actions;

import com.twu.library.Library;
import com.twu.library.titles.LibraryBook;
import com.twu.library.titles.LibraryMovie;
import com.twu.library.titles.Title;
import data.Actions;
import data.LibraryArchive;
import data.UserBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import util.TestConfig;

/**
 * Created by Sreeja on 25/02/2016.
 */
public class ReturnTitleActionTest {
	private LibraryAction returnBookAction;
	private LibraryAction returnMovieAction;
    @InjectMocks
	private Library library;

	@Before
	public void setup() {
		library = new Library(true, new UserBase(), new Actions(), new LibraryArchive());
		returnBookAction = new ReturnTitleAction<>(LibraryBook.class);
		returnMovieAction = new ReturnTitleAction<>(LibraryMovie.class);
	}

	@Test
	public void canReturnCheckedOutBook() {
		Title hp = library.getLibraryTitleById("HP");
		hp.setCheckedOut(true);
		Assert.assertEquals(TestConfig.THANK_YOU_FOR_RETURNING_BOOK, returnBookAction.execute(library, "HP"));
		Assert.assertEquals(false, library.getLibraryTitleById("HP").isCheckedOut());
	}

	@Test
	public void canReturnCheckedOutMovie() {
		Title titanic = library.getLibraryTitleById("TI");
		titanic.setCheckedOut(true);
		Assert.assertEquals("Thank you for returning the movie.\n", returnMovieAction.execute(library, "TI"));
		Assert.assertEquals(false, library.getLibraryTitleById("TI").isCheckedOut());
	}

	@Test
	public void cannotReturnCheckedInBook() {
		Title hp = library.getLibraryTitleById("HP");
		hp.setCheckedOut(false);
		Assert.assertEquals(TestConfig.NOT_A_VALID_BOOK_TO_RETURN, returnBookAction.execute(library, "HP"));
		Assert.assertEquals(false, library.getLibraryTitleById("HP").isCheckedOut());
	}

	@Test
	public void cannotReturnCheckedInMovie() {
		Title titanic = library.getLibraryTitleById("TI");
		titanic.setCheckedOut(false);
		Assert.assertEquals("That is not a valid movie to return.\n", returnMovieAction.execute(library, "TI"));
		Assert.assertEquals(false, library.getLibraryTitleById("TI").isCheckedOut());
	}

	@Test
	public void cannotReturnNonExistentBook() {
		Assert.assertEquals("Title not found.\n", returnBookAction.execute(library, "BOOKDOESNTEXIST"));
	}

	@Test
	public void cannotReturnNonExistentMovie() {
		Assert.assertEquals("Title not found.\n", returnMovieAction.execute(library, "MOVIEDOESNTEXIST"));
	}

	@Test
	public void invalidArgumentsForBook() {
		Assert.assertEquals("Please enter a title ID with your request.\n", returnBookAction.execute(library));
	}

	@Test
	public void invalidArgumentsForMovie() {
		Assert.assertEquals("Please enter a title ID with your request.\n", returnMovieAction.execute(library));
	}
}
