package com.deloitte.aiml.decisiontree.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * 
 * @author soumsarkar
 *
 */
@Document (collection = "DECISION_TREE_PREDICTION")
public class DTPredictionDataEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4493193404674586836L;
	
	
	@Id
	private String id;
	/*public String OrganizationId;
	public String SalesResult;*/
	public String age;
	public String job;
	public String y;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	/*public String getPassengerId() {
		return PassengerId;
	}
	public void setPassengerId(String passengerId) {
		PassengerId = passengerId;
	}
	public String getSurvived() {
		return Survived;
	}
	public void setSurvived(String survived) {
		Survived = survived;
	}*/
	/*public String getOrganizationId() {
		return OrganizationId;
	}
	public void setOrganizationId(String organizationId) {
		OrganizationId = organizationId;
	}
	public String getSalesResult() {
		return SalesResult;
	}
	public void setSalesResult(String salesResult) {
		SalesResult = salesResult;
	}*/
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getY() {
		return y;
	}
	public void setY(String y) {
		this.y = y;
	}
	
}
