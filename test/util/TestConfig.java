package util;

import com.twu.actions.*;
import com.twu.library.titles.LibraryBook;
import com.twu.library.titles.LibraryMovie;
import data.Constants;

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
    public static final Map<Integer, LibraryAction> actionMapper = mapActions();

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
