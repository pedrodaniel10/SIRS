package pt.ulisboa.tecnico.sirs.interfaces;

import org.apache.log4j.Logger;
import pt.ulisboa.tecnico.sirs.api.dataobjects.Citizen;
import pt.ulisboa.tecnico.sirs.api.dataobjects.Institution;

public class DataObjectCreation {

    private static Logger log = Logger.getLogger(DataObjectCreation.class);

    public static Citizen createCitizen(Citizen citizen, String superuserCitizenId) {
        citizen.addRole(Citizen.Role.PATIENT);
        return new Citizen(citizen.getCitizenId(), citizen.getCitizenName(), citizen.getGender(),
                citizen.getDateOfBirth(), citizen.getEmail(), citizen.getPassword(), citizen.getProfilePic(),
                superuserCitizenId, citizen.getRoles());
    }

    public static Institution createInstitution(Institution institution, String superuserCitizenId) {
        Institution newInstitution = new Institution(0, institution.getInstitutionName(), institution.getInstitutionAddress(),
                institution.getProfilePic(), superuserCitizenId);
        newInstitution.setAdminCitizenId(institution.getAdminCitizenId());
        return newInstitution;
    }

}
