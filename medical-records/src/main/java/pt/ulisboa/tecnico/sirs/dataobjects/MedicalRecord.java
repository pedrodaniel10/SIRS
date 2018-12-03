package pt.ulisboa.tecnico.sirs.dataobjects;

import java.sql.Timestamp;

public class MedicalRecord {
	private int recordId;
	private Timestamp creationDate;
	private String doctorCitizenId;
	private String patientCitizenId;
	private int institutionId;
	private String recordSignature;
	private ReportInfo reportInfo;
	
	public MedicalRecord() {}
	
	public MedicalRecord(int recordId, Timestamp creationDate, String doctorCitizenId, String patientCitizenId, int institutionId, 
			String recordSignature, ReportInfo reportInfo) {
		this.recordId = recordId;
		this.creationDate = creationDate;
		this.doctorCitizenId = doctorCitizenId;
		this.patientCitizenId = patientCitizenId;
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
