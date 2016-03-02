package data;

import com.twu.library.LibraryUser;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sreeja on 3/03/2016.
 */
public class Users {
    private static final Map<String, LibraryUser> userStore = new HashMap<>();
    private static final Map<String, String> passwordStore = new HashMap<>();

    public static void mapUsersAndPasswords() {
        userStore.put("123-4567", new LibraryUser("123-4567", "Sreeja", "sreeja@email.com", "23452345"));
        userStore.put("000-1234", new LibraryUser("000-1234", "Nicholas", "nicky@nicholas.com", "45662222"));
        userStore.put("111-1334", new LibraryUser("111-1334", "Joan", "joan@nicholas.com", "45664646"));

        passwordStore.put("123-4567", "password1");
        passwordStore.put("000-1234", "password2");
        passwordStore.put("111-1334", "password3");
    }

    public static LibraryUser getUserById(String id) {
        return userStore.get(id);
    }

    public static boolean doesPasswordMatch(String libraryId, String password) {
        return passwordStore.get(libraryId).equals(password);
    }
}
