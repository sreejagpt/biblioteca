package com.twu.actions;

import com.twu.library.Library;
import com.twu.library.titles.LibraryBook;
import com.twu.library.titles.Title;

import java.util.StringJoiner;
import java.util.stream.Collector;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class ListTitlesAction<T extends Title> extends LibraryAction {

	public ListTitlesAction(Class<T> type) {
		super(type);
	}

	@Override
	//Return all books that are not checked out
	public String execute(Library library, Object... args) {
		Collector<Title, StringJoiner, String> titleListCollector =
				Collector.of(
						() -> new StringJoiner("\n"),          // supplier
						(joiner, title) -> joiner.add(title.toString()),  // accumulator
						StringJoiner::merge,               // combiner
						StringJoiner::toString);

        return library.getUncheckedOutTitlesOfType(getType()).stream()
                .collect(titleListCollector);
	}

	@Override
	public String getActionDescription() {
		if (getType().equals(LibraryBook.class)) {
			return "List Books";
		} else {
			return "List Movies";
		}
	}

	@Override
	public boolean onlyAvailableWhenLoggedIn() {
		return false;
	}
}
