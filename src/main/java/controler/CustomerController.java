package controler;


import view.Display;

import java.io.IOException;
import java.util.Scanner;

public class CustomerController {


    public void run() {

        boolean isRunning = true;

        Scanner scanner = new Scanner(System.in);


        while (isRunning) {

            Display.clearScreen();
            Display.showMenu("customerMenu");


            switch (scanner.nextInt()) {

                case 1: {

                    Display.clearScreen();
                    System.out.println("CUSTOMER");
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }

                case 2: {

                    Display.clearScreen();
                    try {
                        System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }

                case 0: {
                    isRunning = false;
                    break;
                }
                default: {
//                    Display.clearScreen();
                }

            }
        }
    }

}
