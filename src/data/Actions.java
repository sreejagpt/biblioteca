package data;

import com.twu.actions.*;
import com.twu.library.Library;
import com.twu.library.titles.LibraryBook;
import com.twu.library.titles.LibraryMovie;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sreeja on 3/03/2016.
 */
public class Actions {
    public static final int LIST_BOOKS_ACTION = 1;
    public static final int CHECKOUT_BOOK_ACTION = 2;
    public static final int RETURN_BOOK_ACTION = 3;
    public static final int LIST_MOVIES_ACTION = 4;
    public static final int CHECKOUT_MOVIE_ACTION = 5;
    public static final int RETURN_MOVIE_ACTION = 6;
    public static final int QUIT_BOOKS_ACTION = 99;
    public static final int LOGIN_ACTION = 7;
    public static final int PRINT_USER_DETAILS_ACTION = 8;
    private static final Map<Integer, LibraryAction> actionMapper = new HashMap<>();

    public static void mapActions(Library library) {
        actionMapper.put(LIST_BOOKS_ACTION, new ListTitlesAction<>(library, LibraryBook.class));
        actionMapper.put(CHECKOUT_BOOK_ACTION, new CheckoutTitleAction<>(library, LibraryBook.class));
        actionMapper.put(RETURN_BOOK_ACTION, new ReturnTitleAction<>(library, LibraryBook.class));
        actionMapper.put(LIST_MOVIES_ACTION, new ListTitlesAction<>(library, LibraryMovie.class));
        actionMapper.put(CHECKOUT_MOVIE_ACTION, new CheckoutTitleAction<>(library, LibraryMovie.class));
        actionMapper.put(RETURN_MOVIE_ACTION, new ReturnTitleAction<>(library, LibraryMovie.class));
        actionMapper.put(QUIT_BOOKS_ACTION, new QuitAction(library));
        actionMapper.put(LOGIN_ACTION, new LoginAction(library));
        actionMapper.put(PRINT_USER_DETAILS_ACTION, new PrintUserDetailsAction(library));
    }

    public static Map<Integer, LibraryAction> getActions() {
        return actionMapper;
    }
}
