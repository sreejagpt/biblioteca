package com.twu.library;

import com.twu.library.titles.LibraryBook;
import com.twu.library.titles.LibraryMovie;
import com.twu.library.titles.MovieRating;
import com.twu.library.titles.Title;
import data.Actions;
import data.LibraryArchive;
import data.UserBase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import util.TestConfig;
import util.TestUtil;

import java.time.Year;
import java.util.Arrays;
import java.util.Collections;

import static org.mockito.Mockito.when;

/**
 * Created by Sreeja on 24/02/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class LibraryTest {
    @Mock
    LibraryUser user;
    @Mock
    private UserBase userBase;
    @Mock
    private Actions actions;
    @Mock
    private LibraryArchive libraryArchive;
    private Library library;
    private TestUtil util;

	@Before
	public void setup() {
        when(actions.getActions()).thenReturn(TestConfig.actionMapper);
        when(libraryArchive.getLibraryTitles()).thenReturn(TestConfig.allUncheckedOutLibraryTitles);
        when(user.getLibraryId()).thenReturn("123-4567");
        when(user.getName()).thenReturn("Mock");
        when(userBase.doesPasswordMatch("123-4567", "password1")).thenReturn(true);
        when(userBase.getUserById("123-4567")).thenReturn(user);
        when(userBase.getUserById("100-4567")).thenReturn(null);
        library = new Library(true, userBase, actions, libraryArchive);
        util = new TestUtil();
    }

	@Test
    public void whenStartupThenInEnabledState() {
        Assert.assertEquals(true, library.isEnabled());
    }

    @Test
    public void settingEnabledChangesLibraryState() {
        library.setEnabled(false);
        Assert.assertEquals(false, library.isEnabled());
    }

    @Test
    public void whenLoggedGetFullMenu() {
        Assert.assertEquals(util.readFile("printedmenu_postlogin.txt", false), library.getMenuList(true));
    }

    @Test
    public void whenLoggedOutGetLimitedMenu() {
        Assert.assertEquals(util.readFile("printedmenu_prelogin.txt", false), library.getMenuList(false));
    }

    @Test
    public void searchingForExistingBookReturnsBook() {
        Assert.assertEquals(TestConfig.allUncheckedOutLibraryTitles.get("HP"), library.getLibraryTitleById("HP"));
    }

    @Test
    public void searchingForNonExistentBookReturnsNull() {
        Assert.assertEquals(null, library.getLibraryTitleById("WUT"));
    }

    @Test
    public void checkoutExistentBookIsSuccessful() {
        Title title = library.updateCheckoutStatus("HP", true);
        Assert.assertNotNull(title);
        Assert.assertEquals(true, title.isCheckedOut());
    }

    @Test
    public void checkoutNonExistentBookReturnsNull() {
        Assert.assertNull(library.updateCheckoutStatus("DOESNTEXIST", true));
    }

    @Test
    public void checkingOutNullIdReturnsNull() {
        Assert.assertNull(library.updateCheckoutStatus(null, true));
    }

    @Test
    public void authenticateWithValidCredentials() {
        Assert.assertNull(library.getCurrentUser());
        Assert.assertFalse(library.isInLoginMode());
        Assert.assertEquals(user, library.authenticateDetails("123-4567", "password1"));
        Assert.assertEquals(user, library.getCurrentUser());
        Assert.assertTrue(library.isInLoginMode());
    }

    @Test
    public void authenticatingWithBadCredsReturnsNull() {
        Assert.assertNull(library.getCurrentUser());
        Assert.assertFalse(library.isInLoginMode());
        Assert.assertEquals(null, library.authenticateDetails("100-4567", "password1"));
        Assert.assertNull(library.getCurrentUser());
        Assert.assertFalse(library.isInLoginMode());
    }

    @Test
    public void getOnlyUncheckedOutBooks() {
        when(libraryArchive.getLibraryTitles()).thenReturn(TestConfig.allCheckedAndUncheckedOutLibraryTitles);
        Assert.assertEquals(Collections.singletonList(new LibraryBook("HW", "Henri's Walk to Paris", Year.of(1964),
                "Saul Bass", false)), library.getUncheckedOutTitlesOfType(LibraryBook.class));
    }

    @Test
    public void getOnlyUncheckedOutMovies() {
        when(libraryArchive.getLibraryTitles()).thenReturn(TestConfig.allCheckedAndUncheckedOutLibraryTitles);
        Assert.assertEquals(Arrays.asList(new LibraryMovie("SH", "Shrek", Year.of(2001), "Andrew Adamson",
                MovieRating.toRating(8), false), new LibraryMovie("CO", "Cowspiracy", Year.of(2014), "Kip Andersen",
                MovieRating.toRating(9), false)), library.getUncheckedOutTitlesOfType(LibraryMovie.class));
    }

    @Test
    public void whenNoCurrentUserNoSuccessfulLoginMessage() {
        Assert.assertNull(library.getCurrentUser());
        Assert.assertNull(library.getSuccessfulLoginMessage());
    }

    @Test
    public void whenCurrentUserExistLoginMessageIsReturned() {
        Assert.assertNull(library.getCurrentUser());
        Assert.assertEquals(user, library.authenticateDetails("123-4567", "password1"));
        Assert.assertEquals(user, library.getCurrentUser());
        Assert.assertEquals("Welcome Mock. You have now logged in.\n", library.getSuccessfulLoginMessage());
    }
}