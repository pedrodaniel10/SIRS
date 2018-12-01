package pt.ulisboa.tecnico.sirs.dataobjects;

public class Institution {
	private int institutionId;
	private String institutionName;
	private String institutionAddress;
	private String profilePic;
	private String superuserCitizenId;
	
	public 	Institution() {}
	
	public Institution(int institutionId, String institutionName, String institutionAddress, String profilePic, 
			String superuserCitizenId) {
		this.institutionId = institutionId;
		this.institutionName = institutionName;
		this.institutionAddress = institutionAddress;
		this.profilePic = profilePic;
		this.setSuperuserCitizenId(superuserCitizenId);
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

	public String getSuperuserCitizenId() {
		return superuserCitizenId;
	}

	public void setSuperuserCitizenId(String superuserCitizenId) {
		this.superuserCitizenId = superuserCitizenId;
	}
}
