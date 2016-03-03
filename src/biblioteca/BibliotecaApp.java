package biblioteca;

import com.twu.library.BibliotecaController;
import com.twu.library.Library;
import data.Actions;
import data.LibraryArchive;
import data.UserBase;

import java.util.Scanner;

class BibliotecaApp {

    public static void main(String[] args) {
        BibliotecaController controller = new BibliotecaController(new Library(true, new UserBase(), new Actions(),
                new LibraryArchive()));
        Scanner sc = new Scanner(System.in);
        while (controller.isEnabled()) {
            System.out.println("==========================");
            System.out.println(controller.displayMenu());
            int option = sc.nextInt();
            System.out.println(controller.runCommand(option, sc.nextLine().trim()));
        }
        sc.close();
    }
}
