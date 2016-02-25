package com.twu.actions;

import com.twu.library.Library;
import com.twu.library.titles.LibraryBook;
import com.twu.library.titles.LibraryMovie;
import com.twu.library.titles.Title;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.TestUtil;

/**
 * Created by Sreeja on 25/02/2016.
 */
public class ReturnTitleActionTest {
	private LibraryAction returnBookAction;
	private LibraryAction returnMovieAction;
	private Library library;
	private TestUtil util;

	@Before
	public void setup() {
		library = new Library(true);
		returnBookAction = new ReturnTitleAction<>(library, LibraryBook.class);
		returnMovieAction = new ReturnTitleAction<>(library, LibraryMovie.class);
		util = new TestUtil();
	}

	@Test
	public void canReturnCheckedOutBook() {
		Title hp = library.getLibraryTitleById("HP");
		hp.setCheckedOut(true);
		Assert.assertEquals("Thank you for returning the book.\n", returnBookAction.execute("HP"));
		Assert.assertEquals(false, library.getLibraryTitleById("HP").isCheckedOut());
	}

	@Test
	public void canReturnCheckedOutMovie() {
		Title titanic = library.getLibraryTitleById("TI");
		titanic.setCheckedOut(true);
		Assert.assertEquals("Thank you for returning the movie.\n", returnMovieAction.execute("TI"));
		Assert.assertEquals(false, library.getLibraryTitleById("TI").isCheckedOut());
	}

	@Test
	public void cannotReturnCheckedInBook() {
		Title hp = library.getLibraryTitleById("HP");
		hp.setCheckedOut(false);
		Assert.assertEquals("That is not a valid book to return.\n", returnBookAction.execute("HP"));
		Assert.assertEquals(false, library.getLibraryTitleById("HP").isCheckedOut());
	}

	@Test
	public void cannotReturnCheckedInMovie() {
		Title titanic = library.getLibraryTitleById("TI");
		titanic.setCheckedOut(false);
		Assert.assertEquals("That is not a valid movie to return.\n", returnMovieAction.execute("TI"));
		Assert.assertEquals(false, library.getLibraryTitleById("TI").isCheckedOut());
	}

	@Test
	public void cannotReturnNonExistentBook() {
		Assert.assertEquals("Title not found.\n", returnBookAction.execute("BOOKDOESNTEXIST"));
	}

	@Test
	public void cannotReturnNonExistentMovie() {
		Assert.assertEquals("Title not found.\n", returnMovieAction.execute("MOVIEDOESNTEXIST"));
	}

	@Test
	public void invalidArgumentsForBook() {
		Assert.assertEquals("Please enter a title ID with your request.\n", returnBookAction.execute());
	}

	@Test
	public void invalidArgumentsForMovie() {
		Assert.assertEquals("Please enter a title ID with your request.\n", returnMovieAction.execute());
	}
}
