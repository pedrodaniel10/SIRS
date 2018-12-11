package pt.ulisboa.tecnico.sirs.database;

import org.junit.Test;

import pt.ulisboa.tecnico.sirs.database.exceptions.DatabaseConnectionException;

public class DatabaseConnectorConnectionTest {
	
	@Test
	public void success() throws DatabaseConnectionException {
		new DatabaseConnector();
	}
}
