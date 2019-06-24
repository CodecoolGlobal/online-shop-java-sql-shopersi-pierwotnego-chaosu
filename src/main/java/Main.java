import dao.sql.BasketsDAO;
import dao.sql.DataSource;
import dao.sql.ProductDAO;
import dao.sql.UsersDAO;
import model.shop.Basket;
import model.shop.Product;
import model.shop.User;

import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        User newUser = new UsersDAO().readUser("Dejw","dejw");
                    System.out.println(newUser.getName());
            System.out.println(newUser.getPassowrd());
            System.out.println(newUser.getId());
            System.out.println(newUser.getAdmin());
    }

}