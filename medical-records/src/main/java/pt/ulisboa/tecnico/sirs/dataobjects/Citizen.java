package pt.ulisboa.tecnico.sirs.dataobjects;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Citizen {
	
	public enum Gender {
		MALE, FEMALE
	}
	
	public enum Role {
		PATIENT, DOCTOR, ADMIN, SUPERUSER
	}
	
	private String citizenId;
	private String citizenName;
	private Gender gender;
	private Date dateOfBirth;
	private String email;
	private String profilePic;
	private int superuserId;
	private List<Role> roles = new ArrayList<>();
	
	public Citizen() {}
	
	public Citizen(String citizenId, String citizenName, Gender gender, Date dateOfBirth, String email,
			String profilePic, int superuserId, List<Role> roles) {
		this.citizenId = citizenId;
		this.citizenName = citizenName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.profilePic = profilePic;
		this.superuserId = superuserId;
		this.roles = roles;
	}

	public String getCitizenId() {
		return citizenId;
	}
	
	public void setCitizenId(String citizenId) {
		this.citizenId = citizenId;
	}
	
	public String getCitizenName() {
		return citizenName;
	}
	
	public void setCitizenName(String citizenName) {
		this.citizenName = citizenName;
	}
	
	public Gender getGender() {
		return gender;
	}
	
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	public void addRole(Role role) {
		this.roles.add(role);
	}
}
