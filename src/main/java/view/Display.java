package view;

import dao.sql.OrdersItemsDAO;
import model.controller.FileReaderForDispaly;
import model.shop.Basket;
import model.shop.Order;
import model.shop.OrderItem;
import model.shop.Product;
import model.shop.abc.ProductList;
import model.shop.lists.OrderItemsList;
import model.shop.lists.OrdersList;

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


        for (Product prod : keyS) {

            int form1a = String.valueOf(prod.getId()).length();
            int form2a = prod.getName().length();
            int form3a = String.valueOf(basketM.get(prod)).length();

            if (form1 <= form1a) form1 = form1a;
            if (form2 <= form2a) form2 = form2a;
            if (form3 <= form3a) form3 = form3a;

        }


        String formater1 = "%" + form1 + "s";
        String formater2 = "%" + form2 + "s";
        String formater3 = "%" + form3 + "s";


        String output = "";


        String l1 = multi(form1, "─");
        String l2 = multi(form2, "─");
        String l3 = multi(form3, "─");


        output += "┌" + l1 + "┬" + l2 + "┬" + l3 + "┐\n";


        String prodIdH = String.format(formater1, "Product ID");
        String prodNameH = String.format(formater2, "Name");
        String prodQuantityH = String.format(formater3, "Quantity");

        output += "│" + prodIdH + "│" + prodNameH + "│" + prodQuantityH + "│\n";

        output += "├" + l1 + "┼" + l2 + "┼" + l3 + "┤\n";


        for (Product product : keyS) {

            String prodId = String.format(formater1, product.getId());
            String prodName = String.format(formater2, product.getName());
            String prodQuantity = String.format(formater3, String.valueOf(basketM.get(product)));
            output += "│" + prodId + "│" + prodName + "│" + prodQuantity + "│\n";

        }
        output += "└" + l1 + "┴" + l2 + "┴" + l3 + "┘\n";


        System.out.print(output);


    }

    public static void printProductTable(ProductList products){

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


        output += "┌" + l1 + "┬" + l2 + "┬" + l3 +"┬" + l4 +"┬" + l5 + "┐\n";


        String prodIdH = String.format(formater1, "Product ID");
        String prodNameH = String.format(formater2, "Name");
        String prodPriceH = String.format(formater3, "Price");
        String prodAmountH = String.format(formater4, "Amount");
        String prodCategoryH = String.format(formater5, "Category");

        output += "│" + prodIdH + "│" + prodNameH + "│" + prodPriceH +"│"+ prodAmountH +"│"+ prodCategoryH+"│\n";

        output += "├" + l1 + "┼" + l2 + "┼" + l3 +"┼" + l4 +"┼" + l5 +"┤\n";

        for (Product product : productList) {

            String prodId = String.format(formater1, product.getId());
            String prodName = String.format(formater2, product.getName());
            String prodPrice = String.format(formater3, product.getPrice());
            String prodAmount = String.format(formater4, product.getAmount());
            String prodCategory = String.format(formater5, product.getCategoryName());

            output += "│" + prodId + "│" + prodName + "│" + prodPrice +"│"+ prodAmount +"│"+ prodCategory+"│\n";

        }

        output += "└" + l1 + "┴" + l2 + "┴" + l3 +"┴" + l4 + "┴" + l5 +"┘\n";

        System.out.println(output);
        
    }

    public static int askForInt(String question){
        System.out.println(question);
        Scanner scanner = new Scanner(System.in);
        int out = scanner.nextInt();
        return out;
    }

  
    public static void printMessage(String message){
        System.out.print("\n"+message+"\n");
    }

  
  

    public static String askForString(String question){
        System.out.println(question);
        Scanner scanner = new Scanner(System.in);
        String out = scanner.next();
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


    public static void printOrdersTable(OrdersList orders, ProductList products){

        for (Order order: orders.getOrders()) {
            printMessage("Order number: " + order.getId()+", Status: "+order.getStatus()+", Date: "+order.getDate());

            int totalPrice = 0;
            ArrayList<Product> emptyList = new ArrayList<>();
            ProductList productsInOrder = new ProductList(emptyList);
            OrderItemsList orderedItemsList = new OrderItemsList(new OrdersItemsDAO().read(order));

            for (OrderItem orderItem: orderedItemsList.getItems()) {
                    Product product = products.getProductById(orderItem.getItemId());
                    product.setAmount(orderItem.getAmount());
//                    System.out.println(product);
                    productsInOrder.add(product);
                    totalPrice += product.getPrice();
                }
                printProductTable(productsInOrder);
                printMessage("Total price for this order: " + totalPrice + "$.\n");


        }
    }
}

