package demo.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class DbProperties {

	public static String JDBC_DRIVER;
	public static String DATABASE_URI;
	
	static {
		
		Properties prop = new Properties();

		// Open properties file on classpath.
		try (InputStream input = DbProperties.class.getClassLoader().getResourceAsStream("db.properties")) {
			
			
			// Get properties from properties file.
			prop.load(input);
			JDBC_DRIVER = prop.getProperty("JDBC_DRIVER");
			DATABASE_URI = prop.getProperty("DATABASE_URI");	 
		
		} 
		catch (IOException ex) {
			System.err.printf("Error reading Properties file: %s\n", ex.getMessage());
		}
	}
}
