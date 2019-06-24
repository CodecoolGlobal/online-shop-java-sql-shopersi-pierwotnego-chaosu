package view;

import model.controller.FileReaderForDispaly;

public class Display {


    public static void showMenu(String menuName) {

        System.out.println("Choose an option: ");
        FileReaderForDispaly.showMenu(menuName).forEach(System.out::println);
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
