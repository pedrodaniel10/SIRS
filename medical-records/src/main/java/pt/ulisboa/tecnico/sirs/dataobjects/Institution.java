package pt.ulisboa.tecnico.sirs.dataobjects;

public class Institution {
	private int institutionId;
	private String institutionName;
	private String institutionAddress;
	private String profilePic;
	private int superuserId;
	
	public 	Institution() {}
	
	public Institution(int institutionId, String institutionName, String institutionAddress, String profilePic, 
			int superuserId) {
		this.institutionId = institutionId;
		this.institutionName = institutionName;
		this.institutionAddress = institutionAddress;
		this.profilePic = profilePic;
		this.superuserId = superuserId;
	}

	public int getInstitutionId() {
		return institutionId;
	}
	
	public void setInstitutionId(int institutionId) {
		this.institutionId = institutionId;
	}
	
	public String getInstitutionName() {
		return institutionName;
	}
	
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
	
	public String getInstitutionAddress() {
		return institutionAddress;
	}
	
	public void setInstitutionAddress(String institutionAddress) {
		this.institutionAddress = institutionAddress;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public int getSuperuserId() {
		return superuserId;
	}

	public void setSuperuserId(int superuserId) {
		this.superuserId = superuserId;
	}
}
