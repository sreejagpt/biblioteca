package data;

import com.twu.library.titles.LibraryBook;
import com.twu.library.titles.LibraryMovie;
import com.twu.library.titles.MovieRating;
import com.twu.library.titles.Title;

import java.time.Year;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sreeja on 3/03/2016.
 */
public class LibraryArchive {
    private final Map<String, Title> libraryTitles = new HashMap<>();

    public LibraryArchive() {
        libraryTitles.put("HP", new LibraryBook("HP", "Harry Potter 1", Year.of(1991), "J.K Rowling", false));
        libraryTitles.put("HW", new LibraryBook("HW", "Henri's Walk to Paris", Year.of(1964), "Saul Bass", false));
        libraryTitles.put("TI", new LibraryMovie("TI", "Titanic", Year.of(1997), "James Cameron",
                MovieRating.toRating(3), false));
        libraryTitles.put("SH", new LibraryMovie("SH", "Shrek", Year.of(2001), "Andrew Adamson",
                MovieRating.toRating(8), false));
        libraryTitles.put("CO", new LibraryMovie("CO", "Cowspiracy", Year.of(2014), "Kip Andersen",
                MovieRating.toRating(9), false));
    }

    public Map<String, Title> getLibraryTitles() {
        return libraryTitles;
    }
}
