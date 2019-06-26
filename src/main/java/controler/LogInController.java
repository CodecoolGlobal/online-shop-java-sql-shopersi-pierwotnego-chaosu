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
                    break;
                }

                case 2: {
                    createNewAccount();
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
        if (new UsersDAO().readUser(name).getName()==null){
            System.out.println();
            System.out.println("There is no such user in the database.");
            System.out.println();
        } else {
            System.out.print("User password: ");
            String password = scanner.next();
            User user = new UsersDAO().readUser(name);
            if (user.getName() != null && !user.getPassowrd().equals(password)) {
                System.out.println();
                System.out.println("Wrong password... ");
                System.out.println();

            }
            if (user.getName() != null && user.getPassowrd().equals(password)) {
                if (user.getAdmin() == 1) {
                    AdminController adminController = new AdminController(user);
                    adminController.run();
                } else {
                    CustomerController customerController = new CustomerController(user);
                    customerController.run();
                }
            }
        }
    }

    public void createNewAccount(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Choose your username: ");
        String name = scanner.next();
        User newUser = new UsersDAO().readUser(name);
        while (newUser.getName()!=null) {
            System.out.println();
            System.out.println("These username already exist. Choose another one:");
            System.out.println();
            name = scanner.next();
            newUser = new UsersDAO().readUser(name);

        }
        newUser.setAdmin(0);
        newUser.setName(name);
        System.out.print("Choose your password: ");
        String password = scanner.next();
        newUser.setPassowrd(password);
        new UsersDAO().create(newUser);
        System.out.println();
        System.out.println("You have created new account correctly. You can log in now.");
        System.out.println();
    }


    // User newUser = new UsersDAO().readUser("Dejw","dejw");
    //           System.out.println(newUser.getName());
    // System.out.println(newUser.getPassowrd());
    // System.out.println(newUser.getId());
    // System.out.println(newUser.getAdmin());

}
