package pt.ulisboa.tecnico.sirs.server;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import org.apache.log4j.Logger;
import org.bouncycastle.operator.OperatorCreationException;
import pt.ulisboa.tecnico.sirs.api.MedicalRecordsService;
import pt.ulisboa.tecnico.sirs.api.dataobjects.*;
import pt.ulisboa.tecnico.sirs.api.exceptions.AdminException;
import pt.ulisboa.tecnico.sirs.api.exceptions.CitizenException;
import pt.ulisboa.tecnico.sirs.api.exceptions.LoginFailedException;
import pt.ulisboa.tecnico.sirs.database.DatabaseConnector;
import pt.ulisboa.tecnico.sirs.database.exceptions.DatabaseConnectionException;
import pt.ulisboa.tecnico.sirs.database.utils.DatabaseUtils;
import pt.ulisboa.tecnico.sirs.pdp.PolicyEnforcementPoint;

import java.io.IOException;
import java.security.*;
import java.security.cert.CertificateException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordsServiceImpl implements MedicalRecordsService {

    private static Logger log = Logger.getLogger(MedicalRecordsService.class);

    private static final int HOURS_EXPIRE_SESSION = 1;

    private Boolean requestEvaluation(String subjectId, List<String> roles, String action, String resourceName, String resourceId) {
        PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();

        return policyEnforcementPoint.requestEvaluation(subjectId,
                roles, action, resourceName, resourceId);
    }

    @Override
    public Citizen getWelcomePage(Citizen citizen) {
        List<String> roles = new ArrayList<>();

        if (requestEvaluation("", roles, "view", "indexPage", "")) {
            if (citizen.getCitizenId() == null) {
                return null;
            }
            return citizen;
        }

        return null;
    }

    @Override
    public Citizen getLoginPage(Citizen citizen) {
        List<String> roles = new ArrayList<>();

        if (requestEvaluation("", roles, "view", "loginRegisterPage", "")){
            if (citizen.getCitizenId() == null) {
                return null;
            }
            return citizen;
        }

        return null;
    }

    @Override
    public Citizen postLoginPage(String authToken, Login login) throws LoginFailedException {
        List<String> roles = new ArrayList<>();

        if (requestEvaluation("", roles, "edit", "loginRegisterPage", "")){
            try {
                Connection connection = (new DatabaseConnector()).getConnection();

                Citizen citizen = DatabaseUtils.getCitizenByEmail(connection, login.getEmail());

                if (citizen == null) {
                    throw new LoginFailedException("The email doesn't exist.");
                }

                if (!Arrays.equals(citizen.getPassword(), new Citizen(login.getPassword()).getPassword())){
                    throw new LoginFailedException("The password is wrong.");
                }

                Date startSession = new Date();
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(startSession);
                calendar.add(Calendar.HOUR_OF_DAY, HOURS_EXPIRE_SESSION);
                Date endSession = calendar.getTime();

                Timestamp startSessionTimestamp = new Timestamp(startSession.getTime());

                Timestamp endSessionTimestamp = new Timestamp(endSession.getTime());
                Session newSession = new Session(authToken, citizen.getCitizenId(), startSessionTimestamp, endSessionTimestamp);

                DatabaseUtils.addSession(connection, newSession);
                return citizen;
            } catch (DatabaseConnectionException | SQLException e ) {
                log.error(e.getMessage());
            }
        }

        return null;
    }

    @Override
    public void postLogoutPage(String authTokenCookie) {
        try {
            Connection connection = (new DatabaseConnector()).getConnection();

            Session session = DatabaseUtils.getSessionBySessionId(connection, authTokenCookie);

            if (session!= null){
                DatabaseUtils.removeSessionsByCitizenId(connection, session.getCitizenId());
            }
        } catch (DatabaseConnectionException | SQLException e ) {
            log.error(e.getMessage());
        }
    }

    @Override
    public Citizen getSessionCitizen(String authToken) {
        try {
            Connection connection = (new DatabaseConnector()).getConnection();
            Session session = DatabaseUtils.getSessionBySessionId(connection, authToken);

            if (session == null) {
                return new Citizen();
            }

            if (session.getEndTime().getTime() < new Date().getTime()) {
                DatabaseUtils.removeSessionsByCitizenId(connection, session.getCitizenId());
                return new Citizen();
            }

            return DatabaseUtils.getCitizenById(connection, session.getCitizenId());

        } catch (DatabaseConnectionException | SQLException e ) {
            log.error(e.getMessage());
        }
        return new Citizen();
    }



    /* --------------------------------------------------------------------------------------------------------------*/
    /* ------------------------------------------- CITIZENS SERVICES ------------------------------------------------*/
    /* --------------------------------------------------------------------------------------------------------------*/

    @Override
    public Citizen getCitizen(Citizen subject, String citizenId) {
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "view", "citizensPage", citizenId);
        if (authorization) {
            try {
                Connection connection = (new DatabaseConnector()).getConnection();
                return DatabaseUtils.getCitizenById(connection, citizenId);
            } catch (DatabaseConnectionException | SQLException e ) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public List<Citizen> getCitizens(Citizen subject) {
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "view", "citizensPage", "");
        if (authorization) {
            try {
                Connection connection = (new DatabaseConnector()).getConnection();
                return DatabaseUtils.getAllCitizens(connection);
            } catch (DatabaseConnectionException | SQLException e ) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public Boolean getAddCitizensPage(Citizen subject) {
        return requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "create", "citizensPage", "");
    }

    @Override
    public List<Citizen> addCitizen(Citizen subject, Citizen citizenToAdd) throws CitizenException {
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "create", "citizensPage", citizenToAdd.getCitizenId());
        if (authorization) {
            try {
                Connection connection = (new DatabaseConnector()).getConnection();
                DatabaseUtils.addCitizen(connection, citizenToAdd);
                return DatabaseUtils.getAllCitizens(connection);
            } catch (DatabaseConnectionException e) {
                log.error(e.getMessage());
                System.exit(-1);
            } catch (SQLException e) {
                if(e.getErrorCode() == 1062)
                    throw new CitizenException("Citizen Id " + citizenToAdd.getCitizenId() + " is already in use!");
                else
                    throw new CitizenException("Please correct your data");
            }
        }
        return null;
    }

    @Override
    public Citizen getEditCitizensPage(Citizen subject, String citizenToEdit) {
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "edit", "citizensPage", citizenToEdit);
        if (authorization) {
            try {
                Connection connection = (new DatabaseConnector()).getConnection();
                if (citizenToEdit != null)
                    return DatabaseUtils.getCitizenById(connection, citizenToEdit);
            } catch (DatabaseConnectionException | SQLException e ) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public List<Citizen> editCitizen(Citizen subject, String citizenId, Citizen citizenToEdit) {
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "edit", "citizensPage", citizenToEdit.getCitizenId());
        if (authorization) {
            try {
                Connection connection = (new DatabaseConnector()).getConnection();
                citizenToEdit.setCitizenId(citizenId);
                citizenToEdit.addRole(Citizen.Role.PATIENT);
                DatabaseUtils.updateCitizen(connection, citizenToEdit);
                return DatabaseUtils.getAllCitizens(connection);
            } catch (DatabaseConnectionException | SQLException e ) {
                log.error(e.getMessage());
            }
        }
        return null;

    }


    /* --------------------------------------------------------------------------------------------------------------*/
    /* --------------------------------------- INSTITUTIONS SERVICES ------------------------------------------------*/
    /* --------------------------------------------------------------------------------------------------------------*/

    @Override
    public List<Institution> getInstitutions(Citizen subject) {
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "view", "institutionsPage", "");
        if (authorization) {
            try {
                Connection connection = (new DatabaseConnector()).getConnection();
                return DatabaseUtils.getAllInstitutions(connection);
            } catch (DatabaseConnectionException | SQLException e ) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public Boolean getAddInstitutionsPage(Citizen subject) {
        return requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "create", "institutionsPage", "");
    }

    @Override
    public List<Institution> addInstitution(Citizen subject, Institution institutionToAdd) throws AdminException {
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "create", "institutionsPage", Integer.toString(institutionToAdd.getInstitutionId()));
        if (authorization) {
            try {
                Connection connection = (new DatabaseConnector()).getConnection();
                Admin admin = DatabaseUtils.getAdminByCitizenId(connection, institutionToAdd.getAdminCitizenId());
                if (admin == null)
                    throw new AdminException("Citizen ID " + institutionToAdd.getAdminCitizenId() + " is not an admin");
                DatabaseUtils.addInstitution(connection, institutionToAdd);
                return DatabaseUtils.getAllInstitutions(connection);
            } catch (DatabaseConnectionException | SQLException e ) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public Institution getEditInstitutionPage(Citizen subject, int institutionToEdit) {
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "edit", "institutionsPage", Integer.toString(institutionToEdit));
        if (authorization) {
            try {
                Connection connection = (new DatabaseConnector()).getConnection();
                return DatabaseUtils.getInstitutionById(connection, institutionToEdit);
            } catch (DatabaseConnectionException | SQLException e ) {
                e.printStackTrace();
                log.error(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public List<Institution> editInstitution(Citizen subject, int institutionId, Institution institutionToEdit) {
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "edit", "institutionsPage", Integer.toString(institutionToEdit.getInstitutionId()));
        if (authorization) {
            try {
                Connection connection = (new DatabaseConnector()).getConnection();
                institutionToEdit.setInstitutionId(institutionId);
                DatabaseUtils.updateInstitution(connection, institutionToEdit);
                return DatabaseUtils.getAllInstitutions(connection);
            } catch (DatabaseConnectionException | SQLException e ) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

    /* --------------------------------------------------------------------------------------------------------------*/
    /* -------------------------------------------- DOCTORS SERVICES ------------------------------------------------*/
    /* --------------------------------------------------------------------------------------------------------------*/

    @Override
    public List<Doctor> getDoctors(Citizen subject) {
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "view", "doctorsPage", "");
        if (authorization) {
            try {
                Connection connection = (new DatabaseConnector()).getConnection();
                return DatabaseUtils.getDoctorsByAdminId(connection, subject.getCitizenId());
            } catch (DatabaseConnectionException | SQLException e ) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public List<Doctor> getAddDoctorPage(Citizen subject) {
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "create", "doctorsPage", "");
        if (authorization) {
            try {
                Connection connection = (new DatabaseConnector()).getConnection();
                return DatabaseUtils.getAllDoctorsWithoutInstitution(connection);
            } catch (DatabaseConnectionException | SQLException e ) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public List<Doctor> addDoctor(Citizen subject, String doctorToAdd) {
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "create", "doctorsPage", doctorToAdd);
        if (authorization) {
            try {
                Connection connection = (new DatabaseConnector()).getConnection();
                Admin admin = DatabaseUtils.getAdminByCitizenId(connection, subject.getCitizenId());
                if (admin != null && doctorToAdd != null) {
                    int institutionId = admin.getInstitutionId();
                    DatabaseUtils.setDoctorInstitutionId(connection, doctorToAdd, institutionId, subject.getCitizenId());
                    return DatabaseUtils.getDoctorsByAdminId(connection, subject.getCitizenId());
                }
            } catch (DatabaseConnectionException | SQLException e ) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public List<Doctor> deleteDoctor(Citizen subject, String doctorToDelete) {
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "edit", "doctorsPage", doctorToDelete);
        if (authorization) {
            try {
                Connection connection = (new DatabaseConnector()).getConnection();
                if (doctorToDelete != null) DatabaseUtils.removeDoctorFromInstitution(connection, doctorToDelete, subject.getCitizenId());
                return DatabaseUtils.getDoctorsByAdminId(connection, subject.getCitizenId());
            } catch (DatabaseConnectionException | SQLException e ) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

    /* --------------------------------------------------------------------------------------------------------------*/
    /* ----------------------------------------- APPOINTMENTS SERVICES ----------------------------------------------*/
    /* --------------------------------------------------------------------------------------------------------------*/

    @Override
    public List<DocPatRelation> getAppointments(Citizen subject) {
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "view", "appointmentsPage", "");
        if (authorization) {
            try {
                Connection connection = (new DatabaseConnector()).getConnection();
                return DatabaseUtils.getDocPatRelationsByAdminId(connection, subject.getCitizenId());
            } catch (DatabaseConnectionException | SQLException e ) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public Boolean getAddAppointmentsPage(Citizen subject) {
        return requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "create", "appointmentsPage", "");
    }

    @Override
    public List<DocPatRelation> addAppointment(Citizen subject, DocPatRelation appointmentToAdd) {
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "create", "appointmentsPage", subject.getCitizenId());
        if (authorization) {
            try {
                Connection connection = (new DatabaseConnector()).getConnection();
                Citizen patient = DatabaseUtils.getCitizenById(connection, appointmentToAdd.getPatientCitizenId());
                Citizen doctor = DatabaseUtils.getCitizenById(connection, appointmentToAdd.getDoctorCitizenId());
                appointmentToAdd.setPatient(patient);
                appointmentToAdd.setDoctor(doctor);
                appointmentToAdd.setAdminCitizenId(subject.getCitizenId());
                appointmentToAdd.setBeginDate(new java.sql.Date(new java.util.Date().getTime()));
                DatabaseUtils.addDocPatRelation(connection, appointmentToAdd);
                return DatabaseUtils.getDocPatRelationsByAdminId(connection, subject.getCitizenId());
            } catch (DatabaseConnectionException | SQLException e ) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public List<DocPatRelation> deleteAppointment(Citizen subject, int appointmentId) {
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "edit", "appointmentsPage", Integer.toString(appointmentId));
        if (authorization) {
            try {
                Connection connection = (new DatabaseConnector()).getConnection();
                DatabaseUtils.removeDocPatRelation(connection, appointmentId);
                return DatabaseUtils.getDocPatRelationsByAdminId(connection, subject.getCitizenId());
            } catch (DatabaseConnectionException | SQLException e ) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

    /* --------------------------------------------------------------------------------------------------------------*/
    /* --------------------------------------- MEDICAL RECORD SERVICES ----------------------------------------------*/
    /* --------------------------------------------------------------------------------------------------------------*/

    @Override
    public SignedMedicalRecord getMedicalRecord(Citizen subject, String citizenId, int idMedRec) {
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "view", "medicalRecordsPage", citizenId);
        if (authorization) {
            try {
                Connection connection = (new DatabaseConnector()).getConnection();
                return DatabaseUtils.getMedicalRecordById(connection, idMedRec);
            } catch (DatabaseConnectionException | SQLException | InvalidKeyException | SignatureException | NoSuchAlgorithmException | KeyStoreException | CertificateException | UnrecoverableEntryException | IOException e ) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public Boolean getAddMedicalRecordPage(Citizen subject, String citizenId) {
        return requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "create", "medicalRecordsPage", citizenId);
    }

    @Override
    public List<SignedMedicalRecord> getMedicalRecordsByCitizenId(Citizen subject, String citizenId) {
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "view", "medicalRecordsPage", citizenId);
        if (authorization) {
            try {
                Connection connection = (new DatabaseConnector()).getConnection();
                return DatabaseUtils.getMedicalRecordsByPatientCitizenId(connection, citizenId);
            } catch (DatabaseConnectionException | SQLException | InvalidKeyException | SignatureException | NoSuchAlgorithmException | KeyStoreException | CertificateException | UnrecoverableEntryException | IOException e ) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public Citizen getMedicalRecordCitizen(Citizen subject, String citizenId) {
        try {
            Connection connection = (new DatabaseConnector()).getConnection();
            return DatabaseUtils.getCitizenById(connection, citizenId);
        } catch (DatabaseConnectionException | SQLException e ) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Institution getMedicalRecordInstitution(Citizen subject, String citizenId) {
        try {
            Connection connection = (new DatabaseConnector()).getConnection();
            return DatabaseUtils.getInstitutionByDoctorId(connection, citizenId);
        } catch (DatabaseConnectionException | SQLException e ) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public MedicalRecord addMedicalRecord(Citizen subject, MedicalRecord record) {
        try {
            Connection connection = (new DatabaseConnector()).getConnection();
            DatabaseUtils.addMedicalRecord(connection, record);
        } catch (DatabaseConnectionException | SQLException | InvalidKeyException | KeyStoreException | NoSuchAlgorithmException | CertificateException | OperatorCreationException | SignatureException | UnrecoverableEntryException | IOException | InterruptedException e ) {
            log.error(e.getMessage());
        }
        return null;
    }

    /* --------------------------------------------------------------------------------------------------------------*/
    /* -------------------------------------------- PATIENTS SERVICES -----------------------------------------------*/
    /* --------------------------------------------------------------------------------------------------------------*/

    @Override
    public List<Patient> getPatients(Citizen subject) {
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "view", "patientsPage", "");
        if (authorization) {
            try {
                Connection connection = (new DatabaseConnector()).getConnection();
                List<Patient> patients = DatabaseUtils.getPatientsByDoctorCitizenId(connection, subject.getCitizenId());
                List<Patient> validPatients = new ArrayList<>();
                for(Patient patient : patients) {
                    Citizen citPatient = getCitizen(subject, patient.getCitizenId());
                    if (citPatient != null)
                        validPatients.add(patient);
                }
                return validPatients;
            } catch (DatabaseConnectionException | SQLException e ) {
                log.error(e.getMessage());
            }
        }
        return null;
    }
}