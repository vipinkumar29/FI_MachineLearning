package com.deloitte.aiml.decisiontree.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.deloitte.aiml.decisiontree.Node;


/**
 * 
 * @author soumsarkar
 *
 */
@Document (collection = "DECISION_TREE")
public class DecisionTreeEntity  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3113230625295863652L;
	
	
	@Id
	private String id;
	private Node decisionTree;

	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Node getDecisionTree() {
		return decisionTree;
	}

	public void setDecisionTree(Node decisionTree) {
		this.decisionTree = decisionTree;
	}
	
}
