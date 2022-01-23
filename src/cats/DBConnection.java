package cats;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String SCHEMA = "cats";
	private static final String Username = "cats";
	private static final String Password = "cats";
	private static final String Host = "localhost";
	private static final int Port = 3306;
	
	
	static Connection getConnection() {
		String uri = String.format("jdbc:mysql://%s:%d/%s", Host, Port, SCHEMA);
		
		try {
			return DriverManager.getConnection(uri, Username, Password);
		}catch(SQLException e) {
			throw new RuntimeException("Unable to get connection.", e);
		}
		
	}
}
