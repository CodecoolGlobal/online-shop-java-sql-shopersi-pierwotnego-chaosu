package dao.sql;

import java.sql.*;

public class DataSource {
    private static String url = "jdbc:sqlite:src/main/resources/onlineshop.db";
    private static String driverName = "org.sqlite.JDBC";
//    private static String username = "root";
//    private static String password = "root";
    private static Connection con;
//    private static String urlstring;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    public static Connection getConnection() {
        try {

            Class.forName(driverName);
            con = DriverManager.getConnection(url);
            }
        catch (SQLException ex) {
            // log an exception. fro example:
            System.out.println("Failed to create the database connection.");
            }
        catch (ClassNotFoundException ex) {
            // log an exception. for example:
            System.out.println("Driver not found.");
            }
        return con;
    }

    public static ResultSet executeQuery(String sql){
        con = DataSource.getConnection();
//        System.out.println("Opened database successfully");
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
    }
        return rs;
    }

    public static void close(){
        try {
            con.close();
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
