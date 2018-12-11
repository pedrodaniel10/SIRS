package pt.ulisboa.tecnico.sirs.api.dataobjects;

import java.io.IOException;
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