package dao.sql;

import model.shop.OrdersItems;
import model.shop.Product;
import model.shop.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class UsersDAO implements DAO {

    public void create() {

    }

    public ArrayList<User> read(){
        Connection c = null;
        ArrayList<User> list = new ArrayList<User>();
        try {
            DataSource ds = new DataSource();
            ResultSet rs = ds.executeQuery("SELECT * FROM USERS;");
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                int isAdmin = rs.getInt("isAdmin");

                User newUser = new User();
                newUser.setAdmin(isAdmin);
                newUser.setName(name);
                newUser.setPassowrd(password);
                newUser.setId(id);
                list.add(newUser);
            }
            rs.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        };
        return list;
    }


    public User readUser(String userName, String userPassword){
        Connection c = null;
        User newUser = new User();
        try {
            DataSource ds = new DataSource();
            ResultSet rs = ds.executeQuery("SELECT * FROM USERS WHERE \"name\" LIKE '"+userName+"' AND \"password\" LIKE '"+userPassword+"';");
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                int isAdmin = rs.getInt("isAdmin");

                newUser.setAdmin(isAdmin);
                newUser.setName(name);
                newUser.setPassowrd(password);
                newUser.setId(id);

                //                list.add((new User(name,password,isAdmin)));
            }
            rs.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        };
        return newUser;
    }

    public void update() {

    }

    public void delete() {

    }

}
