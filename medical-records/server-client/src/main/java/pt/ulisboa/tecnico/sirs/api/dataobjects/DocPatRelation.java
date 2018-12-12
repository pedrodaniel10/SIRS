package pt.ulisboa.tecnico.sirs.api.dataobjects;

import java.io.Serializable;
import java.sql.Date;

public class DocPatRelation implements Serializable {
	private int docPatRelationId;
	private Date beginDate;
	private Date endDate;
	private String doctorCitizenId;
	private String patientCitizenId;
	private String adminCitizenId;
	private Citizen doctor;
	private Citizen patient;

	public DocPatRelation() {}

	public DocPatRelation(int docPatRelationId, Date beginDate, Date endDate, String doctorCitizenId,
						  String patientCitizenId, String adminCitizenId) {
		this.docPatRelationId = docPatRelationId;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.doctorCitizenId = doctorCitizenId;
		this.patientCitizenId = patientCitizenId;
		this.adminCitizenId = adminCitizenId;
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

	public String getDoctorCitizenId() {
		return doctorCitizenId;
	}

	public void setDoctorCitizenId(String doctorCitizenId) {
		this.doctorCitizenId = doctorCitizenId;
	}

	public String getPatientCitizenId() {
		return patientCitizenId;
	}

	public void setPatientCitizenId(String patientCitizenId) {
		this.patientCitizenId = patientCitizenId;
	}

	public String getAdminCitizenId() {
		return adminCitizenId;
	}

	public void setAdminCitizenId(String adminCitizenId) {
		this.adminCitizenId = adminCitizenId;
	}

	public Citizen getDoctor() {
		return doctor;
	}

	public void setDoctor(Citizen doctor) {
		this.doctor = doctor;
	}

	public Citizen getPatient() {
		return patient;
	}

	public void setPatient(Citizen patient) {
		this.patient = patient;
	}
}