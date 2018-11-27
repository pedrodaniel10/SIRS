package pt.ulisboa.tecnico.sirs.domain;

public class Doctor {
	private int doctorId;
	private int citizenId;
	private int institutionId;
	
	public Doctor() {}
	
	public Doctor(int doctorId, int citizenId, int institutionId) {
		this.doctorId = doctorId;
		this.citizenId = citizenId;
		this.institutionId = institutionId;
	}

	public int getDoctorId() {
		return doctorId;
	}
	
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	
	public int getCitizenId() {
		return citizenId;
	}
	
	public void setCitizenId(int citizenId) {
		this.citizenId = citizenId;
	}
	
	public int getInstitution() {
		return institutionId;
	}
	
	public void setInstitution(int institutionId) {
		this.institutionId = institutionId;
	}
}
