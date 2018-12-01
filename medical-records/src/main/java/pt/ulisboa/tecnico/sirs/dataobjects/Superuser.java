package pt.ulisboa.tecnico.sirs.dataobjects;

public class Superuser {
	private int superuserId;
	private String citizenId;
	private String superuserCitizenId;

	public Superuser() {}

	public Superuser(int superuserId, String citizenId, String superuserCitizenId) {
		super();
		this.superuserId = superuserId;
		this.citizenId = citizenId;
		this.superuserCitizenId = superuserCitizenId;
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

	public String getSuperuserCitizenId() {
		return superuserCitizenId;
	}

	public void setSuperuserCitizenId(String superuserCitizenId) {
		this.superuserCitizenId = superuserCitizenId;
	}
	
}
