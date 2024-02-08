package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;

    
    private DBConnection() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            try {
            
                Class.forName("com.mysql.cj.jdbc.Driver");

                
                String url = "jdbc:mysql://localhost:3306/virtualartgallery";
                String username = "root";
                String password = "admin";

                // Establish the connection
                connection = DriverManager.getConnection(url, username, password);

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
