package com.twu.biblioteca;

import com.twu.actions.*;
import com.twu.library.BibliotecaController;
import com.twu.library.Library;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class BibliotecaTest {

	private Library library;
	private BibliotecaController ctrl = new BibliotecaController();

	@Before
	public void setup() {
		library = new Library(true);

	}

	@Test
	public void getWelcomeMessage() {
		LibraryAction welcomeAction = new WelcomeAction(library);
		Assert.assertEquals("Welcome to Biblioteca. Application is now ready to use.\n", welcomeAction.execute(library));
	}

	@Test
	public void getListOfBooks() {
		LibraryAction listBooksAction = new ListBooksAction(library);
		Assert.assertEquals("[id='HP', name='Harry Potter 1', author='J.K Rowling', yearOfPublication=1991]\n" +
		"[id='HW', name='Henri's Walk to Paris', author='Saul Bass', yearOfPublication=1964]", listBooksAction
				.execute(library));
	}

	@Test
	public void getMenuOptions() {
		LibraryAction displayMenuAction = new DisplayMenuAction(library);
		Assert.assertEquals("1) List Books\n" +
				"2) Checkout Book [ID]\n" +
				"3) Return Book [ID]\n" +
				"99) Quit\n", displayMenuAction.execute(library));
	}

	@Test
	public void quitSystemSuccessfully() {
		LibraryAction quitAction = new QuitAction(library);
		Assert.assertEquals("You have successfully quit.\n", quitAction.execute(library));
		Assert.assertEquals(false, library.isEnabled());
	}

	@Test
	public void wrongInputGivenDisplaysMessage() {
		BibliotecaController ctrl = new BibliotecaController();
		Assert.assertEquals("Select a valid option!\n", ctrl.runCommand(-100));
	}

	@Test
	public void controllerListsBooksWhenInputIs1() {
		BibliotecaController ctrl = new BibliotecaController();
		Assert.assertEquals("[id='HP', name='Harry Potter 1', author='J.K Rowling', yearOfPublication=1991]\n" +
				"[id='HW', name='Henri's Walk to Paris', author='Saul Bass', yearOfPublication=1964]", ctrl
				.runCommand(1));
	}

	@Test
	public void controllerQuitsWhenInputIs99() {
		BibliotecaController ctrl = new BibliotecaController();
		Assert.assertEquals("You have successfully quit.\n", ctrl.runCommand(99));
		Assert.assertEquals(false, ctrl.isEnabled());
	}

	@Test
	public void checkOutBookWhenInputIs2() {
		BibliotecaController ctrl = new BibliotecaController();
		Assert.assertEquals("Thank you! Enjoy the book.\n", ctrl.runCommand(2, "HP"));
		Assert.assertEquals("[id='HW', name='Henri's Walk to Paris', author='Saul Bass', yearOfPublication=1964]",
				ctrl.runCommand(1));
	}

	@Test
	public void cannotCheckoutNonExistentBookWhenInputIs2() {
		BibliotecaController ctrl = new BibliotecaController();
		Assert.assertEquals("That book is not available.\n", ctrl.runCommand(2, "ID Doesn't Exist"));
		Assert.assertEquals("[id='HP', name='Harry Potter 1', author='J.K Rowling', yearOfPublication=1991]\n" +
						"[id='HW', name='Henri's Walk to Paris', author='Saul Bass', yearOfPublication=1964]",
				ctrl.runCommand(1));
	}

	@Test
	public void returnBookWhenInputIs3() {
		BibliotecaController ctrl = new BibliotecaController();
		//first, checkout book
		Assert.assertEquals("Thank you! Enjoy the book.\n", ctrl.runCommand(2, "HP"));
		Assert.assertEquals("[id='HW', name='Henri's Walk to Paris', author='Saul Bass', yearOfPublication=1964]",
				ctrl.runCommand(1));

		//now return book
		Assert.assertEquals("Thank you for returning the book.\n", ctrl.runCommand(3, "HP"));
		Assert.assertEquals("[id='HP', name='Harry Potter 1', author='J.K Rowling', yearOfPublication=1991]\n" +
						"[id='HW', name='Henri's Walk to Paris', author='Saul Bass', yearOfPublication=1964]",
				ctrl.runCommand(1));
	}

	@Test
	public void cannotReturnNonExistentBook() {
		BibliotecaController ctrl = new BibliotecaController();
		Assert.assertEquals("That is not a valid book to return.\n", ctrl.runCommand(3, "Doesn't exist"));
		Assert.assertEquals("[id='HP', name='Harry Potter 1', author='J.K Rowling', yearOfPublication=1991]\n" +
						"[id='HW', name='Henri's Walk to Paris', author='Saul Bass', yearOfPublication=1964]",
				ctrl.runCommand(1));
	}

	@Test
	public void cannotReturnBookThatIsNotCheckedOut() {
		BibliotecaController ctrl = new BibliotecaController();
		Assert.assertEquals("That is not a valid book to return.\n", ctrl.runCommand(3, "HP"));
		Assert.assertEquals("[id='HP', name='Harry Potter 1', author='J.K Rowling', yearOfPublication=1991]\n" +
						"[id='HW', name='Henri's Walk to Paris', author='Saul Bass', yearOfPublication=1964]",
				ctrl.runCommand(1));
	}
}

