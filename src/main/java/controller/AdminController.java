package controller;

import dao.sql.ProductDAO;
import dao.sql.UsersDAO;
import model.shop.Category;
import model.shop.Product;
import model.shop.User;
import model.shop.abc.ProductList;
import model.shop.lists.CategoryList;
//import model.shop.abc.ProductList;
import model.shop.lists.UserList;
import view.Display;

import java.util.Scanner;

class AdminController {
    private final User user;
    private ProductList productList;
    private final UserList userList;
//    private OrdersList ordersList;

    AdminController(User user) {
        this.user = user;
        this.productList = new ProductList(new ProductDAO().read());
        this.userList = new UserList(new UsersDAO().read());

    }

    void run() {
        boolean isRunning = true;
        //Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            Display.clearScreen();
            Display.showMenu("adminMenu");
            this.productList = new ProductList(new ProductDAO().read());

            int option = Display.askForInt("Choose an option");

            switch (option) {

                case 1: {

                    Display.clearScreen();
                    Display.printProductTable(productList, user);
                    Display.pressForNexxt();
                    break;
                }

                case 2: {
                    addNewProduct();
                    break;
                }

                case 3: {
                    Display.clearScreen();
                    Display.printProductTable(productList, user);
                    editProduct();
                    break;
                }

                case 4: {
                    Display.clearScreen();
                    Display.printProductTable(productList, user);
                    deactivateAProduct();
                    break;
                }

                case 5: {

                    Display.clearScreen();
                    showCategories();
                    Display.pressForNexxt();

                    break;
                }

                case 6: {
                    Display.clearScreen();
                    showCategories();
                    addNewCategory();
                    break;
                }

                case 7: {
                    Display.clearScreen();
                    editCategory();
                    break;
                }

                case 9: {
                    Display.clearScreen();
                    Display.printOngoingOrders(userList, productList);
                    Display.pressForNexxt();

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


    private void addNewProduct() {
        String name = Display.askForString("Product name: ");
        double price = Display.askForDouble("Product price: ");
        int amount = Display.askForInt("Amount: ");
        CategoryList categoryList = new CategoryList();
        Display.printCategories(categoryList);
        String idOfCategory = Display.askForString("Category id: ");
        while (!idOfCategory.matches("[0-" + categoryList.getCategoryList().size() + "]+")) {
            Display.printMessage("There is no such category. Choose correct Category ID or 0 to exit:");
            idOfCategory = Display.askForString("Category id: ");
            if (idOfCategory.equals("0")) break;
        }
        if (!idOfCategory.equals("0")) {
            int categoryId = Integer.valueOf(idOfCategory);
            boolean onStock = amount>0;      //Display.askForString("Is on Stock y/n").equals("y");
            Product product = new Product(name, price, amount, amount > 0, categoryId, onStock);
            productList.add(product);
            product.create();
        }
    }

    private void editProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Product id: ");
        int id = scanner.nextInt();
        if (productList.isIdValid(id)) {

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

            System.out.println("\nNew category id: ");

            CategoryList categoryList = new CategoryList();
            Display.printCategories(categoryList);
            String idOfCategory = sc2.nextLine();
            while (!idOfCategory.matches("[0-" + categoryList.getCategoryList().size() + "]+")) {
                if (idOfCategory.equals("") || idOfCategory.equals("0")) break;
                Display.printMessage("There is no such category. Choose correct Category ID or 0 to stop editing product:");
                idOfCategory = Display.askForString("Category id: ");
            }
            if (!idOfCategory.equals("0")) {
                if (!idOfCategory.equals("")) product.setCategory(Integer.valueOf(idOfCategory));
                product.update();
            }
        }
    }

    private void deactivateAProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Product id: ");
        int id = sc.nextInt();
        if (productList.isIdValid(id)) {
            Product product = productList.getProductById(id);
            product.setAvailable(!product.isAvailable());
            product.update();
        }
    }


    private void showCategories(){
        CategoryList categoryList = new CategoryList();
        Display.printCategories(categoryList);
    }

    private void addNewCategory() {

        String name = Display.askForString("Category name: ");
        Category category = new Category(name);
        category.createInDB();
    }

    private void editCategory() {
        showCategories();
        CategoryList list = new CategoryList();
        int id = Display.askForInt("Category id: ");
        Category category = list.getCategoryById(id);
        String name = Display.askForString("Change " + category.getName() + " into: ");
        category.setName(name);
        category.updateInDB();
    }

}
