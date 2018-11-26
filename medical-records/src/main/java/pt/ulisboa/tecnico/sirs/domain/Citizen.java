package pt.ulisboa.tecnico.sirs.domain;

import java.util.Date;

public class Citizen {
	private int citizenId;
	private String citizenName;
	private String gender;
	private String occupation;
	private Date dateOfBirth;
	private String email;
	private String homeAddress;
	
	public Citizen() {}
	
	public Citizen(int citizenId, String citizenName, String gender, String occupation, Date dateOfBirth, String email,
			String homeAddress) {
		this.citizenId = citizenId;
		this.citizenName = citizenName;
		this.gender = gender;
		this.occupation = occupation;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.homeAddress = homeAddress;
	}

	public int getCitizenId() {
		return citizenId;
	}
	
	public void setCitizenId(int citizenId) {
		this.citizenId = citizenId;
	}
	
	public String getCitizenName() {
		return citizenName;
	}
	
	public void setCitizenName(String citizenName) {
		this.citizenName = citizenName;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getOccupation() {
		return occupation;
	}
	
	public void setOccupation(String occupation) {
		this.occupation = occupation;
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
	
	public String getHomeAddress() {
		return homeAddress;
	}
	
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
}
