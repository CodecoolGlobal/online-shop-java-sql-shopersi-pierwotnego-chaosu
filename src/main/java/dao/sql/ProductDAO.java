package dao.sql;

import model.shop.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductDAO implements DAO {
    public void create() {

    }

    public ArrayList<Product> read(){
        Connection c = null;
        ArrayList<Product> list = new ArrayList<Product>();
        try {
            ResultSet rs = DataSource.executeQuery("SELECT * FROM PRODUCTS;");
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                float price = rs.getFloat("price");
                int amount = rs.getInt("amount");
                boolean isAvailable = (rs.getInt("isAvailable") != 0);
                int category = (rs.getInt("categoryId"));

                list.add((new Product(id, name, price, amount, isAvailable, category)));
            }
            DataSource.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        };
        return list;
    }

    public void update() {

    }

    public void delete() {

    }

}
