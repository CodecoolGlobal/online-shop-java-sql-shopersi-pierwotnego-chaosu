package controler;

import dao.sql.ProductDAO;
import model.shop.Product;
import model.shop.User;
import model.shop.lists.ProductList;
import view.Display;

import java.io.IOException;
import java.util.Scanner;

public class AdminController {
    private User user;
    private ProductList productList;

    public AdminController(User user){
        this.user = user;
        this.productList = new ProductList(new ProductDAO().read());
    }

    public void run() {

        boolean isRunning = true;

        Scanner scanner = new Scanner(System.in);


        while (isRunning) {

            Display.clearScreen();
            Display.showMenu("adminMenu");

            this.productList = new ProductList(new ProductDAO().read());
            switch (scanner.nextInt()) {

                case 1: {
                    addNewProduct();
                }

                case 2: {

                    break;
                }

                case 4: {

                    Display.clearScreen();
                    Display.printProductTable(productList);
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


    public void addNewProduct(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Product name: ");
        String name = scanner.nextLine();
        System.out.print("\nProduct price: ");
        System.out.print("\nProduct price: ");
        double price = scanner.nextDouble();
        System.out.print("\nAmount: ");
        int amount = scanner.nextInt();
        System.out.print("\nCategory id: ");
        int categoryId = scanner.nextInt();
        Product product = new Product(name,price,amount,amount > 0,categoryId);
        productList.add(product);
        product.create();
        }


}
