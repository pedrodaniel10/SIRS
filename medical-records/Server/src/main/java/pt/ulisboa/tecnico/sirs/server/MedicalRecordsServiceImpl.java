package pt.ulisboa.tecnico.sirs.server;

import org.apache.log4j.Logger;
import pt.ulisboa.tecnico.sirs.api.MedicalRecordsService;
import pt.ulisboa.tecnico.sirs.api.dataobjects.*;
import pt.ulisboa.tecnico.sirs.api.exceptions.CitizenException;
import pt.ulisboa.tecnico.sirs.database.DatabaseConnector;
import pt.ulisboa.tecnico.sirs.database.exceptions.DatabaseConnectionException;
import pt.ulisboa.tecnico.sirs.database.utils.DatabaseUtils;
import pt.ulisboa.tecnico.sirs.pdp.PolicyEnforcementPoint;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordsServiceImpl implements MedicalRecordsService {

    private static Logger log = Logger.getLogger(MedicalRecordsService.class);

    private Boolean requestEvaluation(String subjectId, List<String> roles, String action, String resourceName, String resourceId) {
        PolicyEnforcementPoint policyEnforcementPoint = new PolicyEnforcementPoint();

        return policyEnforcementPoint.requestEvaluation(subjectId,
                roles, action, resourceName, resourceId);
    }

    @Override
    public Boolean getWelcomePage() {

        ArrayList<String> roles = new ArrayList<>();
        return requestEvaluation("",
                roles, "view", "indexPage", "");
    }

    @Override
    public Boolean getLoginRegisterPage() {

        ArrayList<String> roles = new ArrayList<>();
        return requestEvaluation("",
                roles, "view", "loginRegisterPage", "");
    }

    @Override
    public Boolean postLoginRegisterPage() {

        ArrayList<String> roles = new ArrayList<>();
        return requestEvaluation("",
                roles, "edit", "loginRegisterPage", "");
    }

    @Override
    public Citizen getSessionCitizen() {
        /*
        GET SESSION CITIZEN TODO
         */

        //STATIC CODE
        return getSessionCitizenTest();
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
                ServiceUtils.parseRoleList(subject.getRoles()), "create", "citizensPage", ""/*citizenToAdd.getCitizenId()*/);
        if (authorization) {
            try {
                Connection connection = (new DatabaseConnector()).getConnection();
                if (citizenToAdd != null) DatabaseUtils.addCitizen(connection, citizenToAdd);
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
                if (citizenToEdit!=null)
                    return DatabaseUtils.getCitizenById(connection, citizenToEdit);
            } catch (DatabaseConnectionException | SQLException e ) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public List<Citizen> editCitizen(Citizen subject, Citizen citizenToEdit) {
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "edit", "citizensPage", ""/*citizenToEdit.getCitizenId()*/);
        if (authorization) {
            try {
                Connection connection = (new DatabaseConnector()).getConnection();
                if (citizenToEdit != null) DatabaseUtils.updateCitizen(connection, citizenToEdit);
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
    public Institution getInstitution(Citizen subject, int institutionId) {
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "view", "institutionsPage", Integer.toString(institutionId));
        if (authorization) {
            try {
                Connection connection = (new DatabaseConnector()).getConnection();
                return DatabaseUtils.getInstitutionById(connection, institutionId);
            } catch (DatabaseConnectionException | SQLException e ) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

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
    public List<Institution> addInstitution(Citizen subject, Institution institutionToAdd) {
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "create", "institutionsPage", ""/*institutionToAdd.getInstitutionId()*/);
        if (authorization) {
            try {
                Connection connection = (new DatabaseConnector()).getConnection();
                if (institutionToAdd != null) DatabaseUtils.addInstitution(connection, institutionToAdd);
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
    public List<Institution> editInstitution(Citizen subject, Institution institutionToEdit) {
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "edit", "institutionsPage", ""/*institutionToEdit.getInstitutionId()*/);
        if (authorization) {
            try {
                Connection connection = (new DatabaseConnector()).getConnection();
                if (institutionToEdit != null) DatabaseUtils.updateInstitution(connection, institutionToEdit);
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
    public Doctor getDoctor(Citizen subject, String doctorCitizenId) {
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "view", "doctorsPage", doctorCitizenId);
        if (authorization) {
            try {
                Connection connection = (new DatabaseConnector()).getConnection();
                //return DatabaseUtils.getDoctorByCitizenId(connection, doctorCitizenId);
                return getADoctor();
            } catch (DatabaseConnectionException /*| SQLException*/ e) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

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
                ServiceUtils.parseRoleList(subject.getRoles()), "create", "appointmentsPage", ""/*appointmentToAdd.getAdminCitizenId()*/);
        if (authorization) {
            try {
                Connection connection = (new DatabaseConnector()).getConnection();
                if (appointmentToAdd != null) DatabaseUtils.addDocPatRelation(connection, appointmentToAdd);
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
    public MedicalRecord getMedicalRecord(Citizen subject, String citizenId, String idMedRec) {
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "view", "medicalRecordsPage", citizenId);
        if (authorization) {
            try {
                Connection connection = (new DatabaseConnector()).getConnection();
                return DatabaseUtils.getMedicalRecordsByPatientCitizenId(connection, citizenId).get(0); //this is wrong, we need to get one by id
            } catch (DatabaseConnectionException | SQLException e ) {
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
    public List<MedicalRecord> getMedicalRecordsByCitizenId(Citizen subject, String citizenId) {
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "view", "medicalRecordsPage", citizenId);
        if (authorization) {
            try {
                Connection connection = (new DatabaseConnector()).getConnection();
                return DatabaseUtils.getMedicalRecordsByPatientCitizenId(connection, citizenId);
            } catch (DatabaseConnectionException | SQLException e ) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

    /* --------------------------------------------------------------------------------------------------------------*/
    /* -------------------------------------------- PATIENTS SERVICES -----------------------------------------------*/
    /* --------------------------------------------------------------------------------------------------------------*/

    @Override
    public List<Citizen> getPatients(Citizen subject) {
        /*Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "view", "patientsPage", "");
        if (authorization) {
            try {
                Connection connection = (new DatabaseConnector()).getConnection();
                return DatabaseUtils.getPatientsByDoctorCitizenId(connection, subject.getCitizenId());
            } catch (DatabaseConnectionException | SQLException e ) {
                log.error(e.getMessage());
            }
        }*/
        return null;
    }

    /* --------------------------------------------------------------------------------------------------------------*/
    /* ---------------------------------------------- UTILS SERVICES ------------------------------------------------*/
    /* --------------------------------------------------------------------------------------------------------------*/

    // This code only serves to simulate a session citizen because session is not implemented.
    private Citizen getSessionCitizenTest() {
        try {
            Connection connection = (new DatabaseConnector()).getConnection();
            return DatabaseUtils.getCitizenById(connection, "1");
        } catch (DatabaseConnectionException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // This code only serves to simulate a doctor because there's no database connection yet.
    private Doctor getADoctor() {
        return new Doctor(12345, "12345d", 11111, "12345s", "12345a");
    }

}
