package dao.sql;

import model.shop.Basket;
import model.shop.Order;
import model.shop.OrderItem;
import model.shop.Product;
import view.Display;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class OrdersItemsDAO implements DAO {


    public ArrayList<OrderItem> read(Order order){
        Connection c = null;
        ArrayList<OrderItem> list = new ArrayList<OrderItem>();
        try {
            DataSource ds = new DataSource();
            ResultSet rs = ds.executeQuery("SELECT * FROM ORDERS_ITEMS WHERE \"orderId\" LIKE '"+order.getId()+"';");

            while(rs.next()){
                int id = rs.getInt("id");
                int orderId = rs.getInt("orderId");
                int itemId = rs.getInt("itemId");
                int amount = rs.getInt("amount");
                float price = rs.getFloat("price");
                list.add((new OrderItem(id, orderId, itemId, amount, price)));
            }
            rs.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return list;
    }


    public void create(Basket basket, Order order) {

        HashMap<Product, Integer> basketMap = basket.getProducts();
            for (Product product : basketMap.keySet()) {
                try (Connection c = new DataSource().getConnection()) {
                    String query = " insert into orders_items (orderId, itemId, amount , price)"
                            + " values (?, ?, ?, ?)";

                    PreparedStatement preparedStmt = c.prepareStatement(query);
                    preparedStmt.setInt(1, order.getId());
                    preparedStmt.setInt(2, product.getId());
                    preparedStmt.setInt(3, basketMap.get(product));
                    preparedStmt.setDouble(4, product.getPrice());

                    preparedStmt.execute();

                } catch (SQLException e) {
                    System.err.println("SQL exception when creating. ");
                    e.printStackTrace();
                }
            }
    }

    public void create() {
    }

    public ArrayList<OrderItem> read()    {return null;}

    public void update() {
    }

    public void delete() {

    }

}
