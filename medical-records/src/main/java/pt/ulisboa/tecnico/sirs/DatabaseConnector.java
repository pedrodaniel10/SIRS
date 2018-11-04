package pt.ulisboa.tecnico.sirs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;
 
public class DatabaseConnector {
	private Connection connection = null;
	@Value("${db.url}")
	private String url;
	@Value("${db.username}")
	private String username;
    @Value("${db.password}")
	private String password;
	
	public void createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}