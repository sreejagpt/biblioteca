package data;

import com.twu.actions.*;
import com.twu.library.titles.LibraryBook;
import com.twu.library.titles.LibraryMovie;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sreeja on 3/03/2016.
 */
public class Actions {

    private static final Map<Integer, LibraryAction> actionMapper = new HashMap<>();

    public Actions() {
        actionMapper.put(Constants.LIST_BOOKS_ACTION, new ListTitlesAction<>(LibraryBook.class));
        actionMapper.put(Constants.CHECKOUT_BOOK_ACTION, new CheckoutTitleAction<>(LibraryBook.class));
        actionMapper.put(Constants.RETURN_BOOK_ACTION, new ReturnTitleAction<>(LibraryBook.class));
        actionMapper.put(Constants.LIST_MOVIES_ACTION, new ListTitlesAction<>(LibraryMovie.class));
        actionMapper.put(Constants.CHECKOUT_MOVIE_ACTION, new CheckoutTitleAction<>(LibraryMovie.class));
        actionMapper.put(Constants.RETURN_MOVIE_ACTION, new ReturnTitleAction<>(LibraryMovie.class));
        actionMapper.put(Constants.QUIT_BOOKS_ACTION, new QuitAction());
        actionMapper.put(Constants.LOGIN_ACTION, new LoginAction());
        actionMapper.put(Constants.PRINT_USER_DETAILS_ACTION, new PrintUserDetailsAction());
    }

    public Map<Integer, LibraryAction> getActions() {
        return actionMapper;
    }
}
