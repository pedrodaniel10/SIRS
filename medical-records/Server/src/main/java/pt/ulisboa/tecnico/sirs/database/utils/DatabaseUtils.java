package pt.ulisboa.tecnico.sirs.database.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pt.ulisboa.tecnico.sirs.api.dataobjects.*;
import pt.ulisboa.tecnico.sirs.database.queries.Queries;

public class DatabaseUtils {
	
	private DatabaseUtils(){}
	
	public static List<Citizen> getAllCitizens(Connection conn) throws SQLException {
		return getCitizens(conn, Queries.GET_ALL_CITIZENS_QUERY, null);
	}
	
	public static Citizen getCitizenById(Connection conn, String citizenId) throws SQLException {
		List<Citizen> citizens = getCitizens(conn, Queries.GET_CITIZEN_BY_ID_QUERY, citizenId);
		if (citizens.isEmpty()) {
			return null;
		}
		return citizens.get(0);
	}
	
	private static List<Citizen> getCitizens(Connection conn, String queryStatement, String citizenId) throws SQLException {
		List<Citizen> citizens = new ArrayList<>();
		try (PreparedStatement statement = conn.prepareStatement(queryStatement)) {
			if (citizenId != null) {
				statement.setString(1, citizenId);
			}
			
			try (ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					Citizen citizen = new Citizen();
					citizen.setCitizenId(rs.getString("citizen_id"));
					citizen.setCitizenName(rs.getString("citizen_name"));
					citizen.setGender(Citizen.Gender.valueOf(rs.getString("gender")));
					citizen.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
					citizen.setEmail(rs.getString("email"));
					citizen.setPassword(rs.getString("password"));
					citizen.setProfilePic(rs.getString("profile_pic"));
					citizen.setSuperuserCitizenId(rs.getString("superuser_citizen_id"));
					
					Citizen.Role role;
					if ((role = getRole(conn, citizen, Queries.GET_PATIENT_ROLE_QUERY, Citizen.Role.PATIENT)) != null) {
						citizen.addRole(role);
					}
					if ((role = getRole(conn, citizen, Queries.GET_DOCTOR_ROLE_QUERY, Citizen.Role.DOCTOR)) != null) {
						citizen.addRole(role);
					}
					if ((role = getRole(conn, citizen, Queries.GET_ADMIN_ROLE_QUERY, Citizen.Role.ADMIN)) != null) {
						citizen.addRole(role);
					}
					if ((role = getRole(conn, citizen, Queries.GET_SUPERUSER_ROLE_QUERY, Citizen.Role.SUPERUSER)) != null) {
						citizen.addRole(role);
					}
					
					citizens.add(citizen);
				}
			}
		}
		return citizens;
	}

	public static void addCitizen(Connection conn, Citizen citizen) throws SQLException {
		try (PreparedStatement statement = conn.prepareStatement(Queries.ADD_CITIZEN_QUERY)) {
			statement.setString(1, citizen.getCitizenId());
			statement.setString(2, citizen.getCitizenName());
			statement.setString(3, citizen.getGender().toString());
			statement.setString(4, citizen.getDateOfBirth().toString());
			statement.setString(5, citizen.getEmail());
			statement.setString(6, citizen.getPassword());
			statement.setString(7, citizen.getProfilePic());
			statement.setString(8, citizen.getSuperuserCitizenId());
			statement.executeUpdate();
			
			if (citizen.getRoles().contains(Citizen.Role.PATIENT)) {
				addRole(conn, citizen, Citizen.Role.PATIENT, Queries.ADD_PATIENT_ROLE_QUERY);
			}
			
			if (citizen.getRoles().contains(Citizen.Role.DOCTOR)) {
				addRole(conn, citizen, Citizen.Role.DOCTOR, Queries.ADD_DOCTOR_ROLE_QUERY);
			}

			if (citizen.getRoles().contains(Citizen.Role.ADMIN)) {
				addRole(conn, citizen, Citizen.Role.ADMIN, Queries.ADD_ADMIN_ROLE_QUERY);
			}
			
			if (citizen.getRoles().contains(Citizen.Role.SUPERUSER)) {
				addRole(conn, citizen, Citizen.Role.SUPERUSER, Queries.ADD_SUPERUSER_ROLE_QUERY);
			}
		}
	}
	
	public static void updateCitizen(Connection conn, Citizen citizen) throws SQLException {
		try (PreparedStatement statement = conn.prepareStatement(Queries.UPDATE_CITIZEN_QUERY)) {
			statement.setString(1, citizen.getCitizenName());
			statement.setString(2, citizen.getGender().toString());
			statement.setString(3, citizen.getDateOfBirth().toString());
			statement.setString(4, citizen.getEmail());
			statement.setString(5, citizen.getPassword());
			statement.setString(6, citizen.getProfilePic());
			statement.setString(7, citizen.getSuperuserCitizenId());
			statement.setString(8, citizen.getCitizenId());
			statement.executeUpdate();
			
			updateRole(conn, citizen, Citizen.Role.PATIENT, Queries.GET_PATIENT_ROLE_QUERY,
					Queries.ADD_PATIENT_ROLE_QUERY, Queries.REMOVE_PATIENT_ROLE_QUERY);
			
			updateRole(conn, citizen, Citizen.Role.DOCTOR, Queries.GET_DOCTOR_ROLE_QUERY,
					Queries.ADD_DOCTOR_ROLE_QUERY, Queries.REMOVE_DOCTOR_ROLE_QUERY);
			
			updateRole(conn, citizen, Citizen.Role.ADMIN, Queries.GET_ADMIN_ROLE_QUERY,
					Queries.ADD_ADMIN_ROLE_QUERY, Queries.REMOVE_ADMIN_ROLE_QUERY);
			
			updateRole(conn, citizen, Citizen.Role.SUPERUSER, Queries.GET_SUPERUSER_ROLE_QUERY,
					Queries.ADD_SUPERUSER_ROLE_QUERY, Queries.REMOVE_SUPERUSER_ROLE_QUERY);
		}
	}
	
	private static Citizen.Role getRole(Connection conn, Citizen citizen, String roleQuery, Citizen.Role role) throws SQLException {
		try (PreparedStatement roleStatement = conn.prepareStatement(roleQuery)) {
			roleStatement.setString(1, citizen.getCitizenId());
			try (ResultSet roleRs = roleStatement.executeQuery()) {
				if (roleRs.next()) {
					return role;
				}
			}
		}
		return null;
	}

	private static void addRole(Connection conn, Citizen citizen, Citizen.Role role, String roleQuery) throws SQLException {
		try (PreparedStatement roleStatement = conn.prepareStatement(roleQuery)) {
			
			if (role.equals(Citizen.Role.PATIENT)) {
				roleStatement.setString(1, citizen.getCitizenId());
			}
			else {
				roleStatement.setString(1, citizen.getCitizenId());
				roleStatement.setString(2, citizen.getSuperuserCitizenId());
			}
			roleStatement.executeUpdate();
		}
	}
	
	private static void updateRole(Connection conn, Citizen citizen, Citizen.Role role, String getRoleQuery,
								   String addRoleQuery, String removeRoleQuery) throws SQLException {
		
		if (citizen.getRoles().contains(role)) {
			if (getRole(conn, citizen, getRoleQuery, role) == null) {
				addRole(conn, citizen, role, addRoleQuery);
			}
		
		} else if (getRole(conn, citizen, getRoleQuery, role) != null) {
				removeRole(conn, citizen, role, removeRoleQuery);
		}
	}
	
	private static void removeRole(Connection conn, Citizen citizen, Citizen.Role role, String roleQuery) throws SQLException {
		try (PreparedStatement roleStatement = conn.prepareStatement(roleQuery)) {
			roleStatement.setString(1, citizen.getCitizenId());
			roleStatement.executeUpdate();
		}
		
		if (role.equals(Citizen.Role.PATIENT)) {
			List<DocPatRelation> docPatRelations = getDocPatRelationsByPatientId(conn, citizen.getCitizenId());
			removeDocPatRelations(conn, docPatRelations);
		
		} else if (role.equals(Citizen.Role.DOCTOR)) {
			List<DocPatRelation> docPatRelations = getDocPatRelationsByDoctorId(conn, citizen.getCitizenId());
			removeDocPatRelations(conn, docPatRelations);
		}
	}
	
	public static Institution getInstitutionById(Connection conn, int institutionId) throws SQLException {
		Institution institution = new Institution();
		try (PreparedStatement statement = conn.prepareStatement(Queries.GET_INSTITUTION_BY_ID_QUERY)) {
			statement.setInt(1, institutionId);
			try (ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					institution.setInstitutionId(rs.getInt("institution_id"));
					institution.setInstitutionName(rs.getString("institution_name"));
					institution.setInstitutionAddress(rs.getString("institution_address"));
					institution.setProfilePic(rs.getString("profile_pic"));
					institution.setSuperuserCitizenId(rs.getString("superuser_citizen_id"));
				}
			}
		}
		return institution;
	}
	
	public static List<Institution> getAllInstitutions(Connection conn) throws SQLException {
		List<Institution> institutions = new ArrayList<>();
		try (PreparedStatement statement = conn.prepareStatement(Queries.GET_ALL_INSTITUTIONS_QUERY)) {
			try (ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					Institution institution = new Institution();
					institution.setInstitutionId(rs.getInt("institution_id"));
					institution.setInstitutionName(rs.getString("institution_name"));
					institution.setInstitutionAddress(rs.getString("institution_address"));
					institution.setProfilePic(rs.getString("profile_pic"));
					institution.setSuperuserCitizenId(rs.getString("superuser_citizen_id"));
					
					institutions.add(institution);
				}
			}
		}
		return institutions;
	}
	
	public static void addInstitution(Connection conn, Institution institution) throws SQLException {
		setInstitution(conn, institution, Queries.ADD_INSTITUTION_QUERY);
	}
	
	public static void updateInstitution(Connection conn, Institution institution) throws SQLException {
		setInstitution(conn, institution, Queries.UPDATE_INSTITUTION_QUERY);
	}
	
	private static void setInstitution(Connection conn, Institution institution, String query) throws SQLException {
		try (PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setString(1, institution.getInstitutionName());
			statement.setString(2, institution.getInstitutionAddress());
			statement.setString(3, institution.getProfilePic());
			statement.setString(4, institution.getSuperuserCitizenId());
			try {
				statement.setInt(5, institution.getInstitutionId());
			} catch (SQLException e) {
				//do nothing (this means it's an add operation)
			}
			
			statement.executeUpdate();
		}
	}
	
	public static List<MedicalRecord> getMedicalRecordsByPatientCitizenId(Connection conn, String citizenId) 
			throws SQLException {
		List<MedicalRecord> medicalRecords = new ArrayList<>();
		try (PreparedStatement statement = conn.prepareStatement(
				Queries.GET_MEDICAL_RECORDS_BY_PATIENT_CITIZEN_ID_QUERY)) {
			statement.setString(1, citizenId);
			try (ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					MedicalRecord medicalRecord = new MedicalRecord();
					medicalRecord.setRecordId(rs.getInt("record_id"));
					medicalRecord.getReportInfo().setHeartBeat(rs.getInt("heart_beat"));
					medicalRecord.getReportInfo().setBloodPressure(rs.getInt("blood_pressure"));
					medicalRecord.getReportInfo().setSugar(rs.getInt("sugar"));
					medicalRecord.getReportInfo().setHaemoglobin(rs.getInt("haemoglobin"));
					medicalRecord.setCreationDate(rs.getTimestamp("creation_date"));
					medicalRecord.setDoctorCitizenId(rs.getString("doctor_citizen_id"));
					medicalRecord.getReportInfo().setTreatment(rs.getString("treatment"));
					medicalRecord.setPatientCitizenId(rs.getString("patient_citizen_id"));
					medicalRecord.setInstitutionId(rs.getInt("institution_id"));
					medicalRecord.getReportInfo().setGeneralReport(rs.getString("general_report"));
					medicalRecord.setRecordSignature(rs.getString("record_signature"));
					
					medicalRecords.add(medicalRecord);
				}
			}
		}
		return medicalRecords;
	}
	
	public static void addMedicalRecord(Connection conn, MedicalRecord medicalRecord) throws SQLException {
		setMedicalRecord(conn, medicalRecord, Queries.ADD_MEDICAL_RECORD_QUERY);
	}
	
	public static void updateMedicalRecord(Connection conn, MedicalRecord medicalRecord) throws SQLException {
		setMedicalRecord(conn, medicalRecord, Queries.UPDATE_MEDICAL_RECORD_QUERY);
	}
	
	private static void setMedicalRecord(Connection conn, MedicalRecord medicalRecord, String query) throws SQLException {
		try (PreparedStatement statement = conn.prepareStatement(query)) {
			
			setRecordData(statement, medicalRecord.getReportInfo().getHeartBeat(), 1);
			setRecordData(statement, medicalRecord.getReportInfo().getBloodPressure(), 2);
			setRecordData(statement, medicalRecord.getReportInfo().getSugar(), 3);
			setRecordData(statement, medicalRecord.getReportInfo().getHaemoglobin(), 4);
			statement.setString(5, medicalRecord.getDoctorCitizenId());
			statement.setString(6, medicalRecord.getReportInfo().getTreatment());
			statement.setString(7, medicalRecord.getPatientCitizenId());
			statement.setInt(8, medicalRecord.getInstitutionId());
			statement.setString(9, medicalRecord.getReportInfo().getGeneralReport());
			statement.setString(10, medicalRecord.getRecordSignature());
			try {
				statement.setInt(11, medicalRecord.getRecordId());
			} catch (SQLException e) {
				//do nothing (this means it's an add operation)
			}
			
			statement.executeUpdate();
		}
	}

	private static void setRecordData(PreparedStatement statement, int recordData, int index) throws SQLException {
		if (recordData <= 200 && recordData > 0) {
			statement.setInt(index, recordData);
		} else {
			statement.setInt(index, 0);
		}
	}
	
	public static List<DocPatRelation> getDocPatRelationsByAdminId(Connection conn, String adminId) throws SQLException {
		return getDocPatRelations(conn, Queries.GET_DOC_PAT_RELATIONS_BY_ADMIN_ID_QUERY, adminId);
	}
	
	public static List<DocPatRelation> getDocPatRelationsByPatientId(Connection conn, String patientId) throws SQLException {
		return getDocPatRelations(conn, Queries.GET_DOC_PAT_RELATIONS_BY_PATIENT_ID_QUERY, patientId);
	}
	
	public static List<DocPatRelation> getDocPatRelationsByDoctorId(Connection conn, String doctorId) throws SQLException {
		return getDocPatRelations(conn, Queries.GET_DOC_PAT_RELATIONS_BY_DOCTOR_ID_QUERY, doctorId);
	}
	
	private static List<DocPatRelation> getDocPatRelations(Connection conn, String query, String citizenId) throws SQLException {
		List<DocPatRelation> docPatRelations = new ArrayList<>();
		try (PreparedStatement statement = conn.prepareStatement(query)) {
			if (citizenId != null) {
				statement.setString(1, citizenId);
			}
			try (ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					DocPatRelation docPatRelation = new DocPatRelation();
					docPatRelation.setDocPatRelationId(rs.getInt("doc_pat_relation_id"));
					docPatRelation.setBeginDate(rs.getDate("begin_date"));
					docPatRelation.setEndDate(rs.getDate("end_date"));
					docPatRelation.setDoctorCitizenId(rs.getString("doctor_citizen_id"));
					docPatRelation.setPatientCitizenId(rs.getString("patient_citizen_id"));
					docPatRelation.setAdminCitizenId(rs.getString("admin_citizen_id"));
					
					docPatRelations.add(docPatRelation);
				}
			}
		}
		return docPatRelations;
	}
	
	public static void addDocPatRelation(Connection conn, DocPatRelation docPatRelation) throws SQLException {
		try (PreparedStatement statement = conn.prepareStatement(Queries.ADD_DOC_PAT_RELATION_QUERY)) {
			statement.setDate(1, docPatRelation.getBeginDate());
			statement.setDate(2, docPatRelation.getEndDate());
			statement.setString(3, docPatRelation.getDoctorCitizenId());
			statement.setString(4, docPatRelation.getPatientCitizenId());
			statement.setString(5, docPatRelation.getAdminCitizenId());
			
			statement.executeUpdate();
		}
	}
	
	public static void removeDocPatRelation(Connection conn, int docPatRelationId) throws SQLException {
		try (PreparedStatement statement = conn.prepareStatement(Queries.REMOVE_DOC_PAT_RELATION_QUERY)) {
			statement.setInt(1, docPatRelationId);
			statement.executeUpdate();
		}
	}
	
	private static void removeDocPatRelations(Connection conn, List<DocPatRelation> docPatRelations)
			throws SQLException {
		for (DocPatRelation docPatRelation : docPatRelations) {
			removeDocPatRelation(conn, docPatRelation.getDocPatRelationId());
		}
	}

	public static List<Doctor> getDoctorsByAdminId(Connection conn, String adminId) throws SQLException {
		return getDoctors(conn, Queries.GET_DOCTORS_BY_ADMIN_ID_QUERY, adminId);
	}
	
	public static List<Doctor> getAllDoctorsWithoutInstitution(Connection conn) throws SQLException {
		return getDoctors(conn, Queries.GET_ALL_DOCTORS_WITHOUT_INSTITUTION_QUERY, null);
	}
	
	private static List<Doctor> getDoctors(Connection conn, String query, String adminId) throws SQLException {
		List<Doctor> doctors = new ArrayList<>();
		try (PreparedStatement statement = conn.prepareStatement(query)) {
			if (adminId != null) {
				statement.setString(1, adminId);
			}
			try (ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					Doctor doctor = new Doctor();
					doctor.setDoctorId(rs.getInt("doctor_id"));
					doctor.setCitizenId(rs.getString("citizen_id"));
					doctor.setInstitutionId(rs.getInt("institution_id"));
					doctor.setSuperuserCitizenId(rs.getString("superuser_citizen_id"));
					doctor.setAdminCitizenId(rs.getString("admin_citizen_id"));
					
					doctors.add(doctor);
				}
			}
		}
		return doctors;
	}
	
	public static void setDoctorInstitutionId(Connection conn, String doctorCitizenId, int institutionId, String adminCitizenId) 
			throws SQLException {
		try (PreparedStatement statement = conn.prepareStatement(Queries.SET_DOCTOR_INSTITUTION_ID_QUERY)) {
			statement.setInt(1, institutionId);
			statement.setString(2, adminCitizenId);
			statement.setString(3, doctorCitizenId);
			
			statement.executeUpdate();
		}
	}
	
	public static void removeDoctorFromInstitution(Connection conn, String doctorCitizenId, String adminCitizenId) throws SQLException {
		try (PreparedStatement statement = conn.prepareStatement(Queries.REMOVE_DOCTOR_FROM_INSTITUTION_QUERY)) {
			statement.setString(1, adminCitizenId);
			statement.setString(2, doctorCitizenId);
			
			statement.executeUpdate();
		}
		List<DocPatRelation> docPatRelations = getDocPatRelationsByDoctorId(conn, doctorCitizenId);
		removeDocPatRelations(conn, docPatRelations);
	}
	
	public static List<Patient> getPatientsByDoctorCitizenId(Connection conn, String doctorCitizenId) throws SQLException {
		return getPatients(conn, Queries.GET_PATIENTS_BY_DOCTOR_ID_QUERY, doctorCitizenId);
	}
	
	public static List<Patient> getAllPatients(Connection conn) throws SQLException {
		return getPatients(conn, Queries.GET_ALL_PATIENTS_QUERY, null);
	}
	
	private static List<Patient> getPatients(Connection conn, String query, String doctorCitizenId) throws SQLException {
		List<Patient> patients = new ArrayList<>();
		try (PreparedStatement statement = conn.prepareStatement(query)) {
			if (doctorCitizenId != null) {
				statement.setString(1, doctorCitizenId);
			}
			try (ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					Patient patient = new Patient();
					patient.setPatientId(rs.getInt("patient_id"));
					patient.setCitizenId(rs.getString("citizen_id"));
					
					patients.add(patient);
				}
			}
		}
		return patients;
	}
	
	public static void setAdminInstitutionId(Connection conn, String adminCitizenId, int institutionId) 
			throws SQLException {
		try (PreparedStatement statement = conn.prepareStatement(Queries.SET_ADMIN_INSTITUTION_ID_QUERY)) {
			statement.setInt(1, institutionId);
			statement.setString(2, adminCitizenId);
			
			statement.executeUpdate();
		}
	}
	
	public static Session getSessionBySessionId(Connection conn, String sessionId) throws SQLException {
		List<Session> sessions = getSessions(conn, Queries.GET_SESSION_BY_ID_QUERY, sessionId);
		if (sessions.isEmpty()) {
			return null;
		}
		return sessions.get(0);
	}
	
	public static List<Session> getSessionsByCitizenId(Connection conn, String citizenId) throws SQLException {
		return getSessions(conn, Queries.GET_SESSIONS_BY_CITIZEN_ID_QUERY, citizenId);
	}
	
	private static List<Session> getSessions(Connection conn, String query, String id) throws SQLException {
		List<Session> sessions = new ArrayList<>();
		try (PreparedStatement statement = conn.prepareStatement(query)) {
			statement.setString(1, id);
			try (ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					Session session = new Session();
					session.setSessionId(rs.getString("session_id"));
					session.setCitizenId(rs.getString("citizen_id"));
					session.setCreationTime(rs.getTimestamp("creation_time"));
					session.setEndTime(rs.getTimestamp("end_time"));
					
					sessions.add(session);
				}
			}
		}
		return sessions;
	}
	
	public static void addSession(Connection conn, Session session) throws SQLException {
		try (PreparedStatement statement = conn.prepareStatement(Queries.ADD_SESSION_QUERY)) {
			statement.setString(1, session.getSessionId());
			statement.setString(2, session.getCitizenId());
			statement.setTimestamp(3, session.getCreationTime());
			statement.setTimestamp(4, session.getEndTime());
			
			statement.executeUpdate();
		}
	}
	
	public static void updateSession(Connection conn, Session session) throws SQLException {
		try (PreparedStatement statement = conn.prepareStatement(Queries.UPDATE_SESSION_QUERY)) {
			statement.setString(1, session.getCitizenId());
			statement.setTimestamp(2, session.getCreationTime());
			statement.setTimestamp(3, session.getEndTime());
			statement.setString(4, session.getSessionId());
			
			statement.executeUpdate();
		}
	}
	
}
