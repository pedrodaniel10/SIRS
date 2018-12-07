package pt.ulisboa.tecnico.sirs.server;

import pt.ulisboa.tecnico.sirs.api.MedicalRecordsService;
import pt.ulisboa.tecnico.sirs.api.dataobjects.Citizen;
import pt.ulisboa.tecnico.sirs.api.dataobjects.ServiceUtils;
import pt.ulisboa.tecnico.sirs.pdp.PolicyEnforcementPoint;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("Duplicates")
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
    public List<Citizen> getCitizens(String subjectId) {
        /* THIS IS THE CODE WHEN DATABASE IS MERGED - not tested obviously
        Connection conn = (new DatabaseConnector()).getConnection();
        Citizen citizen = DatabaseUtils.getCitizenById(conn, subjectId);
        Boolean authorization = requestEvaluation(subjectId,
                ServiceUtils.parseRoleList(citizen.getRoles()), "view", "citizensPage", "");
        if (!authorization) return null;
        List<Citizen> citizens = DatabaseUtils.getAllCitizens(conn);
        return citizens;
         */

        //STATIC CODE
        List<Citizen.Role> roles = new ArrayList<>();
        roles.add(Citizen.Role.SUPERUSER);
        Boolean authorization = requestEvaluation("",
                ServiceUtils.parseRoleList(roles), "view", "citizensPage", "");
        if (!authorization) return null;
        List<Citizen> citizens = getSomeCitizens();

        return citizens;
    }

    @Override
    public Boolean getAddCitizensPage(String subjectId) {
        /* THIS IS THE CODE WHEN DATABASE IS MERGED - not tested obviously
        Connection conn = (new DatabaseConnector()).getConnection();
        Citizen citizen = DatabaseUtils.getCitizenById(conn, subjectId);
        Boolean authorization = requestEvaluation(subjectId,
                citizen.getRoles(), "create", "citizensPage", "");
        if (!authorization) return null;
        return authorization;
         */

        //STATIC CODE
        List<Citizen.Role> roles = new ArrayList<>();
        roles.add(Citizen.Role.SUPERUSER);
        Citizen c1 = new Citizen("12345", "David Silva", Citizen.Gender.MALE, LocalDate.of(2000, 1, 1), "david.silva@megamail.com", "pass", "", "", roles);

        return requestEvaluation("",
                ServiceUtils.parseRoleList(roles), "create", "citizensPage", "");
    }

    @Override
    public List<Citizen> deleteCitizen(String subjectId, String citizenToDelete) {
        /* THIS IS THE CODE WHEN DATABASE IS MERGED - not tested obviously
        Connection conn = (new DatabaseConnector()).getConnection();
        Citizen citizen = DatabaseUtils.getCitizenById(conn, subjectId);
        Boolean authorization = requestEvaluation(subjectId,
                citizen.getRoles(), "edit", "citizensPage", "");
        if (!authorization) return null;
        List<Citizen> citizens = DatabaseUtils.getAllCitizens(conn);
        return citizens;
         */

        //STATIC CODE
        List<Citizen.Role> roles = new ArrayList<>();
        roles.add(Citizen.Role.SUPERUSER);
        Citizen c1 = new Citizen("12345", "David Silva", Citizen.Gender.MALE, LocalDate.of(2000, 1, 1), "david.silva@megamail.com", "pass", "", "", roles);
        Boolean authorization = requestEvaluation("",
                ServiceUtils.parseRoleList(roles), "edit", "citizensPage", "");
        if (!authorization) return null;
        List<Citizen> citizens = getSomeCitizens();
        return citizens;

    }

    @Override
    public Boolean editCitizen(String subjectId, String citizenToDelete) {
        /* THIS IS THE CODE WHEN DATABASE IS MERGED - not tested obviously
        Connection conn = (new DatabaseConnector()).getConnection();
        Citizen citizen = DatabaseUtils.getCitizenById(conn, subjectId);
        Boolean authorization = requestEvaluation(subjectId,
                citizen.getRoles(), "edit", "citizensPage", "");
        return authorization;
         */

        //STATIC CODE
        List<Citizen.Role> roles = new ArrayList<>();
        roles.add(Citizen.Role.SUPERUSER);
        Citizen c1 = new Citizen("12345", "David Silva", Citizen.Gender.MALE, LocalDate.of(2000, 1, 1), "david.silva@megamail.com", "pass", "", "", roles);
        Boolean authorization = requestEvaluation("",
                ServiceUtils.parseRoleList(roles), "edit", "citizensPage", "");
        return authorization;

    }

    /*
    This code only serves to simulate some citizens because there's no database connection yet.
    TO DELETE
     */
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


}
