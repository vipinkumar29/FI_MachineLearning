package com.deloitte.aiml.knn.web.execution;

//basic metric interface

public interface Metric {
	double getDistance(Record s, Record e);
}
