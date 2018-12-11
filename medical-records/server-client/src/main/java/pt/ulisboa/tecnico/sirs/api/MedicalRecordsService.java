package pt.ulisboa.tecnico.sirs.api;

import pt.ulisboa.tecnico.sirs.api.dataobjects.*;

import java.util.List;
import pt.ulisboa.tecnico.sirs.api.exceptions.AdminException;
import pt.ulisboa.tecnico.sirs.api.exceptions.CitizenException;

public interface MedicalRecordsService {
    Citizen getWelcomePage(Citizen citizen);

    Citizen getLoginPage(Citizen citizen);
    Citizen postLoginPage(Citizen citizen);
    Citizen getSessionCitizen(String authToken);

    Citizen getCitizen(Citizen subject, String citizenId);
    List<Citizen> getCitizens(Citizen subject);
    Boolean getAddCitizensPage(Citizen subject);
    List<Citizen> addCitizen(Citizen subject, Citizen citizenToAdd) throws CitizenException;
    Citizen getEditCitizensPage(Citizen subject, String citizenToEdit);
    List<Citizen> editCitizen(Citizen subject, String citizenId, Citizen citizenToEdit);

    Institution getInstitution(Citizen subject, int institutionId);
    List<Institution> getInstitutions(Citizen subject);
    Boolean getAddInstitutionsPage(Citizen subject);
    List<Institution> addInstitution(Citizen subject, Institution institutionToAdd) throws AdminException;
    Institution getEditInstitutionPage(Citizen subject, int institutionToEdit);
    List<Institution> editInstitution(Citizen subject, int institutionId, Institution institutionToEdit);

    Doctor getDoctor(Citizen subject, String doctorCitizenId);
    List<Doctor> getDoctors(Citizen subject);
    List<Doctor> getAddDoctorPage(Citizen subject);
    List<Doctor> addDoctor(Citizen subject, String doctorToAdd);
    List<Doctor> deleteDoctor(Citizen subject, String doctorToDelete);

    List<DocPatRelation> getAppointments(Citizen subject);
    Boolean getAddAppointmentsPage(Citizen subject);
    List<DocPatRelation> addAppointment(Citizen subject, DocPatRelation appointmentToAdd);
    List<DocPatRelation> deleteAppointment(Citizen subject, int appointmentId);

    SignedMedicalRecord getMedicalRecord(Citizen subject, String citizenId, String idMedRec);
    Boolean getAddMedicalRecordPage(Citizen subject, String citizenId);
    List<SignedMedicalRecord> getMedicalRecordsByCitizenId(Citizen subject, String citizenId);

    List<Citizen> getPatients(Citizen subject);
}
