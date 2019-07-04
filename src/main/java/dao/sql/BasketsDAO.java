package dao.sql;

import model.shop.Product;
import model.shop.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class BasketsDAO implements DAO {


    private int userId;

    public void create() {

    }

    public ArrayList<HashMap<Product, Integer>> read() {
//        Connection c = null;
        HashMap<Product, Integer> hM = new HashMap<>();
        ArrayList<HashMap<Product, Integer>> list = new ArrayList<>();
        try {
            DataSource ds = new DataSource();
            ResultSet res = ds.executeQuery("SELECT USERID, PRODUCTID, AMOUNT FROM BASKETS WHERE USERID=" + this.userId + ";");

            while (res.next()) {

                int amountB = res.getInt("amount");


//                for (int i = 0; i < amountB; i++) {


                int productID = res.getInt("productId");

                ResultSet rs = ds.executeQuery("SELECT * FROM PRODUCTS WHERE id=" + productID + ";");

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    float price = rs.getFloat("price");
                    int amount = rs.getInt("amount");
                    boolean isAvailable = (rs.getInt("isAvailable") != 0);
                    int category = (rs.getInt("categoryId"));
                    boolean isOnStock = (rs.getInt("isOnStock") != 0);
                    Product product = new Product(name, price, amount, isAvailable, category, isOnStock);
                    product.setId(id);
//                    list.add(product);
                    hM.put(product,amountB);
                }
                list.add(hM);
                ds.close();
            }
//            }
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return list;

    }

    public void update() {

    }

    public void delete() {}

    public void delete(User user) {
        try (Connection c = new DataSource().getConnection())
        {

            PreparedStatement st = c.prepareStatement("DELETE FROM baskets WHERE userId = " + user.getId() + ";");
            st.executeUpdate();
        }
        catch(SQLException e)
        {
            System.err.println("SQL exception when deleting. ");
            System.out.println(e);
        }
    }

    public void setUserId(int userId) {
        this.userId = userId;

    }
}
