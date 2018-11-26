package pt.ulisboa.tecnico.sirs.domain;

import java.util.Date;

public class Auth {
	private int authId;
	private Date beginDate;
	private Date endDate;
	private int doctorId;
	private int patientId;
	
	public Auth() {}
	
	public Auth(int authId, Date beginDate, Date endDate, int doctorId, int patientId) {
		this.authId = authId;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.doctorId = doctorId;
		this.patientId = patientId;
	}

	public int getAuthId() {
		return authId;
	}
	
	public void setAuthId(int authId) {
		this.authId = authId;
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
