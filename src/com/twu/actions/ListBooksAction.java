package com.twu.actions;

import com.twu.library.Library;
import com.twu.library.titles.LibraryBook;

import java.util.StringJoiner;
import java.util.stream.Collector;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class ListBooksAction extends LibraryAction {

	public ListBooksAction(Library library) {
		super(library);
	}

	@Override
	//Return all books that are not checked out
	public String execute(Object... args) {
		Collector<LibraryBook, StringJoiner, String> bookListCollector =
				Collector.of(
						() -> new StringJoiner("\n"),          // supplier
						(joiner, libraryBook) -> joiner.add(libraryBook.toString()),  // accumulator
						StringJoiner::merge,               // combiner
						StringJoiner::toString);

		return getLibrary().getLibraryBooks().stream()
				.filter(libraryBook -> !libraryBook.isCheckedOut())
				.collect(bookListCollector);
	}

	@Override
	public String getActionDescription() {
		return "List Books";
	}

}
