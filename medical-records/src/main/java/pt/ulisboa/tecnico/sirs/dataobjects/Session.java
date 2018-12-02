package pt.ulisboa.tecnico.sirs.dataobjects;

import java.sql.Timestamp;

public class Session {
	private String sessionId;
	private String citizenId;
	private Timestamp creationTime;
	private Timestamp endTime;
	
	public Session() {}

	public Session(String sessionId, String citizenId, Timestamp creationTime, Timestamp endTime) {
		super();
		this.sessionId = sessionId;
		this.citizenId = citizenId;
		this.creationTime = creationTime;
		this.endTime = endTime;
	}
	
	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(String citizenId) {
		this.citizenId = citizenId;
	}

	public Timestamp getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
}
