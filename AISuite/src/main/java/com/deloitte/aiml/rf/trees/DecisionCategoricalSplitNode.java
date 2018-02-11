package com.deloitte.aiml.rf.trees;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

import com.deloitte.aiml.rf.data.DataTable;

import java.util.Map;

import static us.parr.lib.ParrtMath.isClose;


public class DecisionCategoricalSplitNode extends DecisionSplitNode {
	/** Split according to what variable category? An unknown matches category yes or no */
	protected int splitCategory;

	protected Object splitCategoryDisplayValue;

	protected String variableName;

	public DecisionCategoricalSplitNode(DataTable data, int splitVariable, DataTable.VariableType colType, int splitCategory) {
		super(splitVariable, colType);
		this.splitCategory = splitCategory;
		this.splitCategoryDisplayValue = DataTable.getValue(data, splitCategory, splitVariable);
		this.variableName = data.getColNames()[splitVariable];
	}

	public int classify(int[] X) {
		if ( X[splitVariable]==splitCategory ) { // if equal, choose left child
			return left.classify(X);
		}
		else {
			return right.classify(X);
		}
	}

	@Override
	public Map<Integer, Double> classProbabilities(int[] X) {
		if ( X[splitVariable]==splitCategory ) { // if equal, choose left child
			return left.classProbabilities(X);
		}
		else {
			return right.classProbabilities(X);
		}
	}

	@Override
	public JsonObjectBuilder getJSONData() {
		JsonObjectBuilder builder =  Json.createObjectBuilder();
		builder.add("var", variableName);
		builder.add("cat", splitCategoryDisplayValue.toString()); // has to be categorical
		builder.add("n", numRecords);
		if ( !isClose(entropy,0.0) ) {
			builder.add("E", String.format("%.2f",entropy));
		}
		return builder;
	}

	@Override
	public String getDOTLeftEdge() {
		int id = System.identityHashCode(this);
		return String.format("n%s -> n%s [label=\"%s\"];", id, System.identityHashCode(left), splitCategoryDisplayValue.toString());
	}

	@Override
	public String getDOTRightEdge() {
		int id = System.identityHashCode(this);
		return String.format("n%s -> n%s [label=\"!%s\"];", id, System.identityHashCode(right), splitCategoryDisplayValue.toString());
	}

	@Override
	public String getDOTNodeDef() {
		int id = System.identityHashCode(this);
		return String.format("n%d [label=\"%s\\nn=%d\\nE=%.2f\"];",
		                     id, variableName, numRecords, entropy);
	}
}
