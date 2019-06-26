package dao.sql;

import model.shop.Product;

import java.sql.ResultSet;
import java.util.ArrayList;

public class BasketsDAO implements DAO {


    private int userId;

    public void create() {

    }

    public ArrayList<Product> read() {
//        Connection c = null;
        ArrayList<Product> list = new ArrayList<Product>();
        try {
            DataSource ds = new DataSource();
            ResultSet res = ds.executeQuery("SELECT USERID, PRODUCTID FROM BASKETS WHERE USERID=" + this.userId + ";");

            while (res.next()) {
                int productID = res.getInt("productId");

                ResultSet rs = ds.executeQuery("SELECT * FROM PRODUCTS WHERE id=" + productID + ";");

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    int amount = rs.getInt("amount");
                    boolean isAvailable = (rs.getInt("isAvailable") != 0);
                    int category = (rs.getInt("categoryId"));
                    Product product = new Product(name, price, amount, isAvailable, category);
                    product.setId(id);
                    list.add(product);
                }
            ds.close();
            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return list;


    }

    public void update() {

    }

    public void delete() {

    }

    public void setUserId(int userId) {
        this.userId = userId;

    }
}
