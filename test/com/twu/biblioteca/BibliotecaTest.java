package com.twu.biblioteca;

import com.twu.com.twu.actions.*;
import com.twu.library.BibliotecaController;
import com.twu.library.BibliotecaModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class BibliotecaTest {

	private BibliotecaModel model;

	@Before
	public void setup() {
		model = new BibliotecaModel(true);
	}

	@Test
	public void getWelcomeMessage() {
		LibraryAction welcomeAction = new WelcomeAction();
		Assert.assertEquals("Welcome to Biblioteca. Application is now ready to use.\n", welcomeAction.execute(model));
	}

	@Test
	public void getListOfBooks() {
		LibraryAction listBooksAction = new ListBooksAction();
		Assert.assertEquals("[id='HP', name='Harry Potter 1', author='J.K Rowling', yearOfPublication=1991]\n" +
		"[id='HW', name='Henri's Walk to Paris', author='Saul Bass', yearOfPublication=1964]", listBooksAction
				.execute(model));
	}

	@Test
	public void getMenuOptions() {
		LibraryAction displayMenuAction = new DisplayMenuAction();
		Assert.assertEquals("1) List Books\n2) Quit\n", displayMenuAction.execute(model));
	}

	@Test
	public void quitSystemSuccessfully() {
		LibraryAction quitAction = new QuitAction();
		Assert.assertEquals("You have successfully quit.\n", quitAction.execute(model));
		Assert.assertEquals(false, model.isOn());
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
		Assert.assertEquals(false, ctrl.isOn());
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

