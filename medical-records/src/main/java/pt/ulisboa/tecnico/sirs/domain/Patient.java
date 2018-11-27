package pt.ulisboa.tecnico.sirs.domain;

public class Patient{
	private int patientId;
	private int citizenId;

	public Patient() {
	}

	public Patient(int patientId, int citizenId) {
		this.patientId = patientId;
		this.citizenId = citizenId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public int getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(int citizenId) {
		this.citizenId = citizenId;
	}
}
