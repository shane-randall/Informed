package demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

import demo.jdbc.util.DbProperties;

public class SavepointDemo {

    public static void main(String[] args) {
        
        // Set up a default JDBC driver and database name.
        String jdbcDriver = DbProperties.JDBC_DRIVER;
        String databaseUri = DbProperties.DATABASE_URI;

        if (args.length == 2) {
            jdbcDriver = args[0];
            databaseUri = args[1];
        }

        // Load JDBC driver.
        try {
            Class.forName(jdbcDriver);
        } catch (ClassNotFoundException e) {
            System.out.println("Error loading JDBC driver: " + e);
        }

        // Connect to a database.
        Connection cn = null;
        try {
            cn = DriverManager.getConnection(databaseUri);
        } catch (SQLException e) {
            System.out.println("Error connecting to a database: " + e);
        }

        // Create and use Savepoints.
        Savepoint savepoint1 = null;
        try {

          cn.setAutoCommit(false);
          Statement stmt = cn.createStatement(); 

          // Execute a SQL statement.
          String sql = "INSERT INTO MySchema.Employees (Name, Salary, Region) VALUES ('Claire', 1000.00, 'UK')";
          stmt.executeUpdate(sql);  

          // Create a savepoint.
          savepoint1 = cn.setSavepoint("Savepoint1");

          // Execute another SQL statement.
          sql = "INSERT INTO MySchema.Employees (Name, Salary, Region) VALUES ('Ruth', 2000.00, 'USA')";     
          stmt.executeUpdate(sql);

          // If there is no error, commit the changes.
          cn.commit();
          System.out.printf("Operations completed successfully.\n");
        }
        catch (SQLException ex){

            System.out.printf("Exception %s (so rolling back to the savepoint).\n", ex.getMessage());
        	try {
        	   cn.rollback(savepoint1);
        	} catch (SQLException e) {}
        }
    }
}
