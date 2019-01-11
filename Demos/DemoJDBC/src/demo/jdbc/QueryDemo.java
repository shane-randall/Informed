package demo.jdbc;

import java.sql.*;
import java.math.*;

public class QueryDemo {

    public static void main(String[] args) {
        
        // Load database driver
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading driver: " + e);
        }


        // Connect to a database
        Connection cnEmps = null;
        try {
            cnEmps = DriverManager.getConnection("jdbc:derby://localhost:1527/MyDatabase");
        } catch (SQLException e) {
            System.out.println("Error connecting to a database: " + e);
        }


        ResultSet rsEmps = null;
        try {
            // Execute a SQL query
            Statement st = cnEmps.createStatement();
            rsEmps = st.executeQuery("SELECT Name, Salary FROM MySchema.Employees");

            // Process the results of the query, one row at a time
            while (rsEmps.next() != false) {
                
                String name = rsEmps.getString(1);
                BigDecimal salary = rsEmps.getBigDecimal(2);

                // String     name   = rsEmps.getString("Name");
                // BigDecimal salary = rsEmps.getBigDecimal("Salary");

                System.out.println("Name: " + name + "\tsalary: " + salary);
            }
            
        } catch (SQLException e) {
            System.out.println("Error executing query: " + e);
        }
    }
}
