package pt.ulisboa.tecnico.sirs.server;

import org.apache.log4j.Logger;
import pt.ulisboa.tecnico.sirs.api.MedicalRecordsService;
import pt.ulisboa.tecnico.sirs.api.dataobjects.Citizen;
import pt.ulisboa.tecnico.sirs.api.dataobjects.Doctor;
import pt.ulisboa.tecnico.sirs.api.dataobjects.Institution;
import pt.ulisboa.tecnico.sirs.api.dataobjects.ServiceUtils;
import pt.ulisboa.tecnico.sirs.database.DatabaseConnector;
import pt.ulisboa.tecnico.sirs.database.exceptions.DatabaseConnectionException;
import pt.ulisboa.tecnico.sirs.database.utils.DatabaseUtils;
import pt.ulisboa.tecnico.sirs.pdp.PolicyEnforcementPoint;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordsServiceImpl implements MedicalRecordsService {

    private static Logger log = Logger.getLogger(MedicalRecordsService.class);

    public Boolean requestEvaluation(String subjectId, List<String> roles, String action, String resourceName, String resourceId) {
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
        try {
            Connection connection = (new DatabaseConnector()).getConnection();
            Boolean authorization = requestEvaluation(subject.getCitizenId(),
                    ServiceUtils.parseRoleList(subject.getRoles()), "view", "citizensPage", citizenId);

            if (authorization) {
                return DatabaseUtils.getCitizenById(connection, citizenId);
            }
        } catch (DatabaseConnectionException | SQLException e ) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Citizen> getCitizens(Citizen subject) {
        try {
            Connection connection = (new DatabaseConnector()).getConnection();
            Boolean authorization = requestEvaluation(subject.getCitizenId(),
                    ServiceUtils.parseRoleList(subject.getRoles()), "view", "citizensPage", "");

            if (authorization) {
                return DatabaseUtils.getAllCitizens(connection);
            }
        } catch (DatabaseConnectionException | SQLException e ) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean getAddCitizensPage(Citizen subject) {
        return requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "create", "citizensPage", "");
    }

    @Override
    public List<Citizen> addCitizen(Citizen subject, Citizen citizenToAdd) {
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "create", "citizensPage", ""/*citizenToAdd.getCitizenId()*/);
        if (authorization) {
            try {
                Connection connection = (new DatabaseConnector()).getConnection();
                if (citizenToAdd != null) DatabaseUtils.addCitizen(connection, citizenToAdd);
                return DatabaseUtils.getAllCitizens(connection);
            } catch (DatabaseConnectionException | SQLException e ) {
                log.error(e.getMessage());
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
    public List<Institution> getInstitutions(Citizen subject) {
        try {
            Connection connection = (new DatabaseConnector()).getConnection();
            Boolean authorization = requestEvaluation(subject.getCitizenId(),
                    ServiceUtils.parseRoleList(subject.getRoles()), "view", "institutionsPage", "");

            if (authorization) {
                return DatabaseUtils.getAllInstitutions(connection);
            }
        } catch (DatabaseConnectionException | SQLException e ) {
            log.error(e.getMessage());
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
    public Institution getEditInstitutionPage(Citizen subject, String institutionToEdit) {
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "edit", "institutionsPage", "" /*institutionToEdit*/);
        if (authorization) {
            try {
                Connection connection = (new DatabaseConnector()).getConnection();
                if (institutionToEdit != null)
                    //return DatabaseUtils.getInstitutionById(connection, institutionToEdit);
                    return getAInstitution();
            } catch (DatabaseConnectionException /*| SQLException*/ e ) {
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
    public List<Doctor> getDoctors(Citizen subject) {
        //STATIC CODE
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "view", "doctorsPage", "");
        if (!authorization) return null;

        return getSomeDoctors();
    }

    @Override
    public List<Doctor> getDoctorsWithInstitution(Citizen subject, String adminId) {
        //STATIC CODE
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "view", "doctorsPage", "");
        if (!authorization) return null;

        return getSomeDoctors();
    }


    @Override
    public Boolean getAddDoctorPage(Citizen subject) {
        return requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "create", "doctorsPage", "");
    }

    @Override
    public List<Doctor> addDoctor(Citizen subject, String doctorToAdd) {
        //STATIC CODE
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "create", "doctorsPage", doctorToAdd);
        if (!authorization) return null;
        return getSomeDoctors();
    }

    @Override
    public List<Doctor> deleteDoctor(Citizen subject, String doctorToDelete) {
        //STATIC CODE
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "edit", "doctorsPage", doctorToDelete);
        if (!authorization) return null;
        return getSomeDoctors();
    }

    // This code only serves to simulate a session citizen because session is not implemented.
    private Citizen getSessionCitizenTest() {
        List<Citizen.Role> roles = new ArrayList<>();
        roles.add(Citizen.Role.SUPERUSER);

        return new Citizen("12345p", "David Admin", Citizen.Gender.MALE, LocalDate.of(2000, 1, 1), "david.paciente@megamail.com", "pass", "https://blog.estantevirtual.com.br/wp-content/uploads/fernando-pessoa-1.jpg", "", roles);
    }

    // This code only serves to simulate some institutions because there's no database connection yet.
    private List<Institution> getSomeInstitutions() {
        Institution i1 = new Institution(11111, "Institution1", "Rua da Praça nº1 Lisboa", "", "");
        Institution i2 = new Institution(11112, "Institution2", "Rua da Praça nº1 Lisboa", "", "");
        Institution i3 = new Institution(11113, "Institution3", "Rua da Praça nº1 Lisboa", "", "");

        List<Institution> institutions = new ArrayList<>();
        institutions.add(i1);
        institutions.add(i2);
        institutions.add(i3);

        return institutions;
    }

    // This code only serves to simulate some institutions because there's no database connection yet.
    private Institution getAInstitution() {
        return new Institution(11111, "Institution1", "Rua da Praça nº1 Lisboa", "", "");
    }

    // This code only serves to simulate some institutions because there's no database connection yet.
    private List<Doctor> getSomeDoctors() {
        Doctor d1 = new Doctor(12345, "12345d", 11111, "123", "124");
        Doctor d2 = new Doctor(12346, "12346d", 11111, "123", "124");

        List<Doctor> doctors = new ArrayList<>();
        doctors.add(d1);
        doctors.add(d2);

        return doctors;
    }
}
