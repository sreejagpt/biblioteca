import com.twu.actions.*;
import com.twu.library.BibliotecaController;
import com.twu.library.Library;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by Sreeja on 19/02/2016.
 */
public class BibliotecaTest {

	private Library library;
	private BibliotecaController ctrl = new BibliotecaController();

	@Before
	public void setup() {
		library = new Library(true);
		ctrl = new BibliotecaController();
	}

	public String readFile(String filepath) {
		InputStream testFile = getClass().getClassLoader().getResourceAsStream(filepath);
		Scanner sc = new Scanner(testFile);
		String fileToString = "";
		while (sc.hasNextLine()) {
			fileToString += sc.nextLine() + "\n";
		}
		return fileToString.trim();
	}

	@Test
	public void getWelcomeMessage() {
		LibraryAction welcomeAction = new WelcomeAction(library);
		Assert.assertEquals("Welcome to Biblioteca. Application is now ready to use.\n", welcomeAction.execute(library));
	}

	@Test
	public void getListOfBooks() {
		LibraryAction listBooksAction = new ListBooksAction(library);
		Assert.assertEquals(readFile("booklist.txt"), listBooksAction
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
	public void shouldDisplayInvalidMessage() {
		Assert.assertEquals("Select a valid option!\n", ctrl.runCommand(-100));
	}

	@Test
	public void controllerListsBooksWhenInputIs1() {
		Assert.assertEquals(readFile("booklist.txt"), ctrl
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
		Assert.assertEquals("That book is not available.\n", ctrl.runCommand(2, "ID Doesn't Exist"));
		Assert.assertEquals(readFile("booklist.txt"), ctrl.runCommand(1));
	}

	@Test
	public void returnBookWhenInputIs3() {
		//first, checkout book
		Assert.assertEquals("Thank you! Enjoy the book.\n", ctrl.runCommand(2, "HP"));
		Assert.assertEquals("[id='HW', name='Henri's Walk to Paris', author='Saul Bass', yearOfPublication=1964]",
				ctrl.runCommand(1));

		//now return book
		Assert.assertEquals("Thank you for returning the book.\n", ctrl.runCommand(3, "HP"));
		Assert.assertEquals(readFile("booklist.txt"), ctrl.runCommand(1));
	}

	@Test
	public void cannotReturnNonExistentBook() {
		Assert.assertEquals("That is not a valid book to return.\n", ctrl.runCommand(3, "Doesn't exist"));
		Assert.assertEquals(readFile("booklist.txt"), ctrl.runCommand(1));
	}

	@Test
	public void cannotReturnBookThatIsNotCheckedOut() {
		Assert.assertEquals("That is not a valid book to return.\n", ctrl.runCommand(3, "HP"));
		Assert.assertEquals(readFile("booklist.txt"), ctrl.runCommand(1));
	}
}

