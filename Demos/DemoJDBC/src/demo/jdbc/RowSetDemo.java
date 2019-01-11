package demo.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

import demo.jdbc.util.DbProperties;

public class RowSetDemo {

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

        // Create and use a JdbcRowSet.
        JdbcRowSet jdbcRowSet;
        try {
        	// Create and configure a JdbcRowSet object.
        	RowSetFactory rowSetFactory = RowSetProvider.newFactory();
        	jdbcRowSet = rowSetFactory.createJdbcRowSet();

        	jdbcRowSet.setUrl(databaseUri);
        	jdbcRowSet.setType(ResultSet.TYPE_SCROLL_INSENSITIVE);
            jdbcRowSet.setCommand("SELECT Name, Salary FROM MySchema.Employees");
            
            // Execute the command in the RowSet, and iterate through the results.
            jdbcRowSet.execute();
            while (jdbcRowSet.next()) {
              System.out.printf("%s earns %.2f\n", jdbcRowSet.getString(1), jdbcRowSet.getBigDecimal(2));
            }
        } catch (SQLException e) {
            System.out.println("Error using JdbcRowSet: " + e);
        }
    }
}
