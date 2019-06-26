package dao.sql;

import model.shop.Product;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;

public class ProductDAO implements DAO {
    public void create(){};

    public void create(Product product) {

        try
        {
            Connection c = new DataSource().getConnection();

            String query = " insert into products (name, price, amount, isAvailable, categoryId)"
                    + " values (?, ?, ?, ?, ?)";

            PreparedStatement preparedStmt = c.prepareStatement(query);
            preparedStmt.setString(1, product.getName());
            preparedStmt.setDouble(2, product.getPrice());
            preparedStmt.setInt(3, product.getAmount());
            preparedStmt.setInt(4, product.isAvailable() ? 1 : 0);
            preparedStmt.setInt(5, product.getCategory());

            // execute the preparedstatement
            preparedStmt.execute();

            c.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception!");
            System.err.println(e.getMessage());
        }

    }

    public ArrayList<Product> read(){
        Connection c = new DataSource().getConnection();
        ArrayList<Product> list = new ArrayList<Product>();
        try {
            DataSource ds = new DataSource();
            ResultSet rs = ds.executeQuery("SELECT * FROM PRODUCTS;");
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int amount = rs.getInt("amount");
                boolean isAvailable = (rs.getInt("isAvailable") != 0);
                int category = (rs.getInt("categoryId"));
                Product product = new Product(name, price, amount, isAvailable, category);
                product.setId(id);
                list.add(product);
            }
            ds.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        };
        return list;
    }

    public void update(){}

    public void update(Product product) {
        Connection c = new DataSource().getConnection();
        ArrayList<Product> list = new ArrayList<Product>();
        int available = product.isAvailable() ?  1 : 0;
        System.out.println(product.getName() + " PRICE: " + product.getPrice() + " AMOUNT: " +  product.getAmount() + " AVAILABLE: " +  available + " CAT: " + product.getCategory() + " ID: " + product.getId());
        try
        {

            String query = "UPDATE products SET name = ?, price = ?, amount= ? , isAvailable = ? , categoryId = ? WHERE id = ?";
            PreparedStatement preparedStmt = c.prepareStatement(query);

            preparedStmt.setString(1, product.getName());
            preparedStmt.setDouble(2, product.getPrice());
            preparedStmt.setInt(3, product.getAmount());
            preparedStmt.setInt(4, available);
            preparedStmt.setInt(5, product.getCategory());
            preparedStmt.setInt(6, product.getId());

            // execute the java preparedstatement
            preparedStmt.executeUpdate();

            c.close();
        }
        catch (Exception e)
        {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

    }

    public void delete() {

    }

    public void delete(Product product) {
        Connection c = new DataSource().getConnection();
        try
        {

            PreparedStatement st = c.prepareStatement("DELETE FROM products WHERE id = " + product.getId() + ";");
            st.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }

}
