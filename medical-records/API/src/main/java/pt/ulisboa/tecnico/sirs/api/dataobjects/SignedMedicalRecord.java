package pt.ulisboa.tecnico.sirs.api.dataobjects;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.UnrecoverableEntryException;
import java.security.cert.CertificateException;
import java.security.interfaces.RSAPublicKey;

import pt.ulisboa.tecnico.sirs.api.utils.KeyUtils;

public class SignedMedicalRecord {
	private MedicalRecord medicalRecord;
	private byte[] recordSignature;
	
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
	
	public boolean verifySignature() throws InvalidKeyException, SignatureException, NoSuchAlgorithmException, 
	IOException, KeyStoreException, CertificateException, UnrecoverableEntryException {
		
		RSAPublicKey publicKey = (RSAPublicKey) KeyUtils.getKeyPair(this.medicalRecord.getDoctorCitizenId()).getPublic();
		
		byte[] objBytes = convertToByteArray(this.medicalRecord);
		byte[] sigBytes = this.recordSignature;
		
		System.out.println("Object array len: " + objBytes.length);
		System.out.println("Signature array len: " + sigBytes.length);
		
		Signature signature = Signature.getInstance("SHA256withRSA");
		signature.initVerify(publicKey);
		signature.update(objBytes);

		return signature.verify(sigBytes);
	}

	private byte[] convertToByteArray(Object obj) throws IOException {
		byte[] objBytes;
		try(ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream()){
            try(ObjectOutputStream objectOutStream = new ObjectOutputStream(byteOutStream)){
            	objectOutStream.writeObject(obj);
            }
            objBytes = byteOutStream.toByteArray();
        }
		return objBytes;
	}
	
	
}
