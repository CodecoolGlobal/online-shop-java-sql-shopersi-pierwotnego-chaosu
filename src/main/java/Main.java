import dao.sql.BasketsDAO;
import dao.sql.DataSource;
import dao.sql.ProductDAO;
import dao.sql.UsersDAO;
import model.shop.Basket;
import model.shop.Product;
import model.shop.User;
import controler.LogInController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        LogInController logIn = new LogInController();
        logIn.run();

    }
}