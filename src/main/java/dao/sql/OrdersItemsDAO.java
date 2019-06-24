package dao.sql;

import model.shop.OrdersItems;
import model.shop.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class OrdersItemsDAO implements DAO {
    public void create() {

    }

    public ArrayList<OrdersItems> read(){
        Connection c = null;
        ArrayList<OrdersItems> list = new ArrayList<OrdersItems>();
        try {
            DataSource ds = new DataSource();
            ResultSet rs = ds.executeQuery("SELECT * FROM ORDERS_ITEMS;");
            while(rs.next()){
                int id = rs.getInt("id");
                int orderId = rs.getInt("orderId");
                int itemId = rs.getInt("itemId");
                int amount = rs.getInt("amount");
                float price = rs.getFloat("price");

                list.add((new OrdersItems(id, orderId, itemId, amount, price)));
            }
            rs.close();

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
