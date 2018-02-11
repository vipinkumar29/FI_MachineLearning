package com.deloitte.aiml.svm.web.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.collections.IteratorUtils;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.functions.SMO;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

import com.deloitte.aiml.linearreg.entity.LinRegForm;

public class SVMDe {

	
	public static final String TRAINING_DATA_SET_FILENAME="weatherLogTrain.arff";
	public static final String TESTING_DATA_SET_FILENAME="weatherLogTest.arff";
	public static final String PREDICTION_DATA_SET_FILENAME="WeatherPredictLog.arff";
	
	public static void main(String[] args) throws Exception {
		
		/*if (file.isEmpty()) {
			return "File is Empty"; 
        }else{
        	System.out.println("File is getting uploaded Successfully");
        	// Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            FileWriter fileWriter = new FileWriter(new File(file.getOriginalFilename()));
            String str = new String(bytes, "UTF-8");
            fileWriter.append(str).append("\n");
            
            fileWriter.flush();
            fileWriter.close();
        }
		*/
		Instances trainingDataSet = getDataSet(TRAINING_DATA_SET_FILENAME);
		//Instances testingDataSet = getDataSet(TESTING_DATA_SET_FILENAME);
		/** Classifier here is SVM Regression */
		SMO classifier = new SMO();
		/** */
		classifier.buildClassifier(trainingDataSet);
		/**
		 * train the alogorithm with the training data and evaluate the
		 * algorithm with testing data
		 */
		Evaluation eval = new Evaluation(trainingDataSet);
		//eval.evaluateModel(classifier, testingDataSet);
		/** Print the algorithm summary */
		System.out.println("** SVM Evaluation with Datasets **");
		//System.out.println(eval.toSummaryString());
		System.out.print(" the expression for the input data as per alogorithm is ");
		System.out.println(classifier);

		ListIterator<Instance> predicationDataSetList = getDataSet(PREDICTION_DATA_SET_FILENAME).listIterator();
		List<Instance> myList = IteratorUtils.toList(predicationDataSetList);
		List<LinRegForm> linRegFormList = new ArrayList<>();
		//Instance predicationDataSet = getDataSet(PREDICTION_DATA_SET_FILENAME).lastInstance();
		//double value = classifier.classifyInstance(predicationDataSet);
		//System.out.println("Prediction = "+value);
		/** Prediction Output */
		for(int i=0; i<myList.size(); i++){
			LinRegForm linRegForm = new LinRegForm();
			linRegForm.setTemperature(myList.get(i).value(0));
			linRegForm.setPrediction(classifier.classifyInstance(myList.get(i)));
			linRegFormList.add(linRegForm);
			System.out.println(myList.get(i).stringValue(0));
			System.out.println(myList.get(i).stringValue(1));
			System.out.println(myList.get(i).stringValue(2));
			System.out.println(myList.get(i).stringValue(3));
			System.out.println(myList.get(i).stringValue(4));
			System.out.println(classifier.classifyInstance(myList.get(i)));
		}
		
		/*File fileDel = new File(PREDICTION_DATA_SET_FILENAME);
        fileDel.delete();*/
	}
	
	public static Instances getDataSet(String fileName) throws IOException {
		/**
		 * we can set the file i.e., loader.setFile("finename") to load the data
		 */
		int classIdx = 1;
		/** the arffloader to load the arff file */
		ArffLoader loader = new ArffLoader();
		/** load the traing data */
		//loader.setSource(LinearRegressionDemo.class.getResourceAsStream("/" + fileName));
		loader.setSource(new File(fileName));
		/**
		 * we can also set the file like loader3.setFile(new
		 * File("test-confused.arff"));
		 */
		Instances dataSet = loader.getDataSet();
		/** set the index based on the data given in the arff files */
		dataSet.setClassIndex(classIdx);
		return dataSet;
	}

}
