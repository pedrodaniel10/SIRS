package pt.ulisboa.tecnico.sirs.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.sirs.database.exceptions.DatabaseConnectionException;
import pt.ulisboa.tecnico.sirs.database.utils.DatabaseUtils;
import pt.ulisboa.tecnico.sirs.dataobjects.Citizen;
import pt.ulisboa.tecnico.sirs.dataobjects.Citizen.Gender;
import pt.ulisboa.tecnico.sirs.dataobjects.Citizen.Role;
import pt.ulisboa.tecnico.sirs.dataobjects.DocPatRelation;
import pt.ulisboa.tecnico.sirs.dataobjects.Institution;
import pt.ulisboa.tecnico.sirs.dataobjects.MedicalRecord;
import pt.ulisboa.tecnico.sirs.dataobjects.Session;

public class DatabaseCitizenOperationsTest {
	
	private Connection conn;
	private Citizen c1;
	
	@Before
	public void setup() throws DatabaseConnectionException {
		this.conn = (new DatabaseConnector()).getConnection();
		this.c1 = new Citizen("test_id", "paulo", Gender.MALE, LocalDate.of(2000, 1, 1), "paulo@paulos.pt", 
				"jálhedigo", "path", "super", new ArrayList<Role>());
	}
	
	@Test
	public void noRoles() throws DatabaseConnectionException, SQLException {
		DatabaseUtils.addCitizen(this.conn, this.c1);
		Citizen result = DatabaseUtils.getCitizenById(this.conn, "test_id");
		assertEquals(result.getCitizenId(), this.c1.getCitizenId());
	}
	
	@Test
	public void allRoles() throws DatabaseConnectionException, SQLException {
		this.c1.addRole(Role.DOCTOR);
		this.c1.addRole(Role.ADMIN);
		this.c1.addRole(Role.SUPERUSER);
		DatabaseUtils.addCitizen(this.conn, this.c1);
		Citizen result = DatabaseUtils.getCitizenById(this.conn, "test_id");
		assertEquals(result.getCitizenId(), this.c1.getCitizenId());
	}
	
	@Test
	public void updateCitizen() throws SQLException {
		this.c1.addRole(Role.DOCTOR);
		DatabaseUtils.addCitizen(conn, c1);
		
		this.c1.setCitizenName("polo");
		this.c1.addRole(Role.ADMIN);
		this.c1.removeRole(Role.DOCTOR);
		DatabaseUtils.updateCitizen(conn, c1);
	}
	
	@Test
	public void updateCitizenWithRelations() throws SQLException {
		Citizen c2 = new Citizen("test_id2", "paulo", Gender.MALE, LocalDate.of(2000, 1, 1), "paulo@paulos.pt", 
				"jálhedigo", "path", "super", new ArrayList<Role>());
		Citizen c3 = new Citizen("test_id3", "paulo", Gender.MALE, LocalDate.of(2000, 1, 1), "paulo@paulos.pt", 
				"jálhedigo", "path", "super", new ArrayList<Role>());
		
		c1.addRole(Role.PATIENT);
		c1.addRole(Role.DOCTOR);
		c1.addRole(Role.ADMIN);
		c2.addRole(Role.PATIENT);
		c2.addRole(Role.DOCTOR);
		c3.addRole(Role.PATIENT);
		c3.addRole(Role.DOCTOR);
		
		DatabaseUtils.addCitizen(conn, c1);
		DatabaseUtils.addCitizen(conn, c2);
		DatabaseUtils.addCitizen(conn, c3);
		
		Institution i1 = new Institution(2, "second inst", "address", "pic", "super");
		DatabaseUtils.addInstitution(conn, i1);
		
		DatabaseUtils.setAdminInstitutionId(conn, c1.getCitizenId(), 1);
		DatabaseUtils.setDoctorInstitutionId(conn, c2.getCitizenId(), 1, c1.getCitizenId());
		DatabaseUtils.setDoctorInstitutionId(conn, c1.getCitizenId(), 1, c1.getCitizenId());
		
		DocPatRelation dpr1 = new DocPatRelation(0, Date.valueOf("1000-1-1"), Date.valueOf("1000-1-2"), c1.getCitizenId(), c3.getCitizenId(), c1.getCitizenId());
		DocPatRelation dpr2 = new DocPatRelation(0, new Date(1000), new Date(1005), c2.getCitizenId(), c1.getCitizenId(), c1.getCitizenId());
		DatabaseUtils.addDocPatRelation(conn, dpr1);
		DatabaseUtils.addDocPatRelation(conn, dpr2);
		
		ArrayList<DocPatRelation> dprs = DatabaseUtils.getDocPatRelationsByAdminId(conn, c1.getCitizenId());
		assertEquals(2, dprs.size());
		
		c3.removeRole(Role.PATIENT);
		DatabaseUtils.updateCitizen(conn, c3);
		c2.removeRole(Role.DOCTOR);
		DatabaseUtils.updateCitizen(conn, c2);
		
		dprs = DatabaseUtils.getDocPatRelationsByAdminId(conn, c1.getCitizenId());
		assertTrue(dprs.isEmpty());
		
		assertEquals(1, DatabaseUtils.getAllDoctorsWithoutInstitution(conn).size());
	}
	
	@Test
	public void verifyQuerySintax() throws SQLException {
		DatabaseUtils.getAllInstitutions(conn);
		DatabaseUtils.updateInstitution(conn, new Institution(1, "santa maria", "blabla", "bleble", "super"));
		DatabaseUtils.getMedicalRecordsByPatientCitizenId(conn, "test_id");
		DatabaseUtils.addCitizen(conn, c1);
		c1.addRole(Role.PATIENT);
		c1.addRole(Role.DOCTOR);
		c1.addRole(Role.ADMIN);
		c1.addRole(Role.SUPERUSER);
		DatabaseUtils.updateCitizen(conn, c1);
		DatabaseUtils.setAdminInstitutionId(conn, c1.getCitizenId(), 1);
		DatabaseUtils.setDoctorInstitutionId(conn, c1.getCitizenId(), 1, c1.getCitizenId());
		MedicalRecord mr = new MedicalRecord(0, 1, -1, -1, -1, Timestamp.valueOf("1922-11-1 22:22:22"),
				c1.getCitizenId(), "heh", c1.getCitizenId(), 1, "hah", "hoh");
		DatabaseUtils.addMedicalRecord(conn, mr);
		mr.setHaemoglobin(10);
		DatabaseUtils.updateMedicalRecord(conn, mr);
		DatabaseUtils.getDoctorsByAdminId(conn, c1.getCitizenId());
		DatabaseUtils.removeDoctorFromInstitution(conn, c1.getCitizenId(), c1.getCitizenId());
		DatabaseUtils.getPatientsByDoctorCitizenId(conn, c1.getCitizenId());
		DatabaseUtils.getAllPatients(conn);
		Session session = new Session("sessionP1", c1.getCitizenId(), 
				Timestamp.valueOf("1000-11-11 22:22:22"), Timestamp.valueOf("1000-11-11 22:22:23"));
		DatabaseUtils.addSession(conn, session);
		session.setSessionId("sessionP2");
		DatabaseUtils.updateSession(conn, session);
		DatabaseUtils.getSessionBySessionId(conn, session.getSessionId());
		DatabaseUtils.getSessionsByCitizenId(conn, c1.getCitizenId());
		
	}
}
