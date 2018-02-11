package com.deloitte.aiml.knn.web.controller;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Enumeration;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.lazy.IBk;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ConverterUtils;


/*THIS IS THE ANOTHER APPROACH FOR KNN,
IN THIS WE NEED TO FIND A SOLUTION TO FIND SEPCIFIC PREDICTION*/

public class KNNDemo {
	
	/**
	 * This method is to load the data set.
	 * @param fileName
	 * @return
	 * @throws Exception 
	 */
	public static Instances getDataSet(String fileName) throws Exception {
		/**
		 * we can set the file i.e., loader.setFile("finename") to load the data
		 */
		int classIdx = 1;
		/** the arffloader to load the arff file */
		ArffLoader loader = new ArffLoader();
		/** load the traing data */
//		loader.setSource(KNNDemo.class.getResourceAsStream("/" + fileName));
//		ConverterUtils.DataSource source = new ConverterUtils.DataSource(fileName);
		loader.setSource(new File(fileName));
		/**
		 * we can also set the file like loader3.setFile(new
		 * File("test-confused.arff"));
		 */
		//loader.setFile(new File(fileName));
		Instances dataSet = loader.getDataSet();
//		Instances dataSet = source.getDataSet();
		/** set the index based on the data given in the arff files */
		dataSet.setClassIndex(classIdx);
		return dataSet;
	}


	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		
		 
		Instances data = getDataSet("C:\\Users\\soumsarkar\\Documents\\New folder\\readTxt\\pproductTrain.arff");
		data.setClassIndex(data.numAttributes() - 1);
		//k - the number of nearest neighbors to use for prediction
		Classifier ibk = new IBk(2);		
		ibk.buildClassifier(data);
		double label1 = ibk.classifyInstance(data.lastInstance());
		data.lastInstance().setClassValue(label1);
		System.out.println("Prdeiction:=> "+data.lastInstance().stringValue(3));
	
		System.out.println(ibk);
       
        Evaluation eval = new Evaluation(data);
        eval.evaluateModel(ibk, data);
		/** Print the algorithm summary */
		System.out.println("** KNN Demo  **");
		System.out.println(eval.toSummaryString());
	    System.out.println(eval.toClassDetailsString());
		System.out.println(eval.toMatrixString());
		
	}

}
