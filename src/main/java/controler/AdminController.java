package controler;

import model.shop.Product;
import model.shop.User;
import view.Display;

import java.io.IOException;
import java.util.Scanner;

public class AdminController {
    User user;
    public AdminController(User user){
        this.user = user;
    }

    public void run() {

        boolean isRunning = true;

        Scanner scanner = new Scanner(System.in);


        while (isRunning) {

            Display.clearScreen();
            Display.showMenu("adminMenu");


            switch (scanner.nextInt()) {

                case 1: {

                    Product product = new Product("kkkkkaaa", 12, 12, true,1);
                    product.create();

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
