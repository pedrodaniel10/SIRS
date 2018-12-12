package pt.ulisboa.tecnico.sirs.database.utils;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.maven.shared.utils.cli.javatool.JavaToolException;
import org.bouncycastle.operator.OperatorCreationException;

import pt.ulisboa.tecnico.sirs.database.queries.Queries;
import pt.ulisboa.tecnico.sirs.api.dataobjects.*;
import pt.ulisboa.tecnico.sirs.api.dataobjects.Citizen.Gender;
import pt.ulisboa.tecnico.sirs.api.dataobjects.Citizen.Role;
import pt.ulisboa.tecnico.sirs.api.utils.KeyUtils;

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
    
    public static Citizen getCitizenByEmail(Connection conn, String email) throws SQLException {
        List<Citizen> citizens = getCitizens(conn, Queries.GET_CITIZEN_BY_EMAIL_QUERY, email);
        if (citizens.isEmpty()) {
            return null;
        }
        return citizens.get(0);
    }

    private static List<Citizen> getCitizens(Connection conn, String queryStatement, String column) throws SQLException {
        List<Citizen> citizens = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement(queryStatement)) {
            if (column != null) {
                statement.setString(1, column);
            }

            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Citizen citizen = new Citizen();
                    citizen.setCitizenId(rs.getString("citizen_id"));
                    citizen.setCitizenName(rs.getString("citizen_name"));
                    citizen.setGender(Gender.valueOf(rs.getString("gender")));
                    citizen.setDateOfBirth(rs.getDate("date_of_birth").toLocalDate());
                    citizen.setEmail(rs.getString("email"));
                    citizen.setPassword(rs.getBytes("password"));
                    citizen.setProfilePic(rs.getString("profile_pic"));
                    citizen.setSuperuserCitizenId(rs.getString("superuser_citizen_id"));

                    Role role;
                    if ((role = getRole(conn, citizen, Queries.GET_PATIENT_ROLE_QUERY, Role.PATIENT)) != null) {
                        citizen.addRole(role);
                    }
                    if ((role = getRole(conn, citizen, Queries.GET_DOCTOR_ROLE_QUERY, Role.DOCTOR)) != null) {
                        citizen.addRole(role);
                    }
                    if ((role = getRole(conn, citizen, Queries.GET_ADMIN_ROLE_QUERY, Role.ADMIN)) != null) {
                        citizen.addRole(role);
                    }
                    if ((role = getRole(conn, citizen, Queries.GET_SUPERUSER_ROLE_QUERY, Role.SUPERUSER)) != null) {
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
            statement.setBytes(6, citizen.getPassword());
            statement.setString(7, citizen.getProfilePic());
            statement.setString(8, citizen.getSuperuserCitizenId());
            statement.executeUpdate();

            if (citizen.getRoles().contains(Role.PATIENT)) {
                addRole(conn, citizen, Role.PATIENT, Queries.ADD_PATIENT_ROLE_QUERY);
            }

            if (citizen.getRoles().contains(Role.DOCTOR)) {
                addRole(conn, citizen, Role.DOCTOR, Queries.ADD_DOCTOR_ROLE_QUERY);
            }

            if (citizen.getRoles().contains(Role.ADMIN)) {
                addRole(conn, citizen, Role.ADMIN, Queries.ADD_ADMIN_ROLE_QUERY);
            }

            if (citizen.getRoles().contains(Role.SUPERUSER)) {
                addRole(conn, citizen, Role.SUPERUSER, Queries.ADD_SUPERUSER_ROLE_QUERY);
            }
        }
    }

    public static void updateCitizen(Connection conn, Citizen citizen) throws SQLException {
        try (PreparedStatement statement = conn.prepareStatement(Queries.UPDATE_CITIZEN_QUERY)) {
            statement.setString(1, citizen.getCitizenName());
            statement.setString(2, citizen.getGender().toString());
            statement.setString(3, citizen.getDateOfBirth().toString());
            statement.setString(4, citizen.getEmail());
            statement.setBytes(5, citizen.getPassword());
            statement.setString(6, citizen.getProfilePic());
            statement.setString(7, citizen.getSuperuserCitizenId());
            statement.setString(8, citizen.getCitizenId());
            statement.executeUpdate();

            updateRole(conn, citizen, Role.PATIENT, Queries.GET_PATIENT_ROLE_QUERY,
                    Queries.ADD_PATIENT_ROLE_QUERY, Queries.REMOVE_PATIENT_ROLE_QUERY);

            updateRole(conn, citizen, Role.DOCTOR, Queries.GET_DOCTOR_ROLE_QUERY,
                    Queries.ADD_DOCTOR_ROLE_QUERY, Queries.REMOVE_DOCTOR_ROLE_QUERY);

            updateRole(conn, citizen, Role.ADMIN, Queries.GET_ADMIN_ROLE_QUERY,
                    Queries.ADD_ADMIN_ROLE_QUERY, Queries.REMOVE_ADMIN_ROLE_QUERY);

            updateRole(conn, citizen, Role.SUPERUSER, Queries.GET_SUPERUSER_ROLE_QUERY,
                    Queries.ADD_SUPERUSER_ROLE_QUERY, Queries.REMOVE_SUPERUSER_ROLE_QUERY);
        }
    }

    private static Role getRole(Connection conn, Citizen citizen, String roleQuery, Role role) throws SQLException {
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

    private static void addRole(Connection conn, Citizen citizen, Role role, String roleQuery) throws SQLException {
        try (PreparedStatement roleStatement = conn.prepareStatement(roleQuery)) {

            if (role.equals(Role.PATIENT)) {
                roleStatement.setString(1, citizen.getCitizenId());
            }
            else {
                roleStatement.setString(1, citizen.getCitizenId());
                roleStatement.setString(2, citizen.getSuperuserCitizenId());
            }
            roleStatement.executeUpdate();
        }
    }

    private static void updateRole(Connection conn, Citizen citizen, Role role, String getRoleQuery,
                                   String addRoleQuery, String removeRoleQuery) throws SQLException {

        if (citizen.getRoles().contains(role)) {
            if (getRole(conn, citizen, getRoleQuery, role) == null) {
                addRole(conn, citizen, role, addRoleQuery);
            }

        } else if (getRole(conn, citizen, getRoleQuery, role) != null) {
            removeRole(conn, citizen, role, removeRoleQuery);
        }
    }

    private static void removeRole(Connection conn, Citizen citizen, Role role, String roleQuery) throws SQLException {
        try (PreparedStatement roleStatement = conn.prepareStatement(roleQuery)) {
            roleStatement.setString(1, citizen.getCitizenId());
            roleStatement.executeUpdate();
        }

        if (role.equals(Role.PATIENT)) {
            List<DocPatRelation> docPatRelations = getDocPatRelationsByPatientId(conn, citizen.getCitizenId());
            removeDocPatRelations(conn, docPatRelations);

        } else if (role.equals(Role.DOCTOR)) {
            List<DocPatRelation> docPatRelations = getDocPatRelationsByDoctorId(conn, citizen.getCitizenId());
            removeDocPatRelations(conn, docPatRelations);
        }
    }

    public static Institution getInstitutionById(Connection conn, int institutionId) throws SQLException {
        Institution institution = new Institution();
        try (PreparedStatement statement = conn.prepareStatement(Queries.GET_INSTITUTION_BY_ID_QUERY)) {
            statement.setInt(1, institutionId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    institution.setInstitutionId(rs.getInt("institution_id"));
                    institution.setInstitutionName(rs.getString("institution_name"));
                    institution.setInstitutionAddress(rs.getString("institution_address"));
                    institution.setProfilePic(rs.getString("profile_pic"));
                    institution.setSuperuserCitizenId(rs.getString("superuser_citizen_id"));
                }
            }
        }
        institution.setAdminCitizenId(getAdminIdByInstitutionId(conn, institutionId));
        return institution;
    }

    private static String getAdminIdByInstitutionId(Connection conn, int institutionId) throws SQLException {
        String adminCitizenId = null;
        try (PreparedStatement statement = conn.prepareStatement(Queries.GET_ADMIN_ID_BY_INSTITUTION_ID_QUERY)) {
            statement.setInt(1, institutionId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    adminCitizenId = rs.getString("citizen_id");
                }
            }
        }
        return adminCitizenId;
    }

    public static Institution getInstitutionByDoctorId(Connection conn, String doctorCitizenId) throws SQLException {
        List<Institution> institutions = getInstitutions(conn, Queries.GET_INSTITUTION_BY_DOCTOR_ID_QUERY, doctorCitizenId);
        if (institutions.isEmpty()) {
            return null;
        }
        return institutions.get(0);
    }

    public static List<Institution> getAllInstitutions(Connection conn) throws SQLException {
        return getInstitutions(conn, Queries.GET_ALL_INSTITUTIONS_QUERY, null);
    }

    private static List<Institution> getInstitutions(Connection conn, String query, String id) throws SQLException {
        List<Institution> institutions = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            if (id != null) {
                statement.setString(1, id);
            }
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
        for (Institution institution : institutions) {
            institution.setAdminCitizenId(getAdminIdByInstitutionId(conn, institution.getInstitutionId()));
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

        setAdminInstitutionId(conn, institution.getAdminCitizenId(),
                DatabaseUtils.getInstitutionIdByName(conn, institution.getInstitutionName()));
    }

    private static int getInstitutionIdByName(Connection conn, String institutionName) throws SQLException {
        int institutionId = -1;
        try (PreparedStatement statement = conn.prepareStatement(Queries.GET_INSTITUTION_ID_BY_NAME_QUERY)) {
            statement.setString(1, institutionName);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    institutionId = rs.getInt("institution_id");
                }
            }
        }
        return institutionId;
    }

    public static SignedMedicalRecord getMedicalRecordById(Connection conn, int recordId) throws SQLException,
            InvalidKeyException, SignatureException, NoSuchAlgorithmException, KeyStoreException, CertificateException,
            UnrecoverableEntryException, IOException {
        SignedMedicalRecord signedMedicalRecord = new SignedMedicalRecord();

        try (PreparedStatement statement = conn.prepareStatement(
                Queries.GET_MEDICAL_RECORDS_BY_ID_QUERY)) {
            statement.setInt(1, recordId);
            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    signedMedicalRecord.getMedicalRecord().setRecordId(rs.getInt("record_id"));
                    signedMedicalRecord.getMedicalRecord().getReportInfo().setHeartBeat(rs.getInt("heart_beat"));
                    signedMedicalRecord.getMedicalRecord().getReportInfo().setBloodPressure(rs.getInt("blood_pressure"));
                    signedMedicalRecord.getMedicalRecord().getReportInfo().setSugar(rs.getInt("sugar"));
                    signedMedicalRecord.getMedicalRecord().getReportInfo().setHaemoglobin(rs.getInt("haemoglobin"));
                    signedMedicalRecord.getMedicalRecord().setCreationDate(rs.getTimestamp("creation_date"));
                    signedMedicalRecord.getMedicalRecord().setDoctorCitizenId(rs.getString("doctor_citizen_id"));
                    signedMedicalRecord.getMedicalRecord().getReportInfo().setTreatment(rs.getString("treatment"));
                    signedMedicalRecord.getMedicalRecord().setPatientCitizenId(rs.getString("patient_citizen_id"));
                    signedMedicalRecord.getMedicalRecord().setInstitutionId(rs.getInt("institution_id"));
                    signedMedicalRecord.getMedicalRecord().getReportInfo().setGeneralReport(rs.getString("general_report"));
                    signedMedicalRecord.setRecordSignature(rs.getBytes("record_signature"));
                    signedMedicalRecord.verifySignature();
                }
            }
        }
        return signedMedicalRecord;
    }

    public static List<SignedMedicalRecord> getMedicalRecordsByPatientCitizenId(Connection conn, String citizenId)
            throws SQLException, InvalidKeyException, SignatureException, NoSuchAlgorithmException,
            KeyStoreException, CertificateException, UnrecoverableEntryException, IOException {
        List<SignedMedicalRecord> signedMedicalRecords = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement(
                Queries.GET_MEDICAL_RECORDS_BY_PATIENT_CITIZEN_ID_QUERY)) {
            statement.setString(1, citizenId);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    SignedMedicalRecord signedMedicalRecord = new SignedMedicalRecord();
                    signedMedicalRecord.getMedicalRecord().setRecordId(rs.getInt("record_id"));
                    signedMedicalRecord.getMedicalRecord().getReportInfo().setHeartBeat(rs.getInt("heart_beat"));
                    signedMedicalRecord.getMedicalRecord().getReportInfo().setBloodPressure(rs.getInt("blood_pressure"));
                    signedMedicalRecord.getMedicalRecord().getReportInfo().setSugar(rs.getInt("sugar"));
                    signedMedicalRecord.getMedicalRecord().getReportInfo().setHaemoglobin(rs.getInt("haemoglobin"));
                    signedMedicalRecord.getMedicalRecord().setCreationDate(rs.getTimestamp("creation_date"));
                    signedMedicalRecord.getMedicalRecord().setDoctorCitizenId(rs.getString("doctor_citizen_id"));
                    signedMedicalRecord.getMedicalRecord().getReportInfo().setTreatment(rs.getString("treatment"));
                    signedMedicalRecord.getMedicalRecord().setPatientCitizenId(rs.getString("patient_citizen_id"));
                    signedMedicalRecord.getMedicalRecord().setInstitutionId(rs.getInt("institution_id"));
                    signedMedicalRecord.getMedicalRecord().getReportInfo().setGeneralReport(rs.getString("general_report"));
                    signedMedicalRecord.setRecordSignature(rs.getBytes("record_signature"));
                    signedMedicalRecord.verifySignature();

                    signedMedicalRecords.add(signedMedicalRecord);
                }
            }
        }
        return signedMedicalRecords;
    }

    public static void addMedicalRecord(Connection conn, MedicalRecord medicalRecord)
            throws SQLException, InvalidKeyException, KeyStoreException, NoSuchAlgorithmException,
            CertificateException, OperatorCreationException, SignatureException, UnrecoverableEntryException,
            IOException, InterruptedException, JavaToolException {

        setMedicalRecord(conn, medicalRecord, Queries.ADD_MEDICAL_RECORD_QUERY);
    }

    public static void updateMedicalRecord(Connection conn, MedicalRecord medicalRecord)
            throws SQLException, InvalidKeyException, KeyStoreException, NoSuchAlgorithmException,
            CertificateException, OperatorCreationException, SignatureException, UnrecoverableEntryException,
            IOException, InterruptedException, JavaToolException {

        setMedicalRecord(conn, medicalRecord, Queries.UPDATE_MEDICAL_RECORD_QUERY);
    }

    private static void setMedicalRecord(Connection conn, MedicalRecord medicalRecord, String query)
            throws SQLException, KeyStoreException, NoSuchAlgorithmException, CertificateException,
            OperatorCreationException, IOException, InvalidKeyException, SignatureException,
            UnrecoverableEntryException, InterruptedException, JavaToolException {

        KeyUtils.createKeyPair(medicalRecord.getDoctorCitizenId());
        SignedMedicalRecord signedMedicalRecord = medicalRecord.getSignedMedicalRecord();

        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, signedMedicalRecord.getMedicalRecord().getReportInfo().getHeartBeat());
            statement.setInt(2, signedMedicalRecord.getMedicalRecord().getReportInfo().getBloodPressure());
            statement.setInt(3, signedMedicalRecord.getMedicalRecord().getReportInfo().getSugar());
            statement.setInt(4, signedMedicalRecord.getMedicalRecord().getReportInfo().getHaemoglobin());
            statement.setTimestamp(5, signedMedicalRecord.getMedicalRecord().getCreationDate());
            statement.setString(6, signedMedicalRecord.getMedicalRecord().getDoctorCitizenId());
            statement.setString(7, signedMedicalRecord.getMedicalRecord().getReportInfo().getTreatment());
            statement.setString(8, signedMedicalRecord.getMedicalRecord().getPatientCitizenId());
            statement.setInt(9, signedMedicalRecord.getMedicalRecord().getInstitutionId());
            statement.setString(10, signedMedicalRecord.getMedicalRecord().getReportInfo().getGeneralReport());
            statement.setBytes(11, signedMedicalRecord.getRecordSignature());
            try {
                statement.setInt(12, signedMedicalRecord.getMedicalRecord().getRecordId());
            } catch (SQLException e) {
                //do nothing (this means it's an add operation)
            }

            statement.executeUpdate();
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

        for (DocPatRelation docPatRelation : docPatRelations) {
            docPatRelation.setDoctor(getCitizenById(conn, docPatRelation.getDoctorCitizenId()));
            docPatRelation.setPatient(getCitizenById(conn, docPatRelation.getPatientCitizenId()));
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
        for (Doctor doctor : doctors) {
            doctor.setCitizen(getCitizenById(conn, doctor.getCitizenId()));
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
        for (Patient patient : patients) {
            patient.setCitizen(getCitizenById(conn, patient.getCitizenId()));
        }
        return patients;
    }

    public static Admin getAdminByCitizenId(Connection conn, String adminCitizenId) throws SQLException {
        List<Admin> admins = getAdmins(conn, Queries.GET_ADMIN_BY_ID_QUERY, adminCitizenId);
        if (admins.isEmpty()) {
            return null;
        }
        return admins.get(0);
    }

    private static List<Admin> getAdmins(Connection conn, String query, String id) throws SQLException {
        List<Admin> admins = new ArrayList<>();
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Admin admin = new Admin();
                    admin.setAdminId(rs.getInt("admin_id"));
                    admin.setCitizenId(rs.getString("citizen_id"));
                    admin.setInstitutionId(rs.getInt("institution_id"));
                    admin.setSuperuserCitizenId(rs.getString("superuser_citizen_id"));

                    admins.add(admin);
                }
            }
        }

        for (Admin admin : admins) {
            admin.setCitizen(getCitizenById(conn, admin.getCitizenId()));
        }
        return admins;
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
    
    public static void removeSessionBySessionId(Connection conn, String sessionId) throws SQLException {
    	removeSessions(conn, Queries.REMOVE_SESSION_BY_SESSION_ID_QUERY, sessionId);
    }
    
    public static void removeSessionsByCitizenId(Connection conn, String citizenId) throws SQLException {
    	removeSessions(conn, Queries.REMOVE_SESSIONS_BY_CITIZEN_ID_QUERY, citizenId);
    }
    
    private static void removeSessions(Connection conn, String query, String id) throws SQLException {
    	try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setString(1, id);

            statement.executeUpdate();
        }
    }

}