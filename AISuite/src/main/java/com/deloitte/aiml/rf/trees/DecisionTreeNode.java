package com.deloitte.aiml.rf.trees;

import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import com.deloitte.aiml.rf.classifier.Classifier;

public abstract class DecisionTreeNode implements Classifier {
	// for debugging, fields below
	protected int numRecords;
	protected float entropy;

	public JsonObject toJSON() { return toJSON(this); }

	public abstract JsonObjectBuilder getJSONData();

	public static JsonObject toJSON(DecisionTreeNode t) {
		JsonObjectBuilder builder = t.getJSONData();
		if ( t instanceof DecisionSplitNode ) {
			DecisionSplitNode s = (DecisionSplitNode)t;
			builder.add("left", s.left.toJSON());
			builder.add("right", s.right.toJSON());
		}
		return builder.build();
	}

	public abstract String getDOTNodeDef();
}
