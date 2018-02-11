package com.deloitte.aiml.logisticreg.entity;

public class LogRegForm {

	private String id;
	private int rowInstance;
	private String outlook;
	private String temperature;
	private String humidity;
	private String windy;
	private String prediction;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getRowInstance() {
		return rowInstance;
	}
	public void setRowInstance(int rowInstance) {
		this.rowInstance = rowInstance;
	}
	public String getOutlook() {
		return outlook;
	}
	public void setOutlook(String outlook) {
		this.outlook = outlook;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getWindy() {
		return windy;
	}
	public void setWindy(String windy) {
		this.windy = windy;
	}
	public String getPrediction() {
		return prediction;
	}
	public void setPrediction(String prediction) {
		this.prediction = prediction;
	}
	
}
