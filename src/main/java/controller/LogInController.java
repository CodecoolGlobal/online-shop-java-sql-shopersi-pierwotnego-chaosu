package controller;

import dao.sql.UsersDAO;
import model.shop.User;
import view.Display;

import java.util.Scanner;

public class LogInController {


    public void run() {
        boolean isRunning = true;
        while (isRunning) {
            Display.clearScreen();
            Display.showMenu("logMenu");

            int option = Display.askForInt("Choose an option");

            switch (option) {
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
                }

            }
        }
    }

    public void logInUser(){
        Scanner scanner = new Scanner(System.in);
        Display.printMessage("User name: ");
        String name = scanner.nextLine();
        Display.printMessage("User password: ");
        String password = scanner.nextLine();
        if (new UsersDAO().readUser(name,password).getName()==null){
            Display.printMessage("Wrong username or password.");
        } else {
            User user = new UsersDAO().readUser(name,password);
                if (user.getAdmin() == 1) {
                    AdminController adminController = new AdminController(user);
                    adminController.run();
                } else {
                    CustomerController customerController = new CustomerController(user);
                    customerController.run();
                }
//            }
        }
    }

    public void createNewAccount(){
        Scanner scanner = new Scanner(System.in);
        Display.printMessage("Choose your username: ");
        String name = scanner.next();
        User newUser = new UsersDAO().readUser(name);
        while (newUser.getName()!=null) {
            Display.printMessage("These username already exist. Choose another one:");
            name = scanner.next();
            newUser = new UsersDAO().readUser(name);

        }
        newUser.setAdmin(0);
        newUser.setName(name);
        Display.printMessage("Choose your password: ");
        String password = scanner.next();
        newUser.setPassowrd(password);
        new UsersDAO().create(newUser);
        Display.printMessage("You have created new account correctly. You can log in now.");
    }
}
