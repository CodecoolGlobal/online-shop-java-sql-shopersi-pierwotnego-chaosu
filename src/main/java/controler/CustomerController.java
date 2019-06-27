package controler;


import model.shop.Basket;
import model.shop.Customer;
import model.shop.User;
import view.Display;

import java.io.IOException;
import java.util.Scanner;

public class CustomerController {
    private User user;

    public CustomerController(User user) {
        this.user = user;
    }


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
                    Display.prompt();
                    break;
                }

                case 2: {

                    Display.clearScreen();
                    Display.prompt();
                    break;
                }
                case 3: {
                    Basket basket = new Basket(user.getId());
                    basket.setBasketFromDB();
                    System.out.println(basket);
                    Display.printBasket(basket);
                    Display.prompt();
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
