package controler;


import dao.sql.OrdersDAO;
import dao.sql.OrdersItemsDAO;
import dao.sql.ProductDAO;
import model.shop.Basket;
import model.shop.Customer;
import model.shop.Order;
import model.shop.User;
import model.shop.lists.OrderItemsList;
import model.shop.lists.OrdersList;
import model.shop.lists.ProductList;
import view.Display;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

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


            switch (scanner.nextInt()) {

                case 1: {

                    Display.clearScreen();
//                    System.out.println(this.productList);
                    Display.printProductTable(productList);
                    Display.prompt();
                    break;
                }

                case 2: {
//                    basket.addProduct(1,this.productList.getProducts());
                    Display.prompt();
                    break;
                }
                case 3: {
                    Display.printBasket(basket);
                    Display.prompt();
                    break;
                }
                case 6: {
                    makeNewOrder();
                    break;
                }
                case 8: {
                    basket.setBasketFromDB();
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

    public void makeNewOrder (){
        new OrdersDAO().create(user);
        ordersList.setOrders(new OrdersDAO().readOrders(user));
        Order newOrder = new OrdersDAO().readOrders(user).get(ordersList.getOrders().size()-1);
        new OrdersItemsDAO().create(basket,newOrder);
        basket.getProducts().clear();
        //TODO CLEAR BASKET DATABASE BY BASKET_DAO
        //TODO Change general amount of products after making an order
    }

}
