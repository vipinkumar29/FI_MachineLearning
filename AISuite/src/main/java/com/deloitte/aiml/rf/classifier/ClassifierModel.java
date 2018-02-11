package com.deloitte.aiml.rf.classifier;

import com.deloitte.aiml.rf.data.DataTable;


public interface ClassifierModel extends Classifier {
	/** A classifier object's constructor identifies the model parameters.
	 *  In an effort not to store datasets in each model, we use a separate
	 *  train() method to actually fit the model to the data.
	 *
	 *  Repeatedly calling this method, clears the model before training
	 *  each time.
	 */
	void train(DataTable data);
}
