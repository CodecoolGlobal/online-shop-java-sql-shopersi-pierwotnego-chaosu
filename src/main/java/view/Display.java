package view;

import model.controller.FileReaderForDispaly;
import model.shop.Basket;
import model.shop.Product;

import java.io.IOException;
import java.util.HashMap;
import java.util.Set;

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
}

