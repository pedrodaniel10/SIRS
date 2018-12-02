package pt.ulisboa.tecnico.sirs.database;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.sirs.database.exceptions.DatabaseConnectionException;
import pt.ulisboa.tecnico.sirs.database.utils.DatabaseUtils;
import pt.ulisboa.tecnico.sirs.dataobjects.Citizen;
import pt.ulisboa.tecnico.sirs.dataobjects.Citizen.Gender;
import pt.ulisboa.tecnico.sirs.dataobjects.Citizen.Role;

public class DatabaseDocPatRelationOperations {
	
	private Connection conn;
	private Citizen citizen;
	/*
	@Before
	public void setup() throws DatabaseConnectionException, SQLException {
		this.conn = (new DatabaseConnector()).getConnection();
		this.citizen = new Citizen("test_id", "paulo", Gender.MALE, LocalDate.of(2000, 1, 1), "paulo@paulos.pt", 
				"jálhedigo", "path", "super", new ArrayList<Role>());
		DatabaseUtils.addCitizen(this.conn, this.citizen);
		this.citizen.addRole(Role.DOCTOR);
		
	}
	
	@Test
	public void noRoles() throws DatabaseConnectionException, SQLException {
		DatabaseUtils.addCitizen(this.conn, this.citizen);
		Citizen result = DatabaseUtils.getCitizenById(this.conn, "test_id");
		assertEquals(result.getCitizenId(), this.citizen.getCitizenId());
	}
	*/
	
	
}
