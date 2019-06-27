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

        int form1 = 0;
        int form2 = 0;
        int form3 = 0;





        for (Product prod: keyS) {

            int form1a = String.valueOf(prod.getId()).length();
            int form2a = prod.getName().length();
            int form3a = String.valueOf(basketM.get(prod)).length();

            if (form1<=form1a) form1 = form1a;
            if (form2<=form2a) form2 = form2a;
            if (form3<=form3a) form3 = form3a;

        }
        System.out.println("\n");
        System.out.println("form1 = "+form1);
        System.out.println("form2 = "+form2);
        System.out.println("form3 = "+form3+"\n");




        String formater1 = "%"+form1+1+"s";
        String formater2 = "%"+form3+1+"s";
        String formater3 = "%"+form3+1+"s";
        String formater4 = "%15s";
        String doubleF = "%.3f";
        String output ="";
        output += "┌───────────────────────────────────────────────────────────────────────────────────────────────────────────────┐\n";

        String prodIdH = String.format(formater1,"Product ID");
        String prodNameH = String.format(formater2, "Name");
        String prodQuantityH = String.format(formater3,"Quantity");

        output += "│"+prodIdH+"│"+prodNameH+"│"+prodQuantityH+"│\n";

        for (Product product:basketM.keySet()) {

            String prodId = String.format(formater1,product.getId());
            String prodName = String.format(formater2, product.getName());
            String prodQuantity = String.format(formater3, String.valueOf(basketM.get(product)));
            output += "│"+prodId+"│"+prodName+"│"+prodQuantity+"│\n";





        }
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
}

