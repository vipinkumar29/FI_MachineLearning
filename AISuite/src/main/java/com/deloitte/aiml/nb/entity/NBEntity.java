package com.deloitte.aiml.nb.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * 
 * @author soumsarkar
 *
 */
@Document (collection = "NAIVE_BAYES_PREDICTION")
public class NBEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2494535608288167558L;
	
	@Id
	private String id;
	private int rowInstance;
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
	
}
