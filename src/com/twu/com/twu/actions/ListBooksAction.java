package com.twu.com.twu.actions;

import com.twu.library.LibraryBook;

import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collector;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class ListBooksAction implements LibraryAction {
	private static final List<LibraryBook> libraryBooks = Arrays.asList(
			new LibraryBook("HP", "Harry Potter 1", Year.of(1991), "J.K Rowling"),
			new LibraryBook("HW", "Henri's Walk to Paris", Year.of(1964),  "Saul Bass"));

	@Override
	public String execute() {
		Collector<LibraryBook, StringJoiner, String> bookListCollector =
				Collector.of(
						() -> new StringJoiner("\n"),          // supplier
						(joiner, libraryBook) -> joiner.add(libraryBook.toString()),  // accumulator
						StringJoiner::merge,               // combiner
						StringJoiner::toString);

		return libraryBooks.stream()
				.collect(bookListCollector);
	}
}
