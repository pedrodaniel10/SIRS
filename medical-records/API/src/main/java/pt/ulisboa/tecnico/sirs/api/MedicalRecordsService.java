package pt.ulisboa.tecnico.sirs.api;

import pt.ulisboa.tecnico.sirs.api.dataobjects.Citizen;
import pt.ulisboa.tecnico.sirs.api.dataobjects.Doctor;
import pt.ulisboa.tecnico.sirs.api.dataobjects.Institution;

import java.util.List;

public interface MedicalRecordsService {
    Boolean getWelcomePage();

    Boolean getLoginRegisterPage();
    Boolean postLoginRegisterPage();
    Citizen getSessionCitizen();

    Citizen getCitizen(Citizen subject, String citizenId);
    List<Citizen> getCitizens(Citizen subject);
    Boolean getAddCitizensPage(Citizen subject);
    List<Citizen> addCitizen(Citizen subject, Citizen citizenToAdd);
    Citizen getEditCitizensPage(Citizen subject, String citizenToEdit);
    List<Citizen> editCitizen(Citizen subject, Citizen citizenToEdit);

    List<Institution> getInstitutions(Citizen subject);
    Boolean getAddInstitutionsPage(Citizen subject);
    List<Institution> addInstitution(Citizen subject, Institution institutionToAdd);
    Institution getEditInstitutionPage(Citizen subject, String institutionToEdit);
    List<Institution> editInstitution(Citizen subject, Institution institutionToEdit);

    List<Doctor> getDoctors(Citizen subject);
    List<Doctor> getAddDoctorPage(Citizen subject);
    List<Doctor> addDoctor(Citizen subject, String doctorToAdd);
    List<Doctor> deleteDoctor(Citizen subject, String doctorToDelete);


}
