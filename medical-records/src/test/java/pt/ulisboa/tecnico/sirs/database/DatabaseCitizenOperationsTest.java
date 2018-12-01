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

public class DatabaseCitizenOperationsTest {
	
	private Citizen citizen = new Citizen("test_id", "paulo", Gender.MALE, LocalDate.of(2000, 1, 1), "paulo@paulos.pt", 
			"jálhedigo", "path", "super", new ArrayList<Role>());
	private Connection conn;
	
	@Before
	public void setup() throws DatabaseConnectionException {
		this.conn = (new DatabaseConnector()).getConnection();
	}
	
	@Test
	public void noRoles() throws DatabaseConnectionException, SQLException {
		DatabaseUtils.addCitizen(this.conn, this.citizen);
		Citizen result = DatabaseUtils.getCitizenById(this.conn, "test_id");
		assertEquals(result.getCitizenId(), this.citizen.getCitizenId());
	}
	
	@Test
	public void allRoles() throws DatabaseConnectionException, SQLException {
		this.citizen.addRole(Role.DOCTOR);
		this.citizen.addRole(Role.ADMIN);
		this.citizen.addRole(Role.SUPERUSER);
		DatabaseUtils.addCitizen(this.conn, this.citizen);
		Citizen result = DatabaseUtils.getCitizenById(this.conn, "test_id");
		assertEquals(result.getCitizenId(), this.citizen.getCitizenId());
	}
	
	@Test
	public void updateCitizen() throws SQLException {
		this.citizen.addRole(Role.DOCTOR);
		DatabaseUtils.addCitizen(conn, citizen);
		
		this.citizen.setCitizenName("polo");
		this.citizen.addRole(Role.ADMIN);
		this.citizen.removeRole(Role.DOCTOR);
		DatabaseUtils.updateCitizen(conn, citizen);
	}
}
