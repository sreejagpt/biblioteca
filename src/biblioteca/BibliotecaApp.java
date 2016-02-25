package biblioteca;

import com.twu.library.BibliotecaController;

import java.util.Scanner;

class BibliotecaApp {

    public static void main(String[] args) {
        BibliotecaController controller = new BibliotecaController();
        Scanner sc = new Scanner(System.in);
        while (controller.isEnabled()) {
            System.out.println("==========================");
            System.out.println(controller.displayMenu());
            int option = sc.nextInt();
            if (option == 2 || option == 3) {
                System.out.println(controller.runCommand(option, sc.nextLine().trim()));
            } else if (option == 7) {
                System.out.println(controller.runCommand(option, sc.nextLine().trim()));
            } else {
                System.out.println(controller.runCommand(option));
            }
        }
        sc.close();
    }
}
