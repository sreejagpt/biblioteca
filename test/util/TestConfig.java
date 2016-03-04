package util;

import com.twu.actions.*;
import com.twu.library.titles.LibraryBook;
import com.twu.library.titles.LibraryMovie;
import com.twu.library.titles.MovieRating;
import com.twu.library.titles.Title;
import data.Constants;

import java.time.Year;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sreeja on 3/03/2016.
 */
public class TestConfig {
    public static final String CHECKOUT_THANK_YOU_BOOK_MESSAGE = "Thank you! Enjoy the book.\n";
    public static final String MESSAGE_QUIT_SUCCESSFUL = "You have successfully quit.\n";
    public static final String APPLICATION_READY = "Welcome to Biblioteca. Application is now ready to use.\n";
    public static final String NOT_A_VALID_BOOK_TO_RETURN = "That is not a valid book to return.\n";
    public static final String THANK_YOU_FOR_RETURNING_BOOK = "Thank you for returning the book.\n";
    public static final String TITLE_NOT_FOUND = "Title not found.\n";
    public static final Map<Integer, LibraryAction> actionMapper = mapActions();
    public static Map<String, Title> allUncheckedOutLibraryTitles = addUncheckedOutTitles();
    public static Map<String, Title> allCheckedAndUncheckedOutLibraryTitles = addCheckedAndUncheckedOutTitles();

    private static Map<String, Title> addCheckedAndUncheckedOutTitles() {
        Map<String, Title> mapper = new HashMap<>();
        mapper.put("HP", new LibraryBook("HP", "Harry Potter 1", Year.of(1991), "J.K Rowling", true));
        mapper.put("HW", new LibraryBook("HW", "Henri's Walk to Paris", Year.of(1964), "Saul Bass", false));
        mapper.put("TI", new LibraryMovie("TI", "Titanic", Year.of(1997), "James Cameron",
                MovieRating.toRating(3), true));
        mapper.put("SH", new LibraryMovie("SH", "Shrek", Year.of(2001), "Andrew Adamson",
                MovieRating.toRating(8), false));
        mapper.put("CO", new LibraryMovie("CO", "Cowspiracy", Year.of(2014), "Kip Andersen",
                MovieRating.toRating(9), false));
        return mapper;
    }

    private static Map<String, Title> addUncheckedOutTitles() {
        Map<String, Title> mapper = new HashMap<>();
        mapper.put("HP", new LibraryBook("HP", "Harry Potter 1", Year.of(1991), "J.K Rowling", false));
        mapper.put("HW", new LibraryBook("HW", "Henri's Walk to Paris", Year.of(1964), "Saul Bass", false));
        mapper.put("TI", new LibraryMovie("TI", "Titanic", Year.of(1997), "James Cameron",
                MovieRating.toRating(3), false));
        mapper.put("SH", new LibraryMovie("SH", "Shrek", Year.of(2001), "Andrew Adamson",
                MovieRating.toRating(8), false));
        mapper.put("CO", new LibraryMovie("CO", "Cowspiracy", Year.of(2014), "Kip Andersen",
                MovieRating.toRating(9), false));
        return mapper;
    }

    private static Map<Integer, LibraryAction> mapActions() {
        Map<Integer, LibraryAction> mapper = new HashMap<>();
        mapper.put(Constants.LIST_BOOKS_ACTION, new ListTitlesAction<>(LibraryBook.class));
        mapper.put(Constants.CHECKOUT_BOOK_ACTION, new CheckoutTitleAction<>(LibraryBook.class));
        mapper.put(Constants.RETURN_BOOK_ACTION, new ReturnTitleAction<>(LibraryBook.class));
        mapper.put(Constants.LIST_MOVIES_ACTION, new ListTitlesAction<>(LibraryMovie.class));
        mapper.put(Constants.CHECKOUT_MOVIE_ACTION, new CheckoutTitleAction<>(LibraryMovie.class));
        mapper.put(Constants.RETURN_MOVIE_ACTION, new ReturnTitleAction<>(LibraryMovie.class));
        mapper.put(Constants.QUIT_BOOKS_ACTION, new QuitAction());
        mapper.put(Constants.LOGIN_ACTION, new LoginAction());
        mapper.put(Constants.PRINT_USER_DETAILS_ACTION, new PrintUserDetailsAction());
        return mapper;
    }
}
