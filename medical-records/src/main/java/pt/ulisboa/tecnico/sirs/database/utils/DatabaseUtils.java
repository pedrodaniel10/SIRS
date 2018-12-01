package pt.ulisboa.tecnico.sirs.database.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import pt.ulisboa.tecnico.sirs.database.constants.Constants;
import pt.ulisboa.tecnico.sirs.dataobjects.Citizen;
import pt.ulisboa.tecnico.sirs.dataobjects.Citizen.Gender;
import pt.ulisboa.tecnico.sirs.dataobjects.Citizen.Role;

public class DatabaseUtils {
	
	private DatabaseUtils(){}
	
	public static ArrayList<Citizen> getAllCitizens(Connection conn) throws SQLException {
		return getCitizens(conn, Constants.GET_ALL_CITIZENS_QUERY, null);
	}
	
	public static Citizen getCitizenById(Connection conn, String citizenId) throws SQLException {
		String getCitizenByIdQuery = Constants.GET_CITIZEN_BY_ID_QUERY;
		ArrayList<Citizen> citizens = getCitizens(conn, getCitizenByIdQuery, citizenId);
		if (citizens.isEmpty()) {
			return null;
		}
		return citizens.get(0);
	}
	
	private static ArrayList<Citizen> getCitizens(Connection conn, String queryStatement, String citizenId) throws SQLException {
		ArrayList<Citizen> citizens = new ArrayList<Citizen>();
		try (PreparedStatement statement = conn.prepareStatement(queryStatement)) {
			if (citizenId != null) {
				statement.setString(1, citizenId);
			}
			
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				Citizen citizen = new Citizen();
				citizen.setCitizenId(rs.getString("citizen_id"));
				citizen.setCitizenName(rs.getString("citizen_name"));
				citizen.setGender(Gender.valueOf(rs.getString("gender")));
				citizen.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
				citizen.setEmail(rs.getString("email"));
				citizen.setPassword(rs.getString("password"));
				citizen.setProfilePic(rs.getString("profile_pic"));
				citizen.setSuperuserCitizenId(rs.getString("superuser_citizen_id"));
				
				Role role;
				if ((role = getRole(conn, citizen, Constants.GET_PATIENT_ROLE_QUERY, Role.PATIENT)) != null) {
					citizen.addRole(role);
				}
				if ((role = getRole(conn, citizen, Constants.GET_DOCTOR_ROLE_QUERY, Role.DOCTOR)) != null) {
					citizen.addRole(role);
				}
				if ((role = getRole(conn, citizen, Constants.GET_ADMIN_ROLE_QUERY, Role.ADMIN)) != null) {
					citizen.addRole(role);
				}
				if ((role = getRole(conn, citizen, Constants.GET_SUPERUSER_ROLE_QUERY, Role.SUPERUSER)) != null) {
					citizen.addRole(role);
				}
				
				citizens.add(citizen);
			}
		}
		return citizens;
	}

	public static void addCitizen(Connection conn, Citizen citizen) throws SQLException {
		try (PreparedStatement statement = conn.prepareStatement(Constants.ADD_CITIZEN_QUERY)) {
			statement.setString(1, citizen.getCitizenId());
			statement.setString(2, citizen.getCitizenName());
			statement.setString(3, citizen.getGender().toString());
			statement.setString(4, citizen.getDateOfBirth().toString());
			statement.setString(5, citizen.getEmail());
			statement.setString(6, citizen.getPassword());
			statement.setString(7, citizen.getProfilePic());
			statement.setString(8, citizen.getSuperuserCitizenId());
			statement.executeUpdate();
			
			//every citizen is a patient when he's added to the system
			try (PreparedStatement patientRoleStatement = conn.prepareStatement(Constants.ADD_PATIENT_ROLE_QUERY)) {
				patientRoleStatement.setString(1, citizen.getCitizenId());
				patientRoleStatement.executeUpdate();
			}
			
			if (citizen.getRoles().contains(Role.DOCTOR)) {
				addRole(conn, citizen, Constants.ADD_DOCTOR_ROLE_QUERY);
			}

			if (citizen.getRoles().contains(Role.ADMIN)) {
				addRole(conn, citizen, Constants.ADD_ADMIN_ROLE_QUERY);		
			}
			
			if (citizen.getRoles().contains(Role.SUPERUSER)) {
				addRole(conn, citizen, Constants.ADD_SUPERUSER_ROLE_QUERY);		
			}
		}
	}
	
	public static void updateCitizen(Connection conn, Citizen citizen) throws SQLException {
		try (PreparedStatement statement = conn.prepareStatement(Constants.UPDATE_CITIZEN_QUERY)) {
			statement.setString(1, citizen.getCitizenName());
			statement.setString(2, citizen.getGender().toString());
			statement.setString(3, citizen.getDateOfBirth().toString());
			statement.setString(4, citizen.getEmail());
			statement.setString(5, citizen.getPassword());
			statement.setString(6, citizen.getProfilePic());
			statement.setString(7, citizen.getSuperuserCitizenId());
			statement.setString(8, citizen.getCitizenId());
			statement.executeUpdate();
			
			updateRole(conn, citizen, Role.PATIENT, Constants.GET_PATIENT_ROLE_QUERY, 
					Constants.ADD_PATIENT_ROLE_QUERY, Constants.REMOVE_PATIENT_ROLE_QUERY);
			
			updateRole(conn, citizen, Role.DOCTOR, Constants.GET_DOCTOR_ROLE_QUERY, 
					Constants.ADD_DOCTOR_ROLE_QUERY, Constants.REMOVE_DOCTOR_ROLE_QUERY);
			
			updateRole(conn, citizen, Role.ADMIN, Constants.GET_ADMIN_ROLE_QUERY, 
					Constants.ADD_ADMIN_ROLE_QUERY, Constants.REMOVE_ADMIN_ROLE_QUERY);
			
			updateRole(conn, citizen, Role.SUPERUSER, Constants.GET_SUPERUSER_ROLE_QUERY, 
					Constants.ADD_SUPERUSER_ROLE_QUERY, Constants.REMOVE_SUPERUSER_ROLE_QUERY);
		}
	}
	
	private static Role getRole(Connection conn, Citizen citizen, String roleQuery, Role role) throws SQLException {
		try (PreparedStatement roleStatement = conn.prepareStatement(roleQuery)) {
			roleStatement.setString(1, citizen.getCitizenId());
			ResultSet roleRs = roleStatement.executeQuery();
			if (roleRs.next()) {
				return role;
			}
		}
		return null;
	}

	private static void addRole(Connection conn, Citizen citizen, String roleQuery) throws SQLException {
		try (PreparedStatement roleStatement = conn.prepareStatement(roleQuery)) {
			roleStatement.setString(1, citizen.getCitizenId());
			roleStatement.setString(2, citizen.getSuperuserCitizenId());
			roleStatement.executeUpdate();
		}
	}
	
	private static void updateRole(Connection conn, Citizen citizen, Role role, String getRoleQuery, 
			String addRoleQuery, String removeRoleQuery) throws SQLException {
		
		if (citizen.getRoles().contains(role)) {
			if (getRole(conn, citizen, getRoleQuery, role) == null) {
				addRole(conn, citizen, addRoleQuery);
			}
		}
		else {
			if (getRole(conn, citizen, getRoleQuery, role) != null) {
				removeRole(conn, citizen, removeRoleQuery);
			}
		}
	}
	
	private static void removeRole(Connection conn, Citizen citizen, String roleQuery) throws SQLException {
		try (PreparedStatement roleStatement = conn.prepareStatement(roleQuery)) {
			roleStatement.setString(1, citizen.getCitizenId());
			roleStatement.executeUpdate();
		}
	}
	
}
