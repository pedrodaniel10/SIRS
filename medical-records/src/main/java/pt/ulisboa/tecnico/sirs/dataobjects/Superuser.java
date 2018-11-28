package pt.ulisboa.tecnico.sirs.dataobjects;

public class Superuser {
	private int superuserId;
	private String citizenId;

	public Superuser() {}
	
	public Superuser(int superuserId, String citizenId) {
		this.superuserId = superuserId;
		this.citizenId = citizenId;
	}

	public int getSuperuserId() {
		return superuserId;
	}

	public void setSuperuserId(int superuserId) {
		this.superuserId = superuserId;
	}

	public String getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(String citizenId) {
		this.citizenId = citizenId;
	}
	
}
