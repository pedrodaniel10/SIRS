package pt.ulisboa.tecnico.sirs.api;

import pt.ulisboa.tecnico.sirs.api.dataobjects.Citizen;

import java.util.List;

public interface MedicalRecordsService {
    Boolean getWelcomePage();

    Boolean getLoginRegisterPage();
    Boolean postLoginRegisterPage();

    List<Citizen> getCitizens(String subjectId);
    Boolean getAddCitizensPage(String subjectId);
    List<Citizen> deleteCitizen(String subjectId, String citizenToDelete);
    Boolean editCitizen(String subjectId, String citizenToEdit);

}
