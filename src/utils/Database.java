/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author vidur
 */
public class Database {

    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/vertex_pos";
//    private static final String URL = "jdbc:mysql://0.tcp.in.ngrok.io:11833/vertex_pos";

    private static final String USER = "root";
    private static final String PASSWORD = "6jfmd672@V";

    // Database connection object
    private Connection connection;

    // Singleton instance
    private static Database instance;

    private Database() {
        try {
            // Register MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish the connection to the database
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the database.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Get the singleton instance of Database
    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    // Get the database connection
    public Connection getConnection() {
        return connection;
    }

    // Close the database connection
    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet SELECT(String query) throws SQLException {
//         singleton patten | search
        Connection connection = getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        return statement.executeQuery();
    }
    
    public static ResultSet SELECT(PreparedStatement statement) throws SQLException {
//         singleton patten | search
        return statement.executeQuery();
    }

    public static void IUD(String query) throws SQLException {
        //        singleton patten | INSERT,UPDATE,DELETE
        Connection connection = getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        statement.executeUpdate();
    }

}

