package dao.sql;

import model.shop.Category;
import model.shop.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryDAO {


    public ArrayList<Category> readCategoryList(){
        ArrayList<Category> list = new ArrayList<Category>();
        try (ResultSet rs = new DataSource().executeQuery("SELECT * FROM CATEGORIES;")) {
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Category category = new Category(name);
                category.setId(id);
                list.add(category);
            }

        } catch (SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return list;
    }


    public void create(Category category){

        try (Connection c = new DataSource().getConnection()) {
            String query = " insert into categories (name)"
                    + " values (?)";

            PreparedStatement preparedStmt = c.prepareStatement(query);
            preparedStmt.setString(1, category.getName());

            preparedStmt.execute();

        } catch (SQLException e) {
            System.err.println("SQL exception when creating. ");
            e.printStackTrace();
        }
    }



    public void update(Category category) {
        try (Connection c = new DataSource().getConnection())
        {

            String query = "UPDATE categories SET name = ? WHERE id = ?";
            PreparedStatement preparedStmt = c.prepareStatement(query);

            preparedStmt.setString(1, category.getName());
            preparedStmt.setInt(2, category.getId());

            preparedStmt.executeUpdate();

        }
        catch (SQLException e)
        {
            System.err.println("SQL exception when updating. ");
            System.err.println(e.getMessage());
        }

    }


}
