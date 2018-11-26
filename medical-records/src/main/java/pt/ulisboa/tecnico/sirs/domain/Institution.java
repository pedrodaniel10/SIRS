package pt.ulisboa.tecnico.sirs.domain;

public class Institution {
	private int institutionId;
	private String institutionName;
	private String institutionAddress;
	
	public 	Institution() {}
	
	public Institution(int institutionId, String institutionName, String institutionAddress) {
		this.institutionId = institutionId;
		this.institutionName = institutionName;
		this.institutionAddress = institutionAddress;
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
}
