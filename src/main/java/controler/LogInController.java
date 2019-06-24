package controler;

import view.Display;

import java.io.IOException;
import java.util.Scanner;

public class LogInController {


    public void run() {

        boolean isRunning = true;

        Scanner scanner = new Scanner(System.in);


        while (isRunning) {

            Display.clearScreen();
            Display.showMenu("logMenu");


            switch (scanner.nextInt()) {

                case 1: {

                    Display.clearScreen();
//                    System.out.println("TROLLOO");
                    CustomerController customerControler = new CustomerController();
                    customerControler.run();


                    try {
                        System.in.read();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }

                case 2: {

                    Display.clearScreen();
                    AdminController adminController = new AdminController();
                    adminController.run();
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
