package biblioteca;

import com.twu.library.BibliotecaController;
import com.twu.library.Library;
import data.Actions;
import data.Constants;
import data.LibraryArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import util.TestConfig;
import util.TestUtil;

import static org.mockito.Mockito.when;

/**
 * Created by Sreeja on 19/02/2016.
 */
@RunWith(MockitoJUnitRunner.class)
public class BibliotecaTest {
    @Mock
    LibraryArchive archive;
    @Mock
    private Actions actions;
    @InjectMocks
    private Library library;
    private BibliotecaController ctrl;

	private TestUtil util;

	@Before
	public void setup() {
        when(archive.getLibraryTitles()).thenReturn(TestConfig.getAllUncheckedOutLibraryTitles());
        when(actions.getActions()).thenReturn(TestConfig.actionMapper);
        ctrl = new BibliotecaController(library);
		util = new TestUtil();
	}

	@Test
	public void inputtingBadCommandCallsInvalid() {
        Assert.assertEquals(Constants.INVALID_OPTION_MESSAGE, ctrl.runCommand(-1001));
	}

	@Test
	public void controllerListsBooksWhenInputIs1() {
		Assert.assertEquals(util.readFile("booklist.txt", true), ctrl
				.runCommand(Constants.LIST_BOOKS_ACTION));
	}

	@Test
	public void controllerQuitsWhenInputIs99() {
		Assert.assertEquals(TestConfig.MESSAGE_QUIT_SUCCESSFUL, ctrl.runCommand(99));
		Assert.assertEquals(false, ctrl.isEnabled());
	}

	@Test
	public void checkOutBookWhenInputIs2() {
		Assert.assertEquals(TestConfig.CHECKOUT_THANK_YOU_BOOK_MESSAGE, ctrl.runCommand(2, "HP"));
		Assert.assertEquals("[id='HW', name='Henri's Walk to Paris', author='Saul Bass', yearOfPublication=1964]",
				ctrl.runCommand(1));
	}

	@Test
	public void cannotCheckoutNonExistentBookWhenInputIs2() {
        Assert.assertEquals(TestConfig.TITLE_NOT_FOUND, ctrl.runCommand(2, "ID Doesn't Exist"));
        Assert.assertEquals(util.readFile("booklist.txt", true), ctrl.runCommand(1));
	}

	@Test
	public void returnBookWhenInputIs3() {
        when(archive.getLibraryTitles()).thenReturn(TestConfig.getAllCheckedAndUncheckedOutLibraryTitles());
        //now return book
		Assert.assertEquals(TestConfig.THANK_YOU_FOR_RETURNING_BOOK, ctrl.runCommand(3, "HP"));
		Assert.assertEquals(util.readFile("booklist.txt", true), ctrl.runCommand(1));
	}

	@Test
	public void returningNonExistentBookShowsErrorMsg() {
        Assert.assertEquals(TestConfig.TITLE_NOT_FOUND, ctrl.runCommand(3, "Doesn't exist"));
        Assert.assertEquals(util.readFile("booklist.txt", true), ctrl.runCommand(1));
	}

	@Test
	public void cannotReturnBookThatIsNotCheckedOut() {
		Assert.assertEquals(TestConfig.NOT_A_VALID_BOOK_TO_RETURN, ctrl.runCommand(3, "HP"));
		Assert.assertEquals(util.readFile("booklist.txt", true), ctrl.runCommand(1));
	}
}

