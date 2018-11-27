package pt.ulisboa.tecnico.sirs.domain;

public class Superuser {
	private int superuserId;
	private int citizenId;

	public Superuser() {}
	
	public Superuser(int superuserId, int citizenId) {
		this.superuserId = superuserId;
		this.citizenId = citizenId;
	}

	public int getSuperuserId() {
		return superuserId;
	}

	public void setSuperuserId(int superuserId) {
		this.superuserId = superuserId;
	}

	public int getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(int citizenId) {
		this.citizenId = citizenId;
	}
	
}
