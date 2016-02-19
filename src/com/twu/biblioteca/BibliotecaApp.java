package com.twu.biblioteca;

import com.twu.library.BibliotecaController;

import java.util.Scanner;

public class BibliotecaApp {

    public static void main(String[] args) {
        BibliotecaController controller = new BibliotecaController();
        while(controller.isOn()) {
            controller.displayMenu();
            Scanner sc = new Scanner(System.in);
            System.out.println(controller.runCommand(sc.nextInt()));
        }
    }
}
