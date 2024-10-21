package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

    // Database URL, Username, and Password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/mpms"; // Replace 'mpms' with your actual database name
    private static final String DB_USERNAME = "root"; // Replace with your MySQL username
    private static final String DB_PASSWORD = "12345678"; // Replace with your MySQL password

    // Method to establish a connection to the database
    public static Connection getConnection() throws SQLException {
        Connection connection = null;

        try {
            // Load MySQL JDBC Driver (not always necessary in recent versions)
            Class.forName("com.mysql.cj.jdbc.Driver"); 
            
            // Establish the connection
            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error connecting to the database.");
            e.printStackTrace();
            throw e;
        }

        return connection;
    }

    // Optional: Method to close the connection
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing the connection.");
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        Connection connection = null;
        try {
            // Establish a connection
            connection = DBConnect.getConnection();
            
            if (connection != null) {
                System.out.println("Connected to the database successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the connection when done
            DBConnect.closeConnection(connection);
        }
    }
}
