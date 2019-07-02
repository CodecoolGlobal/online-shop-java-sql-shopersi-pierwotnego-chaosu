package controler;

import dao.sql.ProductDAO;
import model.shop.Category;
import model.shop.Product;
import model.shop.User;
import model.shop.abc.ProductList;
import model.shop.lists.CategoryList;
//import model.shop.abc.ProductList;
import view.Display;

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
                    Display.printProductTable(productList);
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
        String name = Display.askForString("Product name: ");
        double price = Display.askForDouble("Product price: ");
        int amount = Display.askForInt("Amount: ");
        int categoryId = Display.askForInt("Category id: ");
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

    private void showCategories(){
        System.out.println(new CategoryList());
    }

    private void addNewCategory(){

        String name = Display.askForString("Category name: ");
        Category category = new Category(name);
        category.createInDB();
    }

    private void editCategory(){
        showCategories();
        CategoryList list = new CategoryList();
        int id = Display.askForInt("Category id: ");
        Category category = list.getCategoryById(id);
        String name = Display.askForString("Change " + category.getName()  +" into: ");
        category.setName(name);
        category.updateInDB();
    }

}
