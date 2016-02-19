package com.twu.library;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collector;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class LibraryMenu {

	private static final String WELCOME_MESSAGE = "Welcome to Bibioteca. Application is now ready to use.";
	private static final List<LibraryBook> listOfBooks =
			Arrays.asList(new LibraryBook("HP", "Harry Potter 1", Year.of(1991), "J.K Rowling"),
						new LibraryBook("HW", "Henri's Walk to Paris", Year.of(1964), "Saul Bass"));

	public String getWelcomeMessage() {
		return WELCOME_MESSAGE;
	}

	public String getListOfBooks() {
		Collector<LibraryBook, StringJoiner, String> bookListCollector =
				Collector.of(
						() -> new StringJoiner("\n"),          // supplier
						(joiner, libraryBook) -> joiner.add(libraryBook.toString()),  // accumulator
						StringJoiner::merge,               // combiner
						StringJoiner::toString);

		return listOfBooks.stream()
				.collect(bookListCollector);
	}
}
