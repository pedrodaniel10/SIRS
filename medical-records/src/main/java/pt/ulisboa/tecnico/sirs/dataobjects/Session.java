package pt.ulisboa.tecnico.sirs.dataobjects;

import java.util.Date;

public class Session {
	private String sessionId;
	private String citizenId;
	private Date creationTime;
	private Date endTime;
	
	public Session() {}

	public Session(String sessionId, String citizenId, Date creationTime, Date endTime) {
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

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
