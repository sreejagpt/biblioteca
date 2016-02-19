package com.twu.com.twu.actions;

import com.twu.library.BibliotecaModel;
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

	@Override
	public String execute(BibliotecaModel model) {
		Collector<LibraryBook, StringJoiner, String> bookListCollector =
				Collector.of(
						() -> new StringJoiner("\n"),          // supplier
						(joiner, libraryBook) -> joiner.add(libraryBook.toString()),  // accumulator
						StringJoiner::merge,               // combiner
						StringJoiner::toString);

		return model.getLibraryBooks().stream()
				.collect(bookListCollector);
	}
}
