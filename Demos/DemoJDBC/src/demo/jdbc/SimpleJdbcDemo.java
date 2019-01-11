package demo.jdbc;

import java.sql.*;

public class SimpleJdbcDemo {

    public static void main(String[] args) {
        
        // Use database name if provided, else use the demo Derby database.
        String dbName;
        if (args.length == 1) {
            dbName = args[0];
        } else {
            dbName = "jdbc:derby://localhost:1527/MyDatabase";
        }

        // Load Derby driver.
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading JDBC/ODBC driver: " + e);
        }


        // Connect to a database.
        @SuppressWarnings("unused")
		Connection cnEmps = null;
        try {
            cnEmps = DriverManager.getConnection(dbName);
            System.out.println("Hooray!");
        } catch (SQLException e) {
            System.out.println("Error connecting to a database: " + e);
        }

    }
}
