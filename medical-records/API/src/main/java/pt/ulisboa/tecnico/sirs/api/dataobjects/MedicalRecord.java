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
		
		System.out.println("Before: " + this.toString());
		
		Signature signature = Signature.getInstance("SHA256withRSA");
		signature.initSign(privateKey);
		signature.update(objBytes);
		byte[] digitalSignature = signature.sign();
		
		return new SignedMedicalRecord(this, digitalSignature);
	}
}
