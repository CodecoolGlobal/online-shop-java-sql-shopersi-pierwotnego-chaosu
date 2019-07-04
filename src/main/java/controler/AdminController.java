package controler;

import dao.sql.OrdersDAO;
import dao.sql.ProductDAO;
import dao.sql.UsersDAO;
import model.shop.Category;
import model.shop.Product;
import model.shop.User;
import model.shop.abc.ProductList;
import model.shop.lists.CategoryList;
//import model.shop.abc.ProductList;
import model.shop.lists.OrdersList;
import model.shop.lists.UserList;
import view.Display;

import java.util.Scanner;

public class AdminController {
    private User user;
    private ProductList productList;
    private UserList userList;
//    private OrdersList ordersList;

    public AdminController(User user) {
        this.user = user;
        this.productList = new ProductList(new ProductDAO().read());
        this.userList = new UserList(new UsersDAO().read());

    }

    public void run() {
        boolean isRunning = true;
        Scanner scanner = new Scanner(System.in);
        while (isRunning) {
            Display.clearScreen();
            Display.showMenu("adminMenu");
            this.productList = new ProductList(new ProductDAO().read());

            int option = Display.askForInt("Choose an option");

            switch (option) {

                case 2: {
                    addNewProduct();
                    break;
                }

                case 3: {
                    editProduct();
                    break;
                }

                case 4: {
                    deactivateAProduct();
                    break;
                }

                case 1: {

                    Display.clearScreen();
                    Display.printProductTable(productList, user);
                    break;
                }

                case 5: {

                    Display.clearScreen();
                    showCategories();
                    break;
                }

                case 6: {
                    addNewCategory();
                    break;
                }

                case 7: {
                    editCategory();
                    break;
                }

                case 9: {
                    Display.printOngoingOrders(userList, productList);
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
        Display.printMessage(categoryList.toString());
        String idOfCategory = Display.askForString("Category id: ");
        while (!idOfCategory.matches("[0-" + String.valueOf(categoryList.getCategoryList().size()) + "]+")) {
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

            System.out.print("\nNew category id: ");
            String categoryId = sc2.nextLine();

            if (!categoryId.equals("")) product.setCategory(Integer.valueOf(categoryId));

            product.update();
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

    private void showCategories() {
        System.out.println(new CategoryList().toString());
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
