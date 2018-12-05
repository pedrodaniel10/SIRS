package pt.ulisboa.tecnico.sirs.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.springframework.core.io.ClassPathResource;

import pt.ulisboa.tecnico.sirs.database.exceptions.DatabaseConnectionException;
 
public class DatabaseConnector {
	private Connection connection = null;
	private String url;
	private String username;
	private String password;
	private String configFilename = "database.properties";
    
    public DatabaseConnector() throws DatabaseConnectionException {
    	try {
			this.setProperties();
		} catch (IOException e) {
			throw new DatabaseConnectionException(e.getMessage());
		}
    	try {
			this.createConnection();
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			throw new DatabaseConnectionException(e.getMessage());
		}
    }
	
	public void setProperties() throws IOException {
		Properties props = new Properties();
		ClassPathResource resource = new ClassPathResource(configFilename);
		InputStream is = resource.getInputStream();
		props.load(is);
		this.url = props.getProperty("db.url");
		this.username = props.getProperty("db.username");
		this.password = props.getProperty("db.password");
	}
	
	public void createConnection() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		this.connection = DriverManager.getConnection(url, username, password);
	}
	
	public Connection getConnection() {
		return this.connection;
	}
}