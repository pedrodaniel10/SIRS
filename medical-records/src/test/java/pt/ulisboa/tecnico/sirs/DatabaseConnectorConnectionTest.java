package pt.ulisboa.tecnico.sirs;

import org.junit.Test;

import pt.ulisboa.tecnico.sirs.exception.DatabaseConnectionException;

public class DatabaseConnectorConnectionTest {
	
	@Test
	public void success() throws DatabaseConnectionException {
		DatabaseConnector dbConn = new DatabaseConnector();
	}
}
