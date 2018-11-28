package pt.ulisboa.tecnico.sirs.dataobjects;

public class Admin {
	private int adminId;
	private String citizenId;
	private int institutionId;

	public Admin() {}
	
	public Admin(int adminId, String citizenId, int institutionId) {
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
	
	public String getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(String citizenId) {
		this.citizenId = citizenId;
	}
	
	public int getInstitutionId() {
		return institutionId;
	}

	public void setInstitutionId(int institutionId) {
		this.institutionId = institutionId;
	}
}
