package pt.ulisboa.tecnico.sirs.database.constants;

public class Constants {
	
	private Constants(){}
	
	/**
	 * Database Queries
	 */
	public static final String GET_ALL_CITIZENS_QUERY = "SELECT *  FROM  citizens";
	public static final String GET_CITIZEN_BY_ID_QUERY = "SELECT * FROM citizens WHERE citizen_id=?";
	public static final String GET_PATIENT_ROLE_QUERY = "SELECT patient_id FROM patients WHERE citizen_id=?";
	public static final String GET_DOCTOR_ROLE_QUERY = "SELECT doctor_id FROM doctors WHERE citizen_id=?";
	public static final String GET_ADMIN_ROLE_QUERY = "SELECT admin_id FROM admins WHERE citizen_id=?";
	public static final String GET_SUPERUSER_ROLE_QUERY = "SELECT superuser_id FROM superusers WHERE citizen_id=?";
	public static final String UPDATE_CITIZEN_QUERY = "UPDATE citizens SET citizen_name=?,gender=?,date_of_birth=?"
			+ ",email=?,password=?,profile_pic=?,superuser_citizen_id=? WHERE citizen_id=?";
	public static final String ADD_CITIZEN_QUERY = "INSERT  INTO  citizens  VALUES  (?,?,?,?,?,?,?,?)";
	public static final String ADD_PATIENT_ROLE_QUERY = "INSERT INTO patients(citizen_id) VALUES (?)";
	public static final String ADD_DOCTOR_ROLE_QUERY = "INSERT INTO doctors(citizen_id,superuser_citizen_id) VALUES (?,?)";
	public static final String ADD_ADMIN_ROLE_QUERY = "INSERT INTO admins(citizen_id,superuser_citizen_id) VALUES (?,?)";
	public static final String ADD_SUPERUSER_ROLE_QUERY = "INSERT INTO superusers(citizen_id,superuser_citizen_id) VALUES (?,?)";
	public static final String REMOVE_DOCTOR_ROLE_QUERY = "DELETE FROM doctors WHERE citizen_id=?";
	public static final String REMOVE_ADMIN_ROLE_QUERY = "DELETE FROM admins WHERE citizen_id=?";
	public static final String REMOVE_SUPERUSER_ROLE_QUERY = "DELETE FROM superusers WHERE citizen_id=?";
	public static final String REMOVE_PATIENT_ROLE_QUERY = "DELETE FROM patients WHERE citizen_id=?";
}
