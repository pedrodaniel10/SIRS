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
    List<Citizen> deleteCitizen(Citizen subject, String citizenToDelete);
    Citizen editCitizen(Citizen subject, String citizenToEdit);

    List<Institution> getInstitutions(Citizen subject);
    Boolean getAddInstitutionsPage(Citizen subject);
    Institution editInstitution(Citizen subject, String institutionToEdit);
    List<Institution> deleteInstitution(Citizen subject, String institutionToDelete);

    List<Doctor> getDoctors(Citizen subject);
    List<Doctor> getDoctorsWithInstitution(Citizen subject, String adminId);
    Boolean getAddDoctorPage(Citizen subject);
    List<Doctor> addDoctor(Citizen subject, String doctorToAdd);
    List<Doctor> deleteDoctor(Citizen subject, String doctorToDelete);


}
