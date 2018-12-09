package pt.ulisboa.tecnico.sirs.server;

import pt.ulisboa.tecnico.sirs.api.MedicalRecordsService;
import pt.ulisboa.tecnico.sirs.api.dataobjects.Citizen;
import pt.ulisboa.tecnico.sirs.api.dataobjects.Doctor;
import pt.ulisboa.tecnico.sirs.api.dataobjects.Institution;
import pt.ulisboa.tecnico.sirs.api.dataobjects.ServiceUtils;
import pt.ulisboa.tecnico.sirs.pdp.PolicyEnforcementPoint;

import javax.print.Doc;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MedicalRecordsServiceImpl implements MedicalRecordsService {

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

    @Override
    public Citizen getCitizen(Citizen subject, String citizenId) {
        /* THIS IS THE CODE WHEN DATABASE IS MERGED - not tested obviously
        Connection conn = (new DatabaseConnector()).getConnection();
        Boolean authorization = requestEvaluation(subject.subjectId,
                ServiceUtils.parseRoleList(subject.getRoles()), "view", "citizensPage", citizenId);
        if (authorization) {
         return DatabaseUtils.getCitizenById(conn, citizenId);
        }
        return null;
         */

        //STATIC CODE
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "view", "citizensPage", citizenId);
        return authorization? getACitizen() : null;
    }

    @Override
    public List<Citizen> getCitizens(Citizen subject) {
        //STATIC CODE
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "view", "citizensPage", "");
        if (!authorization) return null;
        List<Citizen> citizens = getSomeCitizens();

        return citizens;
    }

    @Override
    public Boolean getAddCitizensPage(Citizen subject) {
        //STATIC CODE
        return requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "create", "citizensPage", "");
    }

    @Override
    public List<Citizen> deleteCitizen(Citizen subject, String citizenToDelete) {
        //STATIC CODE
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "edit", "citizensPage", citizenToDelete);
        if (!authorization) return null;
        return getSomeCitizens();

    }

    @Override
    public Citizen editCitizen(Citizen subject, String citizenToEdit) {
        //STATIC CODE
        Citizen c1 = getACitizen(); //database get citizenbyid(citizenToEdit)
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "edit", "citizensPage", citizenToEdit);
        return authorization? c1 : null;

    }

    @Override
    public List<Institution> getInstitutions(Citizen subject) {
        //STATIC CODE
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "view", "institutionsPage", "");
        if (!authorization) return null;

        return getSomeInstitutions();
    }

    @Override
    public Boolean getAddInstitutionsPage(Citizen subject) {
        return requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "create", "institutionsPage", "");
    }

    @Override
    public Institution editInstitution(Citizen subject, String institutionToEdit) {
        //STATIC CODE
        Institution i1 = getAInstitution(); //database get institutionbyid(institutionToEdit)
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "edit", "institutionsPage", institutionToEdit);
        return authorization? i1 : null;
    }

    @Override
    public List<Institution> deleteInstitution(Citizen subject, String institutionToDelete) {
        //STATIC CODE
        Boolean authorization = requestEvaluation(subject.getCitizenId(),
                ServiceUtils.parseRoleList(subject.getRoles()), "edit", "institutionsPage", institutionToDelete);
        if (!authorization) return null;
        return getSomeInstitutions();
    }

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


    // This code only serves to simulate some citizens because there's no database connection yet.
    private List<Citizen> getSomeCitizens() {
        List<Citizen.Role> roles1 = new ArrayList<>();
        roles1.add(Citizen.Role.ADMIN);
        Citizen c1 = new Citizen("12345", "David Silva", Citizen.Gender.MALE, LocalDate.of(2000, 1, 1), "david.silva@megamail.com", "pass", "", "", roles1);
        List<Citizen.Role> roles2 = new ArrayList<>();
        roles2.add(Citizen.Role.DOCTOR);
        Citizen c2 = new Citizen("12346", "Felipe Anderson", Citizen.Gender.MALE, LocalDate.of(2000, 1, 1), "andersona@megamail.com", "pass", "", "", roles2);
        List<Citizen> citizens = new ArrayList<>();
        citizens.add(c1);
        citizens.add(c2);

        return citizens;
    }

    // This code only serves to simulate a citizen because there's no database connection yet.
    private Citizen getACitizen() {
        List<Citizen.Role> roles = new ArrayList<>();
        roles.add(Citizen.Role.PATIENT);

        return new Citizen("12345p", "David Paciente", Citizen.Gender.MALE, LocalDate.of(2000, 1, 1), "david.paciente@megamail.com", "pass", "", "", roles);
    }

    // This code only serves to simulate a session citizen because session is not implemented.
    private Citizen getSessionCitizenTest() {
        List<Citizen.Role> roles = new ArrayList<>();
        roles.add(Citizen.Role.ADMIN);

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
