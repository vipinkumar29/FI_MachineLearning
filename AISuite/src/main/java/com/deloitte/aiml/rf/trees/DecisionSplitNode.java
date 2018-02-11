package com.deloitte.aiml.rf.trees;

import com.deloitte.aiml.rf.data.DataTable;

public abstract class DecisionSplitNode extends DecisionTreeNode {
	/** This node is split on which variable? */
	protected int splitVariable;
	protected DataTable.VariableType colType;

	protected DecisionTreeNode left;
	protected DecisionTreeNode right;

	public DecisionSplitNode(int splitVariable, DataTable.VariableType colType) {
		this.splitVariable = splitVariable;
		this.colType = colType;
	}

	public abstract String getDOTLeftEdge();
	public abstract String getDOTRightEdge();
}
