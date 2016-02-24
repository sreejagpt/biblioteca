package com.twu.com.twu.actions;

import com.twu.library.Library;
import com.twu.library.LibraryBook;

import java.util.StringJoiner;
import java.util.stream.Collector;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class ListBooksAction implements LibraryAction {

	@Override
	public String execute(Library model, Object... args) {
		Collector<LibraryBook, StringJoiner, String> bookListCollector =
				Collector.of(
						() -> new StringJoiner("\n"),          // supplier
						(joiner, libraryBook) -> joiner.add(libraryBook.toString()),  // accumulator
						StringJoiner::merge,               // combiner
						StringJoiner::toString);

		return model.getLibraryBooks().stream()
				.filter(libraryBook -> !libraryBook.isCheckedOut())
				.collect(bookListCollector);
	}

}
