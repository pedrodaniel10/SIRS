package pt.ulisboa.tecnico.sirs.domain;

public class Doctor {
	private int doctorId;
	private int citizenId;
	private Institution institution;
	
	public Doctor() {}
	
	public Doctor(int doctorId, int citizenId, Institution institution) {
		this.doctorId = doctorId;
		this.citizenId = citizenId;
		this.institution = institution;
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
	
	public Institution getInstitution() {
		return institution;
	}
	
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
}
