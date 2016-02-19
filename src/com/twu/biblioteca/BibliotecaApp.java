package com.twu.biblioteca;

import com.twu.library.LibraryController;

import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
        LibraryController controller = new LibraryController();
        while(controller.isOn()) {
            controller.displayMenu();
            Scanner sc = new Scanner(System.in);
            controller.runCommand(sc.nextInt());
        }
    }
}
