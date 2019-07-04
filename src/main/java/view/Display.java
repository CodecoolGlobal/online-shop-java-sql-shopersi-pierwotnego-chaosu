package view;

import dao.sql.OrdersDAO;
import dao.sql.OrdersItemsDAO;
import model.controller.FileReaderForDispaly;
import model.shop.Basket;
import model.shop.Order;
import model.shop.OrderItem;
import model.shop.Product;
import model.shop.User;
import model.shop.abc.ProductList;
import model.shop.lists.OrderItemsList;
import model.shop.lists.OrdersList;
import model.shop.lists.UserList;

import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.*;

public class Display {


    public static void showMenu(String menuName) {

        System.out.println("Choose an option: ");
        FileReaderForDispaly.showMenu(menuName).forEach(System.out::println);
    }

    public static void printBasket(Basket basket) {

        HashMap<Product, Integer> basketM = basket.getProducts();

        Set<Product> keyS = basketM.keySet();

        int form1 = "Product ID".length();
        int form2 = "Name".length();
        int form3 = "Quantity".length();
        int form4 = "Price".length();
        int form5 = "Value".length();


        for (Product prod : keyS) {

            int form1a = String.valueOf(prod.getId()).length();
            int form2a = prod.getName().length();
            int form3a = String.valueOf(basketM.get(prod)).length();
            int form4a = Double.toString(prod.getPrice()).length();
            int form5a = Double.toString(prod.getPrice() * basketM.get(prod)).length();


            if (form1 <= form1a) form1 = form1a;
            if (form2 <= form2a) form2 = form2a;
            if (form3 <= form3a) form3 = form3a;
            if (form4 <= form4a) form4 = form4a;
            if (form5 <= form5a) form5 = form5a;

        }


        String formater1 = "%" + form1 + "s";
        String formater2 = "%" + form2 + "s";
        String formater3 = "%" + form3 + "s";
        String formater4 = "%" + form4 + "s";
        String formater5 = "%" + form5 + "s";


        String output = "";


        String l1 = multi(form1, "─");
        String l2 = multi(form2, "─");
        String l3 = multi(form3, "─");
        String l4 = multi(form4, "─");
        String l5 = multi(form5, "─");


        output += "┌" + l1 + "┬" + l2 + "┬" + l3 + "┬" + l4 + "┬" + l5 + "┐\n";


        String prodIdH = String.format(formater1, "Product ID");
        String prodNameH = String.format(formater2, "Name");
        String prodQuantityH = String.format(formater3, "Quantity");
        String prodPriceH = String.format(formater4, "Price");
        String prodValueH = String.format(formater5, "Value");

        output += "│" + prodIdH + "│" + prodNameH + "│" + prodQuantityH + "│" + prodPriceH + "│" + prodValueH + "│\n";

        output += "├" + l1 + "┼" + l2 + "┼" + l3 + "┼" + l4 + "┼" + l5 + "┤\n";


        for (Product product : keyS) {

            String prodId = String.format(formater1, product.getId());
            String prodName = String.format(formater2, product.getName());
            String prodQuantity = String.format(formater3, String.valueOf(basketM.get(product)));
            String prodPrice = String.format(formater4, Double.toString(product.getPrice()));
            String prodValue = String.format(formater5, Double.toString(product.getPrice() * basketM.get(product))
            );


            output += "│" + prodId + "│" + prodName + "│" + prodQuantity + "│" + prodPrice + "│" + prodValue + "│\n";

        }
        output += "└" + l1 + "┴" + l2 + "┴" + l3 + "┴" + l4 + "┴" + l5 + "┘\n";

        output += "\n" + "Total price for this order: " + basket.countTotalValue() + "$.\n";
        System.out.print(output);


    }

    public static void printProductTable(ProductList products, User user) {

        List<Product> productList = products.getProducts();

        int form1 = "Product ID".length();
        int form2 = "Name".length();
        int form3 = "Price".length();
        int form4 = "Amount".length();
        int form5 = "Category".length();


        for (Product prod : productList) {

            int form1a = String.valueOf(prod.getId()).length();
            int form2a = prod.getName().length();
            int form3a = String.valueOf(prod.getPrice()).length();
            int form4a = String.valueOf(prod.getAmount()).length();
            int form5a = String.valueOf(prod.getCategoryName()).length();

            if (form1 <= form1a) form1 = form1a;
            if (form2 <= form2a) form2 = form2a;
            if (form3 <= form3a) form3 = form3a;
            if (form4 <= form4a) form4 = form4a;
            if (form5 <= form5a) form5 = form5a;

        }
        String formater1 = "%" + form1 + "s";
        String formater2 = "%" + form2 + "s";
        String formater3 = "%" + form3 + "s";
        String formater4 = "%" + form4 + "s";
        String formater5 = "%" + form5 + "s";


        String output = "";


        String l1 = multi(form1, "─");
        String l2 = multi(form2, "─");
        String l3 = multi(form3, "─");
        String l4 = multi(form4, "─");
        String l5 = multi(form5, "─");


        output += "┌" + l1 + "┬" + l2 + "┬" + l3 + "┬" + l4 + "┬" + l5 + "┐\n";


        String prodIdH = String.format(formater1, "Product ID");
        String prodNameH = String.format(formater2, "Name");
        String prodPriceH = String.format(formater3, "Price");
        String prodAmountH = String.format(formater4, "Amount");
        String prodCategoryH = String.format(formater5, "Category");

        output += "│" + prodIdH + "│" + prodNameH + "│" + prodPriceH + "│" + prodAmountH + "│" + prodCategoryH + "│\n";

        output += "├" + l1 + "┼" + l2 + "┼" + l3 + "┼" + l4 + "┼" + l5 + "┤\n";

        for (Product product : productList) {


            if (product.isAvailable() && !user.isAdmin()) {

                String prodId = String.format(formater1, product.getId());
                String prodName = String.format(formater2, product.getName());
                String prodPrice = String.format(formater3, product.getPrice());
                String prodAmount = String.format(formater4, product.getAmount());
                String prodCategory = String.format(formater5, product.getCategoryName());

                output += "│" + prodId + "│" + prodName + "│" + prodPrice + "│" + prodAmount + "│" + prodCategory + "│\n";
            } else if (user.isAdmin()) {

                String prodId = String.format(formater1, product.getId());
                String prodName = String.format(formater2, product.getName());
                String prodPrice = String.format(formater3, product.getPrice());
                String prodAmount = String.format(formater4, product.getAmount());
                String prodCategory = String.format(formater5, product.getCategoryName());
                String prodIsAvailable = String.valueOf(product.isAvailable());

                output += "│" + prodId + "│" + prodName + "│" + prodPrice + "│" + prodAmount + "│" + prodCategory + "│" + prodIsAvailable + "\n";

            }
        }

        output += "└" + l1 + "┴" + l2 + "┴" + l3 + "┴" + l4 + "┴" + l5 + "┘\n";

        System.out.println(output);

    }

    public static int askForInt(String question) {
        System.out.println(question);
        Scanner scanner = new Scanner(System.in);
        String out = scanner.next();
        while (!out.matches("[0-9]+")) {
            Display.printMessage("Use only numbers");
            out = scanner.next();
        }
        return Integer.valueOf(out);

    }

    public static Double askForDouble(String question) {
        System.out.println(question);
        Scanner scanner = new Scanner(System.in);
        String out = scanner.next();
        while (!out.matches("([0-9]*)\\.([0-9]*)")) {
            Display.printMessage("Use only doubles with . as separator ");
            out = scanner.next();
        }
        return Double.valueOf(out);
    }


    public static void printMessage(String message) {
        System.out.println(message);

    }


    public static String askForString(String question) {
        System.out.println(question);
        Scanner scanner = new Scanner(System.in);
        String out = scanner.nextLine();
        return out;
    }


    public static void prompt() {
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static String multi(int occ, String sign) {
        String out = "";

        for (int i = 0; i < occ; i++) {
            out += sign;
        }
        return out;
    }


    public static void printOrdersTable(OrdersList orders, ProductList products, User user) {

        for (Order order : orders.getOrders()) {
            printMessage("OrderId: " + order.getId() + ", Status: " + order.getStatus() + ", Date: " + order.getDate());
            ArrayList<Product> emptyList = new ArrayList<>();
            ProductList productsInOrder = new ProductList(emptyList);
            OrderItemsList orderedItemsList = new OrderItemsList(new OrdersItemsDAO().read(order));

            for (OrderItem orderItem : orderedItemsList.getItems()) {
                Product product = products.getProductById(orderItem.getItemId());
                product.setAmount(orderItem.getAmount());
                productsInOrder.add(product);
            }
            printProductTable(productsInOrder, user);
            printMessage("Total price for this order: " + orderedItemsList.totalPrice(productsInOrder) + "$.\n");



        }
    }

    public static void printOngoingOrders(UserList userList, ProductList products) {
        OrdersList allOrders = new OrdersList(new OrdersDAO().readAllOrders());
        Set<Integer> usersIdList = new HashSet<Integer>();
        for (Order order: allOrders.getOrders()) {
            usersIdList.add(order.getUserId());
        }
        for (User user : userList.getUsers()) {
                if (usersIdList.contains(user.getId())) {
                    printMessage("Orders of user :" + user.getName());
                    OrdersList orders = new OrdersList(new OrdersDAO().readOrders(user));
                    printOrdersTable(orders, products, user);
                }
            }
        }

}
