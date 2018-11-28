package pt.ulisboa.tecnico.sirs.dataobjects;

import java.util.Date;

public class MedicalRecord {
	private int recordId;
	private int heartBeat;
	private int bloodPressure;
	private int sugar;
	private int haemoglobin;
	private Date creationDate;
	private int doctorId;
	private String treatment;
	private int patientId;
	private int institutionId;
	private String generalReport;
	private String recordSignature;
	
	public MedicalRecord() {}
	
	public MedicalRecord(int recordId, int heartBeat, int bloodPressure, int sugar, int haemoglobin, Date creationDate,
			int doctorId, String treatment, int patientId, int institutionId, String generalReport,
			String recordSignature) {
		this.recordId = recordId;
		this.heartBeat = heartBeat;
		this.bloodPressure = bloodPressure;
		this.sugar = sugar;
		this.haemoglobin = haemoglobin;
		this.creationDate = creationDate;
		this.doctorId = doctorId;
		this.treatment = treatment;
		this.patientId = patientId;
		this.institutionId = institutionId;
		this.generalReport = generalReport;
		this.recordSignature = recordSignature;
	}
	
	public int getRecordId() {
		return recordId;
	}
	
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}
	
	public int getHeartBeat() {
		return heartBeat;
	}
	
	public void setHeartBeat(int heartBeat) {
		this.heartBeat = heartBeat;
	}
	
	public int getBloodPressure() {
		return bloodPressure;
	}
	
	public void setBloodPressure(int bloodPressure) {
		this.bloodPressure = bloodPressure;
	}
	
	public int getSugar() {
		return sugar;
	}
	
	public void setSugar(int sugar) {
		this.sugar = sugar;
	}
	
	public int getHaemoglobin() {
		return haemoglobin;
	}
	
	public void setHaemoglobin(int haemoglobin) {
		this.haemoglobin = haemoglobin;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public int getDoctorId() {
		return doctorId;
	}
	
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	
	public String getTreatment() {
		return treatment;
	}
	
	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}
	
	public int getPatientId() {
		return patientId;
	}
	
	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}
	
	public int getInstitutionId() {
		return institutionId;
	}
	
	public void setInstitutionId(int institutionId) {
		this.institutionId = institutionId;
	}
	
	public String getGeneralReport() {
		return generalReport;
	}
	
	public void setGeneralReport(String generalReport) {
		this.generalReport = generalReport;
	}
	
	public String getRecordSignature() {
		return recordSignature;
	}
	
	public void setRecordSignature(String recordSignature) {
		this.recordSignature = recordSignature;
	}
}
