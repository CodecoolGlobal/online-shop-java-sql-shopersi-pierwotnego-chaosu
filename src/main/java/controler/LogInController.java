package controler;

import view.Display;

import java.util.Scanner;

public class LogInController {


    public void run() {

        boolean isRunning = true;

        Scanner scanner = new Scanner(System.in);


        while (isRunning) {

            Display.showMenu("logMenu");
            Display.clearScreen();


            switch (scanner.nextInt()) {

                case 1: {

                    Display.clearScreen();
                    System.out.println("TROLLOO");
                    break;
                }

                case 2: {

                    Display.clearScreen();
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
