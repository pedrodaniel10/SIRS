package pt.ulisboa.tecnico.sirs.api.dataobjects;

import java.io.Serializable;

public class ReportInfo implements Serializable {
	private static final long serialVersionUID = 1L;
	private int heartBeat;
	private int bloodPressure;
	private int sugar;
	private int haemoglobin;
	private String treatment;
	private String generalReport;
	
	public ReportInfo() {}
	
	public ReportInfo(int heartBeat, int bloodPressure, int sugar, int haemoglobin, String treatment,
			String generalReport) {
		this.heartBeat = verifyRecordData(heartBeat);
		this.bloodPressure = verifyRecordData(bloodPressure);
		this.sugar = verifyRecordData(sugar);
		this.haemoglobin = verifyRecordData(haemoglobin);
		this.treatment = treatment;
		this.generalReport = generalReport;
	}
	
	private int verifyRecordData(int recordData) {
		if (recordData <= 200 && recordData > 0) {
			return recordData;
		} else {
			return 0;
		}
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
	
	public String getTreatment() {
		return treatment;
	}
	
	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}
	
	public String getGeneralReport() {
		return generalReport;
	}
	
	public void setGeneralReport(String generalReport) {
		this.generalReport = generalReport;
	}
	
	@Override
	public String toString() {
		return "ReportInfo [heartBeat=" + heartBeat + ", bloodPressure=" + bloodPressure + ", sugar=" + sugar
				+ ", haemoglobin=" + haemoglobin + ", treatment=" + treatment + ", generalReport=" + generalReport
				+ "]";
	}
	
}