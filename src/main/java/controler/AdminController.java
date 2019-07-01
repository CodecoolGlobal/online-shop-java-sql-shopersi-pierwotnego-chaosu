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
                    break;
                }

                case 2: {
                    editProduct();
                    break;
                }

                case 3: {
                    deactivateAProduct();
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


    private void addNewProduct(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Product name: ");
        String name = scanner.nextLine();
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

    private void editProduct(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Product id: ");
        int id = scanner.nextInt();
        if (productList.isIdValid(id)){

            Scanner sc2 = new Scanner(System.in);
            Product product = productList.getProductById(id);

            System.out.print("New product name: ");
            String name = sc2.nextLine();

            if (!name.equals("")) product.setName(name);

            System.out.print("\nNew product price: ");
            String price = sc2.nextLine();

            if (!price.equals("")) product.setPrice(Double.valueOf(price));

            System.out.print("\nNew amount: ");
            String amount = sc2.nextLine();

            if (!amount.equals("")) product.setAmount(Integer.valueOf(amount));

            System.out.print("\nNew category id: ");
            String categoryId = sc2.nextLine();

            if (!categoryId.equals("")) product.setCategory(Integer.valueOf(categoryId));

            product.update();
        }
    }

    private void deactivateAProduct(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Product id: ");
        int id = sc.nextInt();
        if (productList.isIdValid(id)){
            Product product = productList.getProductById(id);
            product.setAvailable(!product.isAvailable());
            product.update();
        }
    }

}
