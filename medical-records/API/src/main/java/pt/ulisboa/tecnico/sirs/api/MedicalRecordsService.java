package pt.ulisboa.tecnico.sirs.api;

import pt.ulisboa.tecnico.sirs.api.dataobjects.*;

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

    Institution getInstitution(Citizen subject, int institutionId);
    List<Institution> getInstitutions(Citizen subject);
    Boolean getAddInstitutionsPage(Citizen subject);
    List<Institution> addInstitution(Citizen subject, Institution institutionToAdd);
    Institution getEditInstitutionPage(Citizen subject, int institutionToEdit);
    List<Institution> editInstitution(Citizen subject, Institution institutionToEdit);

    Doctor getDoctor(Citizen subject, String doctorCitizenId);
    List<Doctor> getDoctors(Citizen subject);
    List<Doctor> getAddDoctorPage(Citizen subject);
    List<Doctor> addDoctor(Citizen subject, String doctorToAdd);
    List<Doctor> deleteDoctor(Citizen subject, String doctorToDelete);

    List<DocPatRelation> getAppointments(Citizen subject);
    Boolean getAddAppointmentsPage(Citizen subject);
    List<DocPatRelation> addAppointment(Citizen subject, DocPatRelation appointmentToAdd);
    List<DocPatRelation> deleteAppointment(Citizen subject, int appointmentId);

    MedicalRecord getMedicalRecord(Citizen subject, String citizenId, String idMedRec);
    Boolean getAddMedicalRecordPage(Citizen subject, String citizenId);
    List<MedicalRecord> getMedicalRecordsByCitizenId(Citizen subject, String citizenId);

    List<Citizen> getPatients(Citizen subject);
}
