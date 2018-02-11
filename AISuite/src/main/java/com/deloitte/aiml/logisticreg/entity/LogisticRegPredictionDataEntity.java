package com.deloitte.aiml.logisticreg.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * 
 * @author soumsarkar
 *
 */
@Document (collection = "LOGISTIC_REG_PREDICTION")
public class LogisticRegPredictionDataEntity implements Serializable{
		
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3182774229827910258L;
	
	
	@Id
	private String id;
	public double xAxis;
	public double yAxis1;
	public double yAxis2;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getxAxis() {
		return xAxis;
	}
	public void setxAxis(double xAxis) {
		this.xAxis = xAxis;
	}
	public double getyAxis1() {
		return yAxis1;
	}
	public void setyAxis1(double yAxis1) {
		this.yAxis1 = yAxis1;
	}
	public double getyAxis2() {
		return yAxis2;
	}
	public void setyAxis2(double yAxis2) {
		this.yAxis2 = yAxis2;
	}
	
	
}
