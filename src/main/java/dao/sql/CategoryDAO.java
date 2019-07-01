package dao.sql;

import model.shop.Category;
import model.shop.Product;

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
                Category category = new Category(id, name);
                list.add(category);
            }

        } catch (SQLException e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
        return list;
    }


}
