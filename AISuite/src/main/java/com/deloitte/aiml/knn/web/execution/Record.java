package com.deloitte.aiml.knn.web.execution;

//Basic Record class
public class Record {
	double[] attributes;
	public int classLabel;
	
	Record(double[] attributes, int classLabel){
		this.attributes = attributes;
		this.classLabel = classLabel;
	}
}
