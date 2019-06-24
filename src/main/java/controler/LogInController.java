package controler;

import dao.sql.UsersDAO;
import model.shop.Admin;
import model.shop.User;
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
                    logInUser();
                }

                case 2: {
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

    public void logInUser(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("User name: ");
        String name = scanner.next();
        System.out.print("User password: ");
        String password = scanner.next();
        User user = new UsersDAO().readUser(name,password);
        if (user.getName()!=null){
            if (user.getAdmin() == 1){
                AdminController adminController = new AdminController(user);
                adminController.run();
            } else {
                CustomerController customerController = new CustomerController(user);
                customerController.run();
            }
        }



    }

    // User newUser = new UsersDAO().readUser("Dejw","dejw");
    //           System.out.println(newUser.getName());
    // System.out.println(newUser.getPassowrd());
    // System.out.println(newUser.getId());
    // System.out.println(newUser.getAdmin());

}
