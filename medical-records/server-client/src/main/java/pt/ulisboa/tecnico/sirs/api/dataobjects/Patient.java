package pt.ulisboa.tecnico.sirs.api.dataobjects;

import java.io.Serializable;

public class Patient implements Serializable {
	private int patientId;
	private String citizenId;
	private Citizen citizen;

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

	public Citizen getCitizen() {
		return citizen;
	}

	public void setCitizen(Citizen citizen) {
		this.citizen = citizen;
	}
}
