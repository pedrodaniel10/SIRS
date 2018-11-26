package pt.ulisboa.tecnico.sirs.domain;

public class Admin {
	private int adminId;
	private int citizenId;
	private Institution institution;
	
	public Admin() {}
	
	public Admin(int adminId, int citizenId, Institution institution) {
		this.adminId = adminId;
		this.citizenId = citizenId;
		this.institution = institution;
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
	
	public Institution getInstitution() {
		return institution;
	}
	
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
}
