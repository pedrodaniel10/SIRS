package pt.ulisboa.tecnico.sirs.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.bouncycastle.operator.OperatorCreationException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import pt.ulisboa.tecnico.sirs.api.dataobjects.*;
import pt.ulisboa.tecnico.sirs.api.utils.KeyUtils;
import pt.ulisboa.tecnico.sirs.database.exceptions.DatabaseConnectionException;
import pt.ulisboa.tecnico.sirs.database.utils.DatabaseUtils;

public class DatabaseCitizenOperationsTest {
	
	private Connection conn;
	private Citizen c1;
	
	@Before
	public void setup() throws DatabaseConnectionException, IOException, SQLException, NoSuchAlgorithmException {
		DatabaseConnector dbConnector = new DatabaseConnector();
		dbConnector.setupTables();
		this.conn = dbConnector.getConnection();
		this.c1 = new Citizen("test_id", "paulo", Citizen.Gender.MALE, LocalDate.of(2000, 1, 1), "paulo1@paulos.pt",
				"jálhedigo", "path", "super", new ArrayList<>());
		Citizen superUser = new Citizen("super","super",Citizen.Gender.MALE, LocalDate.of(2000, 1, 1),"superguy","aaaa","agaga",null, new ArrayList<>());
		superUser.addRole(Citizen.Role.PATIENT);
		superUser.addRole(Citizen.Role.SUPERUSER);
		DatabaseUtils.addCitizen(conn, superUser);

		Institution institution = new Institution(0, "santa maria","blabla","bleble","super");
		DatabaseUtils.addInstitution(conn, institution);
	}

	@Test
	public void noRoles() throws DatabaseConnectionException, SQLException {
		DatabaseUtils.addCitizen(this.conn, this.c1);
		Citizen result = DatabaseUtils.getCitizenById(this.conn, "test_id");
		assertEquals(result.getCitizenId(), this.c1.getCitizenId());
	}
	
	@Test
	public void allRoles() throws DatabaseConnectionException, SQLException {
		this.c1.addRole(Citizen.Role.DOCTOR);
		this.c1.addRole(Citizen.Role.ADMIN);
		this.c1.addRole(Citizen.Role.SUPERUSER);
		DatabaseUtils.addCitizen(this.conn, this.c1);
		Citizen result = DatabaseUtils.getCitizenById(this.conn, "test_id");
		assertEquals(result.getCitizenId(), this.c1.getCitizenId());
	}
	
	@Test
	public void updateCitizen() throws SQLException {
		this.c1.addRole(Citizen.Role.DOCTOR);
		DatabaseUtils.addCitizen(conn, c1);
		
		this.c1.setCitizenName("polo");
		this.c1.addRole(Citizen.Role.ADMIN);
		this.c1.removeRole(Citizen.Role.DOCTOR);
		DatabaseUtils.updateCitizen(conn, c1);
	}
	
	@Test
	public void updateCitizenWithRelations() throws SQLException, NoSuchAlgorithmException {
		Citizen c2 = new Citizen("test_id2", "paulo", Citizen.Gender.MALE, LocalDate.of(2000, 1, 1), "paulo2@paulos.pt",
				"jálhedigo", "path", "super", new ArrayList<>());
		Citizen c3 = new Citizen("test_id3", "paulo", Citizen.Gender.MALE, LocalDate.of(2000, 1, 1), "paulo3@paulos.pt",
				"jálhedigo", "path", "super", new ArrayList<>());
		
		c1.addRole(Citizen.Role.PATIENT);
		c1.addRole(Citizen.Role.DOCTOR);
		c1.addRole(Citizen.Role.ADMIN);
		c2.addRole(Citizen.Role.PATIENT);
		c2.addRole(Citizen.Role.DOCTOR);
		c3.addRole(Citizen.Role.PATIENT);
		c3.addRole(Citizen.Role.DOCTOR);
		
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
		
		List<DocPatRelation> dprs = DatabaseUtils.getDocPatRelationsByAdminId(conn, c1.getCitizenId());
		assertEquals(2, dprs.size());
		
		c3.removeRole(Citizen.Role.PATIENT);
		DatabaseUtils.updateCitizen(conn, c3);
		c2.removeRole(Citizen.Role.DOCTOR);
		DatabaseUtils.updateCitizen(conn, c2);
		
		dprs = DatabaseUtils.getDocPatRelationsByAdminId(conn, c1.getCitizenId());
		assertTrue(dprs.isEmpty());
		
		assertEquals(1, DatabaseUtils.getAllDoctorsWithoutInstitution(conn).size());
	}
	
	@Test
	public void verifyQuerySintax() throws SQLException, InvalidKeyException, NoSuchAlgorithmException, 
	SignatureException, KeyStoreException, CertificateException, UnrecoverableEntryException, IOException, 
	OperatorCreationException, InterruptedException {
		
		DatabaseUtils.getAllInstitutions(conn);
		DatabaseUtils.updateInstitution(conn, new Institution(1, "santa maria", "blabla", "bleble", "super"));
		DatabaseUtils.getMedicalRecordsByPatientCitizenId(conn, "test_id");
		DatabaseUtils.addCitizen(conn, c1);
		c1.addRole(Citizen.Role.PATIENT);
		c1.addRole(Citizen.Role.DOCTOR);
		c1.addRole(Citizen.Role.ADMIN);
		c1.addRole(Citizen.Role.SUPERUSER);
		DatabaseUtils.updateCitizen(conn, c1);
		DatabaseUtils.setAdminInstitutionId(conn, c1.getCitizenId(), 1);
		DatabaseUtils.setDoctorInstitutionId(conn, c1.getCitizenId(), 1, c1.getCitizenId());
		ReportInfo ri = new ReportInfo(-1,-1,-1,-1,"heh","hah");
		MedicalRecord mr = new MedicalRecord(0,
				c1.getCitizenId(), c1.getCitizenId(), 1, ri);

		DatabaseUtils.addMedicalRecord(conn, mr);
		SignedMedicalRecord record = DatabaseUtils.getMedicalRecordsByPatientCitizenId(conn, c1.getCitizenId()).get(0);
		List<SignedMedicalRecord> records = DatabaseUtils.getMedicalRecordsByPatientCitizenId(conn, record.getMedicalRecord().getPatientCitizenId());
		assertTrue(records.get(0).isVerified());
		
		mr.getReportInfo().setHaemoglobin(10);
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
		DatabaseUtils.getInstitutionById(conn, 1);
		DatabaseUtils.getInstitutionByDoctorId(conn, c1.getCitizenId());
		DatabaseUtils.getAdminByCitizenId(conn, c1.getCitizenId());
		DatabaseUtils.getMedicalRecordById(conn, DatabaseUtils.getMedicalRecordsByPatientCitizenId(conn, 
				mr.getPatientCitizenId()).get(0).getMedicalRecord().getRecordId());
	}
	
	@Test
	public void keyUtils() throws KeyStoreException, NoSuchAlgorithmException, CertificateException,
			IOException, UnrecoverableEntryException, InterruptedException {
		KeyUtils.createKeyPair("hola");
		KeyUtils.getKeyPair("hola");
	}

	@After
	public void clear() throws DatabaseConnectionException, IOException, SQLException, NoSuchAlgorithmException {
		DatabaseConnector dbConnector = new DatabaseConnector();
		dbConnector.setupTables();
	}
	
}
