import dao.sql.DataSource;
import dao.sql.ProductDAO;
import model.shop.Product;

import java.sql.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Product> list = new ProductDAO().read();

        System.out.println(list);

        ArrayList<Product> list2 = new ProductDAO().read();

        System.out.println(list2);
    }


    public static int getCategoryId(String category){

        Connection c = null;

        int colId=0;

        try {
            c = DataSource.getConnection();
            System.out.println("Opened database successfully");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM CATEGORIES where NAME = \'"+ category + "\' ");
            while(rs.next()){
                colId = rs.getInt("id");
                System.out.println(colId);
            }
            rs.close();
            stmt.close();
            c.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }

        return colId;
    }

    public static void testing(){
        Connection c = null;

        try {
            c = DataSource.getConnection();
            System.out.println("Opened database successfully");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCTS;");
            while(rs.next()){
                String name = rs.getString("name");
                System.out.println(name);
            }
            rs.close();
            stmt.close();
            c.close();

        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }
}