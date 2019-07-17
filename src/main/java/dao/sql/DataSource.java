package dao.sql;

import java.sql.*;

public class DataSource {
    private String url;
    private String driverName;

    public DataSource(){
        this.url = "jdbc:sqlite:src/main/resources/onlineshop.db";
        this.driverName = "org.sqlite.JDBC";
    }
    private Connection con;
    private Statement stmt = null;
    private ResultSet rs = null;

    public Connection getConnection() {
        try {

            Class.forName(driverName);
            con = DriverManager.getConnection(url);
            }
        catch (SQLException ex) {
            System.out.println("Failed to create the database connection.");
            }
        catch (ClassNotFoundException ex) {
            System.out.println("Driver not found.");
            }
        return con;
    }

    public ResultSet executeQuery(String sql){
        DataSource ds = new DataSource();
        this.con = ds.getConnection();
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
