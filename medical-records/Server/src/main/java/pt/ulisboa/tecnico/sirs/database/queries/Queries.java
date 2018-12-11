package pt.ulisboa.tecnico.sirs.database.queries;
	

public class Queries {
	
	private Queries(){}
	
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
	
	public static final String GET_ALL_INSTITUTIONS_QUERY = "SELECT *  FROM  institutions";
	
	public static final String ADD_INSTITUTION_QUERY = "INSERT  INTO  institutions(institution_name,institution_address,profile_pic,"
			+ "superuser_citizen_id)  VALUES  (?,?,?,?)";
	
	public static final String UPDATE_INSTITUTION_QUERY = "UPDATE institutions SET institution_name=?,institution_address=?,"
			+ "profile_pic=?,superuser_citizen_id=? WHERE institution_id=?";
	
	public static final String GET_MEDICAL_RECORDS_BY_PATIENT_CITIZEN_ID_QUERY = "SELECT * FROM medical_records "
			+ "WHERE patient_citizen_id=?";
	
	public static final String GET_MEDICAL_RECORDS_BY_ID_QUERY = "SELECT * FROM medical_records "
			+ "WHERE record_id=?";
	
	public static final String ADD_MEDICAL_RECORD_QUERY = "INSERT  INTO  medical_records(heart_beat,blood_pressure,sugar,"
			+ "haemoglobin,creation_date,doctor_citizen_id,treatment,patient_citizen_id,institution_id,general_report,record_signature) "
			+ "VALUES  (?,?,?,?,?,?,?,?,?,?,?)";
	
	public static final String UPDATE_MEDICAL_RECORD_QUERY = "UPDATE medical_records SET heart_beat=?,blood_pressure=?,"
			+ "sugar=?,haemoglobin=?,creation_date=?,doctor_citizen_id=?,treatment=?,patient_citizen_id=?,institution_id=?,general_report=?,"
			+ "record_signature=? WHERE record_id=?";

	public static final String GET_ALL_DOC_PAT_RELATIONS_QUERY = "SELECT * FROM doc_pat_relations";

	public static final String ADD_DOC_PAT_RELATION_QUERY = "INSERT  INTO  doc_pat_relations(begin_date,end_date,doctor_citizen_id,"
			+ "patient_citizen_id,admin_citizen_id) VALUES (?,?,?,?,?)";

	public static final String REMOVE_DOC_PAT_RELATION_QUERY = "DELETE FROM doc_pat_relations WHERE doc_pat_relation_id=?";

	public static final String GET_DOC_PAT_RELATIONS_BY_PATIENT_ID_QUERY = "SELECT * FROM doc_pat_relations WHERE patient_citizen_id=?";

	public static final String GET_DOC_PAT_RELATIONS_BY_DOCTOR_ID_QUERY = "SELECT * FROM doc_pat_relations WHERE doctor_citizen_id=?";

	public static final String GET_DOC_PAT_RELATIONS_BY_INSTITUTION_ID_QUERY = "SELECT * FROM doc_pat_relations WHERE doctor_citizen_id "
			+ "IN (SELECT citizen_id FROM doctors WHERE institution_id=?)";

	public static final String GET_DOCTORS_BY_INSTITUTION_QUERY = "SELECT * FROM doctors WHERE institution_id=?";
	
	public static final String GET_ALL_DOCTORS_WITHOUT_INSTITUTION_QUERY = "SELECT * FROM doctors WHERE institution_id IS NULL";

	public static final String ADD_DOCTOR_QUERY = "INSERT INTO doctors(citizen_id,institution_id,superuser_citizen_id,admin_citizen_id) "
			+ "VALUES (?,?,?,?)";

	public static final String SET_DOCTOR_INSTITUTION_ID_QUERY = "UPDATE doctors SET institution_id=?,admin_citizen_id=? "
			+ "WHERE citizen_id=?";

	public static final String REMOVE_DOCTOR_FROM_INSTITUTION_QUERY = "UPDATE doctors SET institution_id=NULL,admin_citizen_id=? "
			+ "WHERE citizen_id=?";

	public static final String GET_PATIENTS_BY_DOCTOR_ID_QUERY = "SELECT * FROM patients WHERE citizen_id IN "
			+ "(SELECT patient_citizen_id FROM doc_pat_relations WHERE doctor_citizen_id=?)";

	public static final String GET_ALL_PATIENTS_QUERY = "SELECT * FROM patients";

	public static final String SET_ADMIN_INSTITUTION_ID_QUERY = "UPDATE admins SET institution_id=? WHERE citizen_id=?";

	public static final String GET_SESSION_BY_ID_QUERY = "SELECT * FROM sessions WHERE session_id=?";

	public static final String GET_SESSIONS_BY_CITIZEN_ID_QUERY = "SELECT * FROM sessions WHERE citizen_id=?";

	public static final String ADD_SESSION_QUERY = "INSERT INTO sessions VALUES (?,?,?,?)";

	public static final String UPDATE_SESSION_QUERY = "UPDATE sessions SET citizen_id=?,creation_time=?,end_time=? WHERE session_id=?";

	public static final String GET_DOC_PAT_RELATIONS_BY_ADMIN_ID_QUERY = "SELECT * FROM doc_pat_relations WHERE doctor_citizen_id "
			+ "IN (SELECT citizen_id FROM doctors WHERE institution_id IN (SELECT institution_id FROM admins WHERE citizen_id=?))";

	public static final String GET_DOCTORS_BY_ADMIN_ID_QUERY = "SELECT * FROM doctors WHERE institution_id IN "
			+ "(SELECT institution_id FROM admins WHERE citizen_id=?)";

	public static final String GET_INSTITUTION_BY_ID_QUERY = "SELECT * FROM institutions WHERE institution_id=?";
	
	public static final String GET_INSTITUTION_ID_BY_NAME_QUERY = "SELECT institution_id FROM institutions WHERE institution_name=?";

	public static final String GET_INSTITUTION_BY_DOCTOR_ID_QUERY = "SELECT * FROM institutions WHERE institution_id IN "
			+ "(SELECT institution_id FROM doctors WHERE citizen_id=?)";

	public static final String GET_ADMIN_BY_ID_QUERY = "SELECT * FROM admins WHERE citizen_id=?";

	public static final String GET_ADMIN_BY_INSTITUTION_ID_QUERY = "SELECT * FROM admins WHERE institution_id=?";

	public static final String GET_ADMIN_ID_BY_INSTITUTION_ID_QUERY = "SELECT citizen_id FROM admins WHERE institution_id=?";
	
}
