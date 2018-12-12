package pt.ulisboa.tecnico.sirs.api.dataobjects;

import java.io.IOException;
import java.io.Serializable;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPrivateKey;
import java.io.Serializable;
import java.sql.Timestamp;

import pt.ulisboa.tecnico.sirs.api.utils.KeyUtils;
public class MedicalRecord implements Serializable {
	private static final long serialVersionUID = 1L;
	private int recordId;
	private Timestamp creationDate;
	private String doctorCitizenId;
	private String patientCitizenId;
	private int institutionId;
	private ReportInfo reportInfo;
	private Citizen doctor;
	private Citizen patient;
	private Institution institution;
	
	public MedicalRecord() {
		this.reportInfo = new ReportInfo();
	}
	
	public MedicalRecord(int recordId, String doctorCitizenId, String patientCitizenId, 
			int institutionId, ReportInfo reportInfo) {
		this.creationDate = new Timestamp(System.currentTimeMillis());
		this.creationDate.setNanos(0);
		this.recordId = recordId;
		this.doctorCitizenId = doctorCitizenId;
		this.patientCitizenId = patientCitizenId;
		this.institutionId = institutionId;
		this.reportInfo = reportInfo;
	}

	public MedicalRecord(boolean dataobject) {
		this.reportInfo = new ReportInfo();
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

	public ReportInfo getReportInfo() {
		return reportInfo;
	}

	public void setReportInfo(ReportInfo reportInfo) {
		this.reportInfo = reportInfo;
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

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
	
	public void setHeartBeat(int heartBeat) {
		this.reportInfo.setHeartBeat(heartBeat);
	}
	
	public void setBloodPressure(int bloodPressure) {
		this.reportInfo.setBloodPressure(bloodPressure);
	}
	
	public void setSugar(int sugar) {
		this.reportInfo.setSugar(sugar);
	}
	
	public void setHaemoglobin(int haemoglobin) {
		this.reportInfo.setHaemoglobin(haemoglobin);
	}
	
	public void setTreatment(String treatment) {
		this.reportInfo.setTreatment(treatment);
	}
	
	public void setGeneralReport(String generalReport) {
		this.reportInfo.setGeneralReport(generalReport);
	}
	
	@Override
	public String toString() {
		return "MedicalRecord [creationDate=" + creationDate + ", doctorCitizenId="
				+ doctorCitizenId + ", patientCitizenId=" + patientCitizenId + ", institutionId=" + institutionId
				+ ", reportInfo=" + reportInfo + "]";
	}

	public SignedMedicalRecord getSignedMedicalRecord() throws NoSuchAlgorithmException, InvalidKeyException, 
	IOException, SignatureException, KeyStoreException, CertificateException, UnrecoverableEntryException {
		
		RSAPrivateKey privateKey = (RSAPrivateKey) KeyUtils.getKeyPair(this.doctorCitizenId).getPrivate();
		
		byte[] objBytes = this.toString().getBytes();

		Signature signature = Signature.getInstance("SHA256withRSA");
		signature.initSign(privateKey);
		signature.update(objBytes);
		byte[] digitalSignature = signature.sign();
		
		return new SignedMedicalRecord(this, digitalSignature);
	}
}
