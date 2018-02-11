package com.deloitte.aiml.nb.form;

/**
 * 
 * @author soumsarkar
 *
 */
public class NBForm {
	
	private String id;
	private int rowInstance;
	private String outlook;
	private Double temperature;
	private Double humidity;
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
	public String getPrediction() {
		return prediction;
	}
	public void setPrediction(String prediction) {
		this.prediction = prediction;
	}
	public String getOutlook() {
		return outlook;
	}
	public void setOutlook(String outlook) {
		this.outlook = outlook;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	public Double getHumidity() {
		return humidity;
	}
	public void setHumidity(Double humidity) {
		this.humidity = humidity;
	}
	public String getWindy() {
		return windy;
	}
	public void setWindy(String windy) {
		this.windy = windy;
	}
}
