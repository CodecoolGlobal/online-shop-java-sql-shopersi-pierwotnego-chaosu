import dao.sql.BasketsDAO;
import dao.sql.DataSource;
import dao.sql.ProductDAO;
import dao.sql.UsersDAO;
import model.shop.Basket;
import model.shop.Product;
import model.shop.User;
import controler.LogInController;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Product> products = new ProductDAO().read();
        System.out.println(products);
//        Product product = new Product("Matuzalem iDx", 29.99, 10, true, 1);
//        new ProductDAO().create(product);
//        ArrayList<Product> products2 = new ProductDAO().read();
//        System.out.println(products2);


//        LogInController logIn = new LogInController();
//        logIn.run();

    }
}