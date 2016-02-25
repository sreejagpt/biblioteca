package biblioteca;

import com.twu.actions.*;
import com.twu.library.BibliotecaController;
import com.twu.library.Library;
import com.twu.library.titles.LibraryBook;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import util.TestUtil;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class BibliotecaTest {

	private Library library;
	private BibliotecaController ctrl;
	private TestUtil util;

	@Before
	public void setup() {
		library = new Library(true);
		ctrl = new BibliotecaController();
		util = new TestUtil();
	}

	@Test
	public void getWelcomeMessage() {
		LibraryAction welcomeAction = new WelcomeAction(library);
		Assert.assertEquals("Welcome to Biblioteca. Application is now ready to use.\n", welcomeAction.execute(library));
	}

	@Test
	public void getListOfBooks() {
		LibraryAction listBooksAction = new ListTitlesAction(library, LibraryBook.class);
		Assert.assertEquals(util.readFile("booklist.txt", true), listBooksAction
				.execute(library));
	}

	@Test
	public void getMenuOptions() {
		LibraryAction displayMenuAction = new DisplayMenuAction(library);
		Assert.assertEquals(util.readFile("printedmenu.txt", false), displayMenuAction.execute(library));
	}

	@Test
	public void quitSystemSuccessfully() {
		LibraryAction quitAction = new QuitAction(library);
		Assert.assertEquals("You have successfully quit.\n", quitAction.execute(library));
		Assert.assertEquals(false, library.isEnabled());
	}

	@Test
	public void shouldDisplayInvalidMessage() {
		Assert.assertEquals("Select a valid option!\n", ctrl.runCommand(-100));
	}

	@Test
	public void controllerListsBooksWhenInputIs1() {
		Assert.assertEquals(util.readFile("booklist.txt", true), ctrl
				.runCommand(1));
	}

	@Test
	public void controllerQuitsWhenInputIs99() {
		Assert.assertEquals("You have successfully quit.\n", ctrl.runCommand(99));
		Assert.assertEquals(false, ctrl.isEnabled());
	}

	@Test
	public void checkOutBookWhenInputIs2() {
		Assert.assertEquals("Thank you! Enjoy the book.\n", ctrl.runCommand(2, "HP"));
		Assert.assertEquals("[id='HW', name='Henri's Walk to Paris', author='Saul Bass', yearOfPublication=1964]",
				ctrl.runCommand(1));
	}

	@Test
	public void cannotCheckoutNonExistentBookWhenInputIs2() {
		Assert.assertEquals("Title not found.\n", ctrl.runCommand(2, "ID Doesn't Exist"));
		Assert.assertEquals(util.readFile("booklist.txt", true), ctrl.runCommand(1));
	}

	@Test
	public void returnBookWhenInputIs3() {
		//first, checkout book
		Assert.assertEquals("Thank you! Enjoy the book.\n", ctrl.runCommand(2, "HP"));
		Assert.assertEquals("[id='HW', name='Henri's Walk to Paris', author='Saul Bass', yearOfPublication=1964]",
				ctrl.runCommand(1));

		//now return book
		Assert.assertEquals("Thank you for returning the book.\n", ctrl.runCommand(3, "HP"));
		Assert.assertEquals(util.readFile("booklist.txt", true), ctrl.runCommand(1));
	}

	@Test
	public void cannotReturnNonExistentBook() {
		Assert.assertEquals("Title not found.\n", ctrl.runCommand(3, "Doesn't exist"));
		Assert.assertEquals(util.readFile("booklist.txt", true), ctrl.runCommand(1));
	}

	@Test
	public void cannotReturnBookThatIsNotCheckedOut() {
		Assert.assertEquals("That is not a valid book to return.\n", ctrl.runCommand(3, "HP"));
		Assert.assertEquals(util.readFile("booklist.txt", true), ctrl.runCommand(1));
	}
}

