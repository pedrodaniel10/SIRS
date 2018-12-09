package pt.ulisboa.tecnico.sirs.dataobjects;

public class Patient{
	private int patientId;
	private String citizenId;

	public Patient() {
	}

	public Patient(int patientId, String citizenId) {
		this.patientId = patientId;
		this.citizenId = citizenId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(String citizenId) {
		this.citizenId = citizenId;
	}
}
