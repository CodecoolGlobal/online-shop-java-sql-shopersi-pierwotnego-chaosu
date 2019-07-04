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

    public void create(User user) {

        Order newOrder = new Order();
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

    public ArrayList<Order> readOrders(User user){
        Connection c = null;
        ArrayList<Order> list = new ArrayList<Order>();
        DataSource ds = new DataSource();
        try (ResultSet rs = ds.executeQuery("SELECT * FROM ORDERS WHERE \"userId\" LIKE '"+user.getId()+"';" )){
            while(rs.next()){
                int id = rs.getInt("id");
                int userId = rs.getInt("userId");
                String status = rs.getString("status");
                String date = rs.getString("date");

                Order newOrder = new Order();
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
        }
        return list;
    }


    public ArrayList<Order> read(){
        return null;

    }

    public void update(User user, Order order) {

//        System.out.println(product.getName() + " PRICE: " + product.getPrice() + " AMOUNT: " +  product.getAmount() + " AVAILABLE: " +  available + " CAT: " + product.getCategory() + " ID: " + product.getId());
        try (Connection c = new DataSource().getConnection())
        {

            String query = "UPDATE orders SET status = ? WHERE id = ?";
            PreparedStatement preparedStmt = c.prepareStatement(query);

            preparedStmt.setInt(1, user.getId());
            preparedStmt.setString(2, order.getStatus());
            preparedStmt.setString(3, order.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));


            preparedStmt.executeUpdate();

        }
        catch (SQLException e)
        {
            System.err.println("SQL exception when updating. ");
            System.err.println(e.getMessage());
        }

    }
    public void update() {}

    public void delete() {

    }

    public ArrayList<Order> readAllOrders(){
        Connection c = null;
        ArrayList<Order> list = new ArrayList<Order>();
        DataSource ds = new DataSource();
        try (ResultSet rs = ds.executeQuery("SELECT * FROM ORDERS;" )){
            while(rs.next()){
                int id = rs.getInt("id");
                int userId = rs.getInt("userId");
                String status = rs.getString("status");
                String date = rs.getString("date");

                Order newOrder = new Order();
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
        }
        return list;
    }
}

