package com.deloitte.aiml.linearreg.web.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.IteratorUtils;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.deloitte.aiml.linearreg.entity.LinRegForm;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

@RestController
@RequestMapping(value="/aiSuit")
public class LinearRegressionController {
	
	/*@ModelAttribute("loginForm")LoginForm loginForm,
	HttpServletRequest request,HttpServletResponse response,
	HttpSession session,RedirectAttributes redirectAttributes,ModelMap modelMap*/
	
	/** file names are defined*/
	public static final String TRAINING_DATA_SET_FILENAME="linear-train.arff";
	public static final String TESTING_DATA_SET_FILENAME="linear-test.arff";
	public static final String PREDICTION_DATA_SET_FILENAME="test-confused.arff";
	
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

	/**
	 * This method is used to process the input and return the statistics.
	 * 
	 * @throws Exception
	 */
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/callLinReg", method = RequestMethod.POST)
	@ResponseBody
	public Object getSaveLinRegPrediction(@RequestParam("file") MultipartFile file,
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
		Instances testingDataSet = getDataSet(TESTING_DATA_SET_FILENAME);
		/** Classifier here is Linear Regression */
		Classifier classifier = new weka.classifiers.functions.LinearRegression();
		/** */
		classifier.buildClassifier(trainingDataSet);
		/**
		 * train the alogorithm with the training data and evaluate the
		 * algorithm with testing data
		 */
		Evaluation eval = new Evaluation(trainingDataSet);
		eval.evaluateModel(classifier, testingDataSet);
		/** Print the algorithm summary */
		System.out.println("** Linear Regression Evaluation with Datasets **");
		System.out.println(eval.toSummaryString());
		System.out.print(" the expression for the input data as per alogorithm is ");
		System.out.println(classifier);

		ListIterator<Instance> predicationDataSetList = getDataSet(file.getOriginalFilename()).listIterator();
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
			System.out.println(myList.get(i).value(0));
			System.out.println(classifier.classifyInstance(myList.get(i)));
		}
		
		File fileDel = new File(file.getOriginalFilename());
        fileDel.delete();
		
		return linRegFormList;
	}

}
