package com.deloitte.aiml.knn.web.controller;

public class KNNForm {
	
	private Number customerId;
	private Number age;
	private Number income;
	private String product;
	
	public Number getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Number customerId) {
		this.customerId = customerId;
	}
	public Number getAge() {
		return age;
	}
	public void setAge(Number age) {
		this.age = age;
	}
	public Number getIncome() {
		return income;
	}
	public void setIncome(Number income) {
		this.income = income;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
}
