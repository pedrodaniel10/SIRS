package pt.ulisboa.tecnico.sirs.dataobjects;

import java.util.Date;

public class MedicalRecord {
	private int recordId;
	private Date creationDate;
	private int doctorId;
	private int patientId;
	private int institutionId;
	private String recordSignature;
	private ReportInfo reportInfo;
	
	public MedicalRecord() {}
	
	public MedicalRecord(int recordId, Date creationDate, int doctorId, int patientId, int institutionId, 
			String recordSignature, ReportInfo reportInfo) {
		this.recordId = recordId;
		this.creationDate = creationDate;
		this.doctorId = doctorId;
		this.patientId = patientId;
		this.institutionId = institutionId;
		this.recordSignature = recordSignature;
		this.reportInfo = reportInfo;
	}
	
	public int getRecordId() {
		return recordId;
	}
	
	public void setRecordId(int recordId) {
		this.recordId = recordId;
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
	
	public String getRecordSignature() {
		return recordSignature;
	}
	
	public void setRecordSignature(String recordSignature) {
		this.recordSignature = recordSignature;
	}

	public ReportInfo getReportInfo() {
		return reportInfo;
	}

	public void setReportInfo(ReportInfo reportInfo) {
		this.reportInfo = reportInfo;
	}
}
