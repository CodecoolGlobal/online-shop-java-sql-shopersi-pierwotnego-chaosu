package dao.sql;

import model.shop.Product;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ProductDAO implements DAO {
    public void create() {

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
                float price = rs.getFloat("price");
                int amount = rs.getInt("amount");
                boolean isAvailable = (rs.getInt("isAvailable") != 0);
                int category = (rs.getInt("categoryId"));

                list.add((new Product(id, name, price, amount, isAvailable, category)));
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
            preparedStmt.setFloat(2, product.getPrice());
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

}
