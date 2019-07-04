package dao.sql;

import model.shop.Product;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class ProductDAO implements DAO {
    public void create(){}

    public void create(Product product) {

        try (Connection c = new DataSource().getConnection()) {
            String query = " insert into products (name, price, amount, isAvailable, categoryId, isOnStock)"
                    + " values (?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = c.prepareStatement(query);
            preparedStmt.setString(1, product.getName());
            preparedStmt.setDouble(2, product.getPrice());
            preparedStmt.setInt(3, product.getAmount());
            preparedStmt.setInt(4, product.isAvailable() ? 1 : 0);
            preparedStmt.setInt(5, product.getCategory());
            preparedStmt.setInt(6, product.isOnStock() ? 1 : 0);
            preparedStmt.execute();

        } catch (SQLException e) {
            System.err.println("SQL exception when creating. ");
            e.printStackTrace();
        }
    }

    public ArrayList<Product> read(){
//        Connection c = new DataSource().getConnection();
        ArrayList<Product> list = new ArrayList<Product>();
//        DataSource ds = new DataSource();
        try (ResultSet rs = new DataSource().executeQuery("SELECT * FROM PRODUCTS;")) {
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int amount = rs.getInt("amount");
                boolean isAvailable = (rs.getInt("isAvailable") != 0);
                int category = (rs.getInt("categoryId"));
                boolean isOnStock = (rs.getInt("isOnStock") != 0);

                Product product = new Product(name, price, amount, isAvailable, category, isOnStock);
                product.setId(id);
                list.add(product);
            }

        } catch (SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return list;
    }

    public void update(){}

    public void update(Product product) {

        ArrayList<Product> list = new ArrayList<Product>();
        int available = product.isAvailable() ?  1 : 0;
        int onStock = product.isOnStock() ? 1 : 0;
//        System.out.println(product.getName() + " PRICE: " + product.getPrice() + " AMOUNT: " +  product.getAmount() + " AVAILABLE: " +  available + " CAT: " + product.getCategory() + " ID: " + product.getId());
        try (Connection c = new DataSource().getConnection())
        {

            String query = "UPDATE products SET name = ?, price = ?, amount= ? , isAvailable = ? , categoryId = ?, isOnStock = ? WHERE id = ?";
            PreparedStatement preparedStmt = c.prepareStatement(query);

            preparedStmt.setString(1, product.getName());
            preparedStmt.setDouble(2, product.getPrice());
            preparedStmt.setInt(3, product.getAmount());
            preparedStmt.setInt(4, available);
            preparedStmt.setInt(5, product.getCategory());
            preparedStmt.setInt(6, onStock);
            preparedStmt.setInt(7, product.getId());

            preparedStmt.executeUpdate();

        }
        catch (SQLException e)
        {
            System.err.println("SQL exception when updating. ");
            System.err.println(e.getMessage());
        }

    }

    public void delete() {

    }

    public void delete(Product product) {
        try (Connection c = new DataSource().getConnection())
        {

            PreparedStatement st = c.prepareStatement("DELETE FROM products WHERE id = " + product.getId() + ";");
            st.executeUpdate();
        }
        catch(SQLException e)
        {
            System.err.println("SQL exception when deleting. ");
            System.out.println(e);
        }

    }

}
