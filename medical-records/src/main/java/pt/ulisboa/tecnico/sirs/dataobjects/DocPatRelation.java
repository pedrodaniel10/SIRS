package pt.ulisboa.tecnico.sirs.dataobjects;

import java.util.Date;

public class DocPatRelation {
	private int docPatRelationId;
	private Date beginDate;
	private Date endDate;
	private int doctorId;
	private int patientId;
	private int adminId;
	
	public DocPatRelation() {}
	
	public DocPatRelation(int docPatRelationId, Date beginDate, Date endDate, int doctorId, int patientId, int adminId) {
		this.docPatRelationId = docPatRelationId;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.doctorId = doctorId;
		this.patientId = patientId;
		this.adminId = adminId;
	}

	public int getDocPatRelationId() {
		return docPatRelationId;
	}
	
	public void setDocPatRelationId(int docPatRelationId) {
		this.docPatRelationId = docPatRelationId;
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

	public int getAdminId() {
		return adminId;
	}

	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
}
