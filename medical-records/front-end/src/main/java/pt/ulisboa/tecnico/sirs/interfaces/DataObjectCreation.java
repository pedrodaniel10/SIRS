package pt.ulisboa.tecnico.sirs.interfaces;

import org.apache.log4j.Logger;
import pt.ulisboa.tecnico.sirs.api.dataobjects.Citizen;
import pt.ulisboa.tecnico.sirs.api.dataobjects.Institution;
import pt.ulisboa.tecnico.sirs.api.dataobjects.Login;

public class DataObjectCreation {

    private static Logger log = Logger.getLogger(DataObjectCreation.class);

    public static Citizen createCitizen(Citizen citizen, String superuserCitizenId) {
        citizen.addRole(Citizen.Role.PATIENT);
        Citizen newCitizen = new Citizen(citizen.getCitizenId(), citizen.getCitizenName(), citizen.getGender(),
                citizen.getDateOfBirth(), citizen.getEmail(), "", citizen.getProfilePic(),
                superuserCitizenId, citizen.getRoles());
        newCitizen.setPassword(citizen.getPassword());
        return newCitizen;
    }

    public static Institution createInstitution(Institution institution, String superuserCitizenId) {
        Institution newInstitution = new Institution(0, institution.getInstitutionName(), institution.getInstitutionAddress(),
                institution.getProfilePic(), superuserCitizenId);
        newInstitution.setAdminCitizenId(institution.getAdminCitizenId());
        return newInstitution;
    }

    public static Login createLogin(Login login){
        return new Login(login.getEmail(), login.getPassword());
    }

}
