package dao.sql;

import java.sql.*;

public class DataSource {
    private String url;
    private String driverName;

    public DataSource(){
        this.url = "jdbc:sqlite:src/main/resources/onlineshop.db";
        this.driverName = "org.sqlite.JDBC";
    }
//    private static String username = "root";
//    private static String password = "root";
    private Connection con;
//    private static String urlstring;
    private Statement stmt = null;
    private ResultSet rs = null;

    public Connection getConnection() {
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

    public ResultSet executeQuery(String sql){
        DataSource ds = new DataSource();
        this.con = ds.getConnection();
//        System.out.println("Opened database successfully");
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
    }
        return rs;
    }

    public void updateQuery(String sql){
        DataSource ds = new DataSource();
        this.con = ds.getConnection();
//        System.out.println("Opened database successfully");
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        try {
            con.close();
            stmt.close();
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
