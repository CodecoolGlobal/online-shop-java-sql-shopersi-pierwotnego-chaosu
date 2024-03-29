package view;

import dao.sql.OrdersDAO;
import dao.sql.OrdersItemsDAO;
import model.display.FileReaderForDispaly;
import model.shop.*;
import model.shop.abc.ProductList;
import model.shop.lists.CategoryList;
import model.shop.lists.OrderItemsList;
import model.shop.lists.OrdersList;
import model.shop.lists.UserList;

import java.io.IOException;
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


        String l1 = multiSign(form1, "─");
        String l2 = multiSign(form2, "─");
        String l3 = multiSign(form3, "─");
        String l4 = multiSign(form4, "─");
        String l5 = multiSign(form5, "─");


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
        int form6 = "Availability".length();


        for (Product prod : productList) {

            int form1a = String.valueOf(prod.getId()).length();
            int form2a = prod.getName().length();
            int form3a = String.valueOf(prod.getPrice()).length();
            int form4a = String.valueOf(prod.getAmount()).length();
            int form5a = String.valueOf(prod.getCategoryName()).length();
            int form6a = (prod.isAvailable() ? "available":"Not Available").length();

            if (form1 <= form1a) form1 = form1a;
            if (form2 <= form2a) form2 = form2a;
            if (form3 <= form3a) form3 = form3a;
            if (form4 <= form4a) form4 = form4a;
            if (form5 <= form5a) form5 = form5a;
            if (form6 <= form6a) form6 = form6a;

        }
        String formater1 = "%" + form1 + "s";
        String formater2 = "%" + form2 + "s";
        String formater3 = "%" + form3 + "s";
        String formater4 = "%" + form4 + "s";
        String formater5 = "%" + form5 + "s";
        String formater6 = "%" + form6 + "s";


        String output = "";


        String l1 = multiSign(form1, "─");
        String l2 = multiSign(form2, "─");
        String l3 = multiSign(form3, "─");
        String l4 = multiSign(form4, "─");
        String l5 = multiSign(form5, "─");
        String l6 = multiSign(form6, "─");

        output += "┌" + l1 + "┬" + l2 + "┬" + l3 + "┬" + l4 + "┬" + l5 +"┬" + l6 + "┐\n";


        String prodIdH = String.format(formater1, "Product ID");
        String prodNameH = String.format(formater2, "Name");
        String prodPriceH = String.format(formater3, "Price");
        String prodAmountH = String.format(formater4, "Amount");
        String prodCategoryH = String.format(formater5, "Category");
        String prodAvailabilityH = String.format(formater6, "Availability");

        output += "│" + prodIdH + "│" + prodNameH + "│" + prodPriceH + "│" + prodAmountH + "│" +
                    prodCategoryH +  "│" + prodAvailabilityH +  "│\n";

        output += "├" + l1 + "┼" + l2 + "┼" + l3 + "┼" + l4 + "┼" + l5 +"┼" + l6 + "┤\n";

        for (Product product : productList) {


            if (product.isOnStock() && !user.isAdmin()) {

                String prodId = String.format(formater1, product.getId());
                String prodName = String.format(formater2, product.getName());
                String prodPrice = String.format(formater3, product.getPrice());
                String prodAmount = String.format(formater4, product.getAmount());
                String prodCategory = String.format(formater5, product.getCategoryName());
                String prodIsAvailable = String.format(formater6,product.isAvailable() ? "available":"Not Available");


                output += "│" + prodId + "│" + prodName + "│" + prodPrice + "│" +
                        prodAmount + "│" + prodCategory + "│" + prodIsAvailable +"│" + "\n";
            } else if (user.isAdmin()) {

                String prodId = String.format(formater1, product.getId());
                String prodName = String.format(formater2, product.getName());
                String prodPrice = String.format(formater3, product.getPrice());
                String prodAmount = String.format(formater4, product.getAmount());
                String prodCategory = String.format(formater5, product.getCategoryName());
                String prodIsAvailable = String.format(formater6,product.isAvailable() ? "available":"Not Available");
                String prodIsOnStock = product.isOnStock() ? "onStock":"EMPTY";

                output += "│" + prodId + "│" + prodName + "│" + prodPrice + "│" +
                        prodAmount + "│" + prodCategory + "│" + prodIsAvailable +"│" + prodIsOnStock +"\n";

            }
        }

        output += "└" + l1 + "┴" + l2 + "┴" + l3 + "┴" + l4 + "┴" + l5 +"┴" + l6 + "┘\n";

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


    public static void pressForNexxt() {
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

    private static String multiSign(int multiplication, String sign) {
        String out = "";

        for (int i = 0; i < multiplication; i++) {
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
                product.setOnStock(true);
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

    public static void printCategories(CategoryList list) {

        List<Category> categoryList = list.getCategoryList();

        int form1 = "Category ID".length();
        int form2 = "Name".length();

        for (Category category : categoryList) {

            int form1a = String.valueOf(category.getId()).length();
            int form2a = category.getName().length();

            if (form1 <= form1a) form1 = form1a;
            if (form2 <= form2a) form2 = form2a;
        }


        String formater1 = "%" + form1 + "s";
        String formater2 = "%" + form2 + "s";

        String output = "";

        String l1 = multiSign(form1, "─");
        String l2 = multiSign(form2, "─");

        output += "┌" + l1 + "┬" + l2 + "┐\n";


        String categoryIdH = String.format(formater1, "Category ID");
        String categoryNameH = String.format(formater2, "Name");

        output += "│" + categoryIdH + "│" + categoryNameH + "│\n";

        output += "├" + l1 + "┼" + l2 +"┤\n";


        for (Category category : categoryList) {

            String prodId = String.format(formater1, category.getId());
            String prodName = String.format(formater2, category.getName());

            output += "│" + prodId + "│" + prodName + "│\n";

        }
        output += "└" + l1 + "┴" + l2 + "┘\n";

        System.out.print(output);
    }
}
