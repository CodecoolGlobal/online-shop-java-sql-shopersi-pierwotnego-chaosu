package dao.sql;

import model.shop.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsersDAO implements DAO {

    public void create() {
    }

    public void create(User user) {

        try
        {
            Connection c = new DataSource().getConnection();

            String query = " insert into users (name, isAdmin, password)"
                    + " values (?, ?, ?)";

            PreparedStatement preparedStmt = c.prepareStatement(query);
            preparedStmt.setString(1, user.getName());
            preparedStmt.setInt(2, user.getAdmin());
            preparedStmt.setString(3, user.getPassowrd());

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

        public ArrayList<User> read(){
        Connection c = null;
        ArrayList<User> list = new ArrayList<User>();
            DataSource ds = new DataSource();
            try (ResultSet rs = ds.executeQuery("SELECT * FROM USERS;")){
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

        } catch ( SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }finally{
                ds.close();
            };
        return list;
    }


    public User readUser(String userName){
        Connection c = null;
        User newUser = new User();
        try {
            DataSource ds = new DataSource();
            ResultSet rs = ds.executeQuery("SELECT * FROM USERS WHERE \"name\" LIKE '"+userName+"';");
            if (rs.next() && rs.getString("name").equals(userName)) {
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
