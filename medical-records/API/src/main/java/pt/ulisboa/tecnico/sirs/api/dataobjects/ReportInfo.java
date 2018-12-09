package pt.ulisboa.tecnico.sirs.api.dataobjects;

public class ReportInfo {
	private int heartBeat;
	private int bloodPressure;
	private int sugar;
	private int haemoglobin;
	private String treatment;
	private String generalReport;
	
	public ReportInfo() {}
	
	public ReportInfo(int heartBeat, int bloodPressure, int sugar, int haemoglobin, String treatment,
			String generalReport) {
		this.heartBeat = heartBeat;
		this.bloodPressure = bloodPressure;
		this.sugar = sugar;
		this.haemoglobin = haemoglobin;
		this.treatment = treatment;
		this.generalReport = generalReport;
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
}