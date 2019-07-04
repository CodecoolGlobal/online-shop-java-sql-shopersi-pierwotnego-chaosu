package controller;


import dao.sql.BasketsDAO;
import dao.sql.OrdersDAO;
import dao.sql.OrdersItemsDAO;
import dao.sql.ProductDAO;
import model.shop.*;

import model.shop.abc.ProductList;
import model.shop.lists.CategoryList;
import view.Display;
import model.shop.User;
import model.shop.lists.OrdersList;
//import model.shop.lists.ProductList;

import java.util.ArrayList;

import java.util.Scanner;
import java.util.Set;

public class CustomerController {

    private User user;
    private Basket basket;
    private ProductList productList;
    private OrdersList ordersList;

    public CustomerController(User user) {
        this.user = user;
        this.basket = new Basket(user.getId());
        this.productList = new ProductList(new ProductDAO().read());
        this.ordersList = new OrdersList(new OrdersDAO().readOrders(user));
    }


    public void run() {

        boolean isRunning = true;

        Scanner scanner = new Scanner(System.in);


        while (isRunning) {


            Display.clearScreen();
            Display.showMenu("customerMenu");

            int option = Display.askForInt("Choose an option");

            switch (option) {

                case 1: {

                    Display.clearScreen();
                    Display.printProductTable(productList, user);
                    Display.prompt();
                    break;
                }
                case 2: {
                    showProductsFromSpecificCategory();
                    break;
                }
                case 3: {
                    Display.clearScreen();
                    Display.printProductTable(productList, user);
                    addProdToB();
                    break;
                }
                case 4: {
                    Display.printBasket(basket);
                    Display.prompt();
                    break;
                }
                case 5: {
                    Display.printBasket(basket);
                    editBasket();
                    Display.prompt();
                    break;
                }

                case 7: {
                    makeNewOrder();
                    break;
                }
                case 8: {
                    Display.clearScreen();
                    Display.printOrdersTable(ordersList, productList, user);
                    Display.prompt();
                    break;
                }

                case 9: {
                    this.basket.setBasketFromDB();
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


    private void editBasket() {
        boolean isAsking = true;
        while (isAsking) {

            int prodId = Display.askForInt("Select productID");
            final boolean isValid = productList.isIdValid(prodId); //&& productList.getProductById(prodId).isAvailable();

            if (isValid) {
                int quantity = Display.askForInt("Set new amount of items / 0 to delete.");


                if (quantity == 0) {

                    Set<Product> productSet = this.basket.getProducts().keySet();


                    for (Product key : productSet) {

                        if (key.getId() == prodId) {
                            this.basket.getProducts().remove(key);
                            isAsking = false;
                            break;
                        }

                    }


                } else if (quantity > 0 && quantity <= productList.getProductById(prodId).getAmount()) {

                    this.basket.getProducts().forEach((key, value) -> {
                        if (key.getId() == prodId) {
                            basket.getProducts().replace(key, quantity);
                        }

                    });
                    isAsking = false;

                } else System.out.println("Incorrect Amount");

            } else System.out.println("Incorrect Id");

        }
    }

    private void addProdToB() {
        boolean isAsking = true;
        while (isAsking) {

            int prodId = Display.askForInt("Select productID");
            boolean isValid = productList.isIdValid(prodId) && productList.getProductById(prodId).isOnStock();

            if (isValid) {
                int quantity = Display.askForInt("How many Items?");
                if (quantity==0)isAsking=false;
                if (quantity > 0 && quantity <= productList.getProductById(prodId).getAmount()
                        && productList.getProductById(prodId).isAvailable()) {

                    basket.addProductToBasket(prodId, this.productList.getProducts(), quantity);
                    this.productList.removeAmountById(prodId, quantity);
                    isAsking = false;
                } else System.out.println("Incorrect Amount or product is no longer available");

            } else System.out.println("Incorrect Id");

        }
    }

    public void makeNewOrder() {
        if (!basket.getProducts().isEmpty()) {

            new OrdersDAO().create(user);
            ordersList.setOrders(new OrdersDAO().readOrders(user));
            Order newOrder = new OrdersDAO().readOrders(user).get(ordersList.getOrders().size() - 1);
            new OrdersItemsDAO().create(basket, newOrder);
            this.productList.updateAllProductsInDataBase();


//            for (Product productFromBasket : basket.getProducts().keySet()) {
//                for (Product productUpdated : productList.getProducts()) {
//                    if (productFromBasket.getId() == productUpdated.getId()) {
//                        int amount = productUpdated.getAmount() - basket.getProducts().get(productUpdated);
//                        productUpdated.setAmount(amount);
//                        new ProductDAO().update(productUpdated);
//                    }
//                }
//            }
            basket.getProducts().clear();
            new BasketsDAO().delete(user);
            this.productList = new ProductList(new ProductDAO().read());
            Display.printMessage("You have placed your order succesfully.");

        } else {
            Display.printMessage("Your basket is empty. Add items to basket.");
        }

    }

    public void showProductsFromSpecificCategory(){
        ArrayList<Product> productsFromCategory = new ArrayList<Product>();
        CategoryList categoryList = new CategoryList();
        Display.printMessage(categoryList.toString());
        Display.printMessage("Choose Id of category which you want to see products from.");
        Scanner scanner = new Scanner(System.in);

        String out = scanner.next();
        while (!out.matches("[0-"+String.valueOf(categoryList.getCategoryList().size())+"]+")) {
            Display.printMessage("There is no such category. Choose correct Category ID or 0 to exit:");
            out = scanner.next();
            if (out.equals("0")) break;
        }
        if (!out.equals("0")) {
            Integer chooseCategory = Integer.valueOf(out);
            for (Product product : productList.getProducts()) {
                if (product.getCategory() == chooseCategory) {
                    productsFromCategory.add(product);
                }
            }
            ProductList productsListFromCategory = new ProductList(productsFromCategory);
            Display.printProductTable(productsListFromCategory, user);
        }
    }


}
