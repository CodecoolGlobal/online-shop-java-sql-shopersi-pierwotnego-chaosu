import java.sql.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(getCategoryId("Animals"));
    }


    public static int getCategoryId(String category){

        Connection c = null;

        int colId=0;

        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/onlineshop.db");
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
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:src/main/resources/onlineshop.db");
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