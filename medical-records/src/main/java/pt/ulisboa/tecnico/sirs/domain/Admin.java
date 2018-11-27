package pt.ulisboa.tecnico.sirs.domain;

public class Admin {
	private int adminId;
	private int citizenId;
	private int institutionId;

	public Admin() {}
	
	public Admin(int adminId, int citizenId, int institutionId) {
		this.adminId = adminId;
		this.citizenId = citizenId;
		this.institutionId = institutionId;
	}
	
	public int getAdminId() {
		return adminId;
	}
	
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	
	public int getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(int citizenId) {
		this.citizenId = citizenId;
	}
	
	public int getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(int institutionId) {
		this.institutionId = institutionId;
	}
}
