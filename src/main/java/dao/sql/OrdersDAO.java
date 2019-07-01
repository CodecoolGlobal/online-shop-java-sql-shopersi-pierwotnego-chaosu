package dao.sql;

import model.shop.Basket;
import model.shop.Order;
import model.shop.Product;
import model.shop.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class OrdersDAO implements DAO {

    public void create() {
    }

    public void create(User user, Basket basket) {

        Order newOrder = new Order(basket);
        newOrder.setStatusAsSubmit();
        try
        {
            Connection c = new DataSource().getConnection();

            String query = " insert into orders (userId, status, date)"
                    + " values (?, ?, ?)";

            PreparedStatement preparedStmt = c.prepareStatement(query);
            preparedStmt.setInt(1, user.getId());
            preparedStmt.setString(2, newOrder.getStatus());
            preparedStmt.setString(3, newOrder.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));

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

    public ArrayList<Order> readOrders(User user, Basket basket){
        Connection c = null;
        ArrayList<Order> list = new ArrayList<Order>();
        DataSource ds = new DataSource();
        try (ResultSet rs = ds.executeQuery("SELECT * FROM ORDERS;")){
            while(rs.next()){
                int id = rs.getInt("id");
                int userId = rs.getInt("userId");
                String status = rs.getString("status");
                String date = rs.getString("date");

                Order newOrder = new Order(basket);
                newOrder.setId(id);
                newOrder.setUserId(userId);
                newOrder.setStatusAsSubmit();
                list.add(newOrder);
            }
//            rs.close();

        } catch ( SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }finally{
            ds.close();
        };
        return list;
    }


    public ArrayList<Order> read(){
        return null;

    }

    public void update() {

    }

    public void delete() {

    }

}

