package pt.ulisboa.tecnico.sirs.domain;

public class Doctor {
	private int doctorId;
	private int citizenId;
	private int institutionId;
	private int superuserId;
	private int adminId;
	
	public Doctor() {}
	
	public Doctor(int doctorId, int citizenId, int institutionId, int superuserId, int adminId) {
		this.doctorId = doctorId;
		this.citizenId = citizenId;
		this.institutionId = institutionId;
		this.superuserId = superuserId;
		this.adminId = adminId;
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

	public int getSuperuserId() {
		return superuserId;
	}

	public void setSuperuserId(int superuserId) {
		this.superuserId = superuserId;
	}

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
}
