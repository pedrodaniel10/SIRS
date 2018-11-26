package pt.ulisboa.tecnico.sirs.domain;

import java.util.Date;

public class Authorization {
	private int authorizationId;
	private Date beginDate;
	private Date endDate;
	private int doctorId;
	private int patientId;
	
	public Authorization() {}
	
	public Authorization(int authId, Date beginDate, Date endDate, int doctorId, int patientId) {
		this.authorizationId = authId;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.doctorId = doctorId;
		this.patientId = patientId;
	}

	public int getAuthId() {
		return authorizationId;
	}
	
	public void setAuthId(int authId) {
		this.authorizationId = authId;
	}
	
	public Date getBeginDate() {
		return beginDate;
	}
	
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public int getDoctorId() {
		return doctorId;
	}
	
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	
	public int getPatientId() {
		return patientId;
	}
	
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
}
