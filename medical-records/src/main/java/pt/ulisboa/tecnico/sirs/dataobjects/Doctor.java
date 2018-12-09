package pt.ulisboa.tecnico.sirs.dataobjects;

public class Doctor {
	private int doctorId;
	private String citizenId;
	private int institutionId;
	private String superuserCitizenId;
	private String adminCitizenId; //admin that set the institution
	
	public Doctor(int doctorId, String citizenId, int institutionId, String superuserCitizenId, String adminCitizenId) {
		super();
		this.doctorId = doctorId;
		this.citizenId = citizenId;
		this.institutionId = institutionId;
		this.superuserCitizenId = superuserCitizenId;
		this.adminCitizenId = adminCitizenId;
	}

	public Doctor() {}

	public int getDoctorId() {
		return doctorId;
	}
	
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
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

	public String getAdminCitizenId() {
		return adminCitizenId;
	}

	public void setAdminCitizenId(String adminCitizenId) {
		this.adminCitizenId = adminCitizenId;
	}
}
