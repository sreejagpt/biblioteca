package com.twu.actions;

import com.twu.library.Library;
import com.twu.library.titles.LibraryBook;
import com.twu.library.titles.Title;

import java.lang.reflect.Type;
import java.util.StringJoiner;
import java.util.stream.Collector;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class ListTitlesAction extends LibraryAction {

	private final Type type;

	public ListTitlesAction(Library library, Class<? extends Title> type) {
		super(library);
		this.type = type;
	}

	@Override
	//Return all books that are not checked out
	public String execute(Object... args) {
		Collector<Title, StringJoiner, String> titleListCollector =
				Collector.of(
						() -> new StringJoiner("\n"),          // supplier
						(joiner, title) -> joiner.add(title.toString()),  // accumulator
						StringJoiner::merge,               // combiner
						StringJoiner::toString);

		return getLibrary().getTitlesByType(type).stream()
				.filter(title -> !title.isCheckedOut())
				.collect(titleListCollector);
	}

	@Override
	public String getActionDescription() {
		if (type.equals(LibraryBook.class)) {
			return "List Books";
		} else {
			return "List Movies";
		}
	}
}
