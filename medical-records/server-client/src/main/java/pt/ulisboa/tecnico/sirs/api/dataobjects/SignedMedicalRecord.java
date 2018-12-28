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
import java.security.interfaces.RSAPublicKey;
import java.sql.Timestamp;

import pt.ulisboa.tecnico.sirs.api.utils.KeyUtils;

public class SignedMedicalRecord implements Serializable {
	private MedicalRecord medicalRecord;
	private byte[] recordSignature;
	private boolean isVerified;
	
	public SignedMedicalRecord() {
		this.medicalRecord = new MedicalRecord();
	}
	
	public SignedMedicalRecord(MedicalRecord medicalRecord, byte[] recordSignature) {
		this.medicalRecord = medicalRecord;
		this.recordSignature = recordSignature;
	}

	public MedicalRecord getMedicalRecord() {
		return medicalRecord;
	}
	
	public void setMedicalRecord(MedicalRecord medicalRecord) {
		this.medicalRecord = medicalRecord;
	}
	
	public byte[] getRecordSignature() {
		return recordSignature;
	}
	
	public void setRecordSignature(byte[] recordSignature) {
		this.recordSignature = recordSignature;
	}
	
	public void setRecordId(int recordId) {
		this.medicalRecord.setRecordId(recordId);
	}
	
	public void setCreationDate(Timestamp creationDate) {
		this.medicalRecord.setCreationDate(creationDate);
	}
	
	public void setDoctorCitizenId(String doctorCitizenId) {
		this.medicalRecord.setDoctorCitizenId(doctorCitizenId);
	}

	public void setPatientCitizenId(String patientCitizenId) {
		this.medicalRecord.setPatientCitizenId(patientCitizenId);
	}
	
	public void setInstitutionId(int institutionId) {
		this.medicalRecord.setInstitutionId(institutionId);
	}

	public void setReportInfo(ReportInfo reportInfo) {
		this.medicalRecord.setReportInfo(reportInfo);
	}
	
	public void setDoctor(Citizen doctor) {
		this.medicalRecord.setDoctor(doctor);
	}

	public void setPatient(Citizen patient) {
		this.medicalRecord.setPatient(patient);
	}

	public void setInstitution(Institution institution) {
		this.medicalRecord.setInstitution(institution);
	}
	
	public void setHeartBeat(int heartBeat) {
		this.medicalRecord.setHeartBeat(heartBeat);
	}
	
	public void setBloodPressure(int bloodPressure) {
		this.medicalRecord.setBloodPressure(bloodPressure);
	}
	
	public void setSugar(int sugar) {
		this.medicalRecord.setSugar(sugar);
	}
	
	public void setHaemoglobin(int haemoglobin) {
		this.medicalRecord.setHaemoglobin(haemoglobin);
	}
	
	public void setTreatment(String treatment) {
		this.medicalRecord.setTreatment(treatment);
	}
	
	public void setGeneralReport(String generalReport) {
		this.medicalRecord.setGeneralReport(generalReport);
	}
	
	public void verifySignature() throws InvalidKeyException, SignatureException, NoSuchAlgorithmException, 
	IOException, KeyStoreException, CertificateException, UnrecoverableEntryException {
		
		RSAPublicKey publicKey = (RSAPublicKey) KeyUtils.getKeyPair(this.medicalRecord.getDoctorCitizenId()).getPublic();
		
		byte[] objBytes = this.getMedicalRecord().toString().getBytes();
		byte[] sigBytes = this.recordSignature;

		Signature signature = Signature.getInstance("SHA256withRSA");
		signature.initVerify(publicKey);
		signature.update(objBytes);

		this.isVerified = signature.verify(sigBytes);
	}
	

	public boolean isVerified() {
		return isVerified;
	}
	
}
