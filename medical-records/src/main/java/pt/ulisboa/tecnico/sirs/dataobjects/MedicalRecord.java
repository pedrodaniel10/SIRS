package pt.ulisboa.tecnico.sirs.dataobjects;

import java.sql.Timestamp;
import java.util.Date;

public class MedicalRecord {
	private int recordId;
	private int heartBeat;
	private int bloodPressure;
	private int sugar;
	private int haemoglobin;
	private Timestamp creationDate;
	private String doctorCitizenId;
	private String treatment;
	private String patientCitizenId;
	private int institutionId;
	private String generalReport;
	private String recordSignature;
	
	public MedicalRecord() {}
	
	public MedicalRecord(int recordId, int heartBeat, int bloodPressure, int sugar, int haemoglobin, Timestamp creationDate,
			String doctorCitizenId, String treatment, String patientCitizenId, int institutionId, String generalReport,
			String recordSignature) {
		super();
		this.recordId = recordId;
		this.heartBeat = heartBeat;
		this.bloodPressure = bloodPressure;
		this.sugar = sugar;
		this.haemoglobin = haemoglobin;
		this.creationDate = creationDate;
		this.doctorCitizenId = doctorCitizenId;
		this.treatment = treatment;
		this.patientCitizenId = patientCitizenId;
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
	
	public Timestamp getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}
	
	public String getDoctorCitizenId() {
		return doctorCitizenId;
	}

	public void setDoctorCitizenId(String doctorCitizenId) {
		this.doctorCitizenId = doctorCitizenId;
	}
	
	public String getTreatment() {
		return treatment;
	}
	
	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}
	
	public String getPatientCitizenId() {
		return patientCitizenId;
	}

	public void setPatientCitizenId(String patientCitizenId) {
		this.patientCitizenId = patientCitizenId;
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
