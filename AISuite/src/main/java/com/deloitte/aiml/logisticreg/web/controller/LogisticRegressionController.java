package com.deloitte.aiml.logisticreg.web.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.collections.IteratorUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.deloitte.aiml.logisticreg.entity.LogRegForm;
import com.deloitte.aiml.nb.form.NBForm;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

@RestController
@RequestMapping(value="/aiSuit")
public class LogisticRegressionController {
	
	/** file names are defined*/
	public static final String TRAINING_DATA_SET_FILENAME="weatherLogTrain.arff"; //"C:\\Users\\soumsarkar\\Documents\\New folder\\readTxt\\weatherLogTrain.arff";
	public static final String TESTING_DATA_SET_FILENAME="weatherLogTest.arff"; //C:\\Users\\soumsarkar\\Documents\\New folder\\readTxt\\weatherLogTest.arff";
	public static final String PREDICTION_DATA_SET_FILENAME="WeatherPredictLog.arff"; //"C:\\Users\\soumsarkar\\Documents\\New folder\\readTxt\\WeatherPredictLog.arff";
	

	/**
	 * This method is used to process the input and return the statistics.
	 * 
	 * @throws Exception
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/callLogReg", method = RequestMethod.POST)
	@ResponseBody
    public Object getSaveLogisticPrediction(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) throws Exception {
		
		
		if (file.isEmpty()) {
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
		
		Instances trainingDataSet = getDataSet(TRAINING_DATA_SET_FILENAME);
		//Instances testingDataSet = getDataSet(TESTING_DATA_SET_FILENAME);
		/** Classifier here is Logical Regression */
		Classifier classifier = new weka.classifiers.functions.Logistic();
		/** */
		classifier.buildClassifier(trainingDataSet);
		/**
		 * train the alogorithm with the training data and evaluate the
		 * algorithm with testing data
		 */
		Evaluation eval = new Evaluation(trainingDataSet);
		//eval.evaluateModel(classifier, testingDataSet);
		/** Print the algorithm summary */
		System.out.println("** Linear Regression Evaluation with Datasets **");
		System.out.println(eval.toSummaryString());
		System.out.print(" the expression for the input data as per alogorithm is ");
		System.out.println(classifier);

		ListIterator<Instance> predicationDataSetList = getDataSet(file.getOriginalFilename()).listIterator();
		List<Instance> myList = IteratorUtils.toList(predicationDataSetList);
		List<LogRegForm> logRegFormList = new ArrayList<>();
		for(int i=0; i<myList.size(); i++){
			LogRegForm logRegForm = new LogRegForm();
			Instance predicationDataSet = getDataSet(PREDICTION_DATA_SET_FILENAME).instance(i);
			double value = classifier.classifyInstance(predicationDataSet);
			//String outlook = classifier.
			/** Prediction Output */
			logRegForm.setOutlook(myList.get(i).stringValue(0));
			logRegForm.setTemperature(myList.get(i).stringValue(1));
			logRegForm.setHumidity(myList.get(i).stringValue(2));
			logRegForm.setWindy(myList.get(i).stringValue(3));
			logRegForm.setPrediction(myList.get(i).stringValue(4));
			logRegFormList.add(logRegForm);

			//System.out.println(value);
		}
		/*Instance predicationDataSet = getDataSet(PREDICTION_DATA_SET_FILENAME).lastInstance();
		double value = classifier.classifyInstance(predicationDataSet);
		*//** Prediction Output *//*
		System.out.println(value);*/
		 
		File fileDel = new File(file.getOriginalFilename());
        fileDel.delete();
		 
		 return logRegFormList;
	}
	
	/**
	 * This method is to load the data set.
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static Instances getDataSet(String fileName) throws IOException {
		/**
		 * we can set the file i.e., loader.setFile("finename") to load the data
		 */
		int classIdx = 1;
		/** the arffloader to load the arff file */
		ArffLoader loader = new ArffLoader();
		//loader.setFile(new File(fileName));
		/** load the traing data */
//		loader.setSource(LogisticRegressionDemo.class.getResourceAsStream("/" + fileName));
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
