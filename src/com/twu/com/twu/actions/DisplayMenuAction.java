package com.twu.com.twu.actions;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class DisplayMenuAction implements LibraryAction {
	@Override
	public String execute() {
		return "1) List Books\n2) Quit";
	}
}
