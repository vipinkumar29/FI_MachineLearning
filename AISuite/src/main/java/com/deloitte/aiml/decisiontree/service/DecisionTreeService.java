package com.deloitte.aiml.decisiontree.service;

import java.util.List;

import com.deloitte.aiml.decisiontree.DecisionTree;
import com.deloitte.aiml.decisiontree.Node;
import com.deloitte.aiml.decisiontree.data.DataSample;



/**
 * 
 * @author soumsarkar
 *
 */
public interface DecisionTreeService {
	
	public String savePrediction(List<DataSample> testingData, DecisionTree tree);
	public String saveDecisionTree(Node tree);
	
}
