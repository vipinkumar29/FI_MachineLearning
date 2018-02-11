package com.deloitte.aiml.rf.data;

import java.util.Iterator;

class DataTableIterator implements Iterator<int[]> {
	protected final DataTable table;
	protected int i = 0; // next element to return

	@Override
	public boolean hasNext() { return i<table.size(); }

	@Override
	public int[] next() { return table.getRow(i++); }

	public DataTableIterator(DataTable table) {this.table = table;	}
}
