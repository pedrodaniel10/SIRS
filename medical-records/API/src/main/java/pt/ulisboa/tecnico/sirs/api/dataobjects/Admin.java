package pt.ulisboa.tecnico.sirs.api.dataobjects;

public class Admin {
	private int adminId;
	private String citizenId;
	private int institutionId;
	private String superuserCitizenId;

	public Admin() {}
	
	public Admin(int adminId, String citizenId, int institutionId, String superuserCitizenId) {
		this.adminId = adminId;
		this.citizenId = citizenId;
		this.institutionId = institutionId;
		this.superuserCitizenId = superuserCitizenId;
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

	public String getSuperuserCitizenId() {
		return superuserCitizenId;
	}

	public void setSuperuserCitizenId(String superuserCitizenId) {
		this.superuserCitizenId = superuserCitizenId;
	}
}
