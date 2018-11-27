package pt.ulisboa.tecnico.sirs.domain;

import java.util.Date;

public class Session {
	private int sessionId;
	private int citizenId;
	private Date creationTime;
	private Date endTime;
	
	public Session() {}

	public Session(int sessionId, int citizenId, Date creationTime, Date endTime) {
		super();
		this.sessionId = sessionId;
		this.citizenId = citizenId;
		this.creationTime = creationTime;
		this.endTime = endTime;
	}
	
	public int getSessionId() {
		return sessionId;
	}

	public void setSessionId(int sessionId) {
		this.sessionId = sessionId;
	}

	public int getCitizenId() {
		return citizenId;
	}

	public void setCitizenId(int citizenId) {
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
