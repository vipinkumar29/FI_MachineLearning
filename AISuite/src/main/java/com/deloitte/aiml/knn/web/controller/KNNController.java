package com.deloitte.aiml.knn.web.controller;

import java.io.File;
import java.io.FileWriter;
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

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.lazy.IBk;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;

@RestController
@RequestMapping(value = "/aiSuit")
public class KNNController {
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/callKnn", method = RequestMethod.POST)
	@ResponseBody
	public Object getSaveKnnPrediction(
			@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) throws Exception {

		if (file.isEmpty()) {
			return "File is Empty";
		} else {
			System.out.println("File is getting uploaded Successfully");
			// Get the file and save it somewhere
			byte[] bytes = file.getBytes();
			FileWriter fileWriter = new FileWriter(new File(
					file.getOriginalFilename()));
			String str = new String(bytes, "UTF-8");
			fileWriter.append(str).append("\n");

			fileWriter.flush();
			fileWriter.close();
		}
		Instances data1 = getDataSet(file.getOriginalFilename());
		data1.setClassIndex(data1.numAttributes() - 1);
		// k - the number of nearest neighbors to use for prediction
		Classifier ibk = new IBk(2);
		ibk.buildClassifier(data1);
		double label1 = ibk.classifyInstance(data1.lastInstance());
		data1.lastInstance().setClassValue(label1);
		System.out.println("Prdeiction:=> "
				+ data1.lastInstance().stringValue(3));

		List<KNNForm> knnFormList = new ArrayList<>();
		Evaluation eval = new Evaluation(data1);
		eval.evaluateModel(ibk, data1);
		for (int i = 0; i < data1.size() - 1; i++) {
			KNNForm knnForm = new KNNForm();
			knnForm.setCustomerId(data1.get(i).value(0));
			knnForm.setAge(data1.get(i).value(1));
			knnForm.setIncome(data1.get(i).value(2));
			knnForm.setProduct(data1.get(i).stringValue(3));

			knnFormList.add(knnForm);
			// ----
			System.out.println(data1.get(i).value(0));
			System.out.println(data1.get(i).value(1));
			System.out.println(data1.get(i).value(2));
			System.out.println(data1.get(i).stringValue(3));
			System.out.println(data1.get(i).stringValue(3));

			System.out.println(ibk);

			/** Print the algorithm summary */
			/*
			 * System.out.println("** KNN Demo  **");
			 * System.out.println(eval.toSummaryString());
			 * System.out.println(eval.toClassDetailsString());
			 * System.out.println(eval.toMatrixString());
			 */
		}
		System.out.println("-------------");
		KNNForm knnForm = new KNNForm();
		knnForm.setCustomerId(data1.get(data1.size() - 1).value(0));
		knnForm.setAge(data1.get(data1.size() - 1).value(1));
		knnForm.setIncome(data1.get(data1.size() - 1).value(2));
		knnForm.setProduct(data1.get(data1.size() - 1).stringValue(3));

		knnFormList.add(knnForm);
		// --
		System.out.println(data1.get(data1.size() - 1).value(0));
		System.out.println(data1.get(data1.size() - 1).value(1));
		System.out.println(data1.get(data1.size() - 1).value(2));
		System.out.println(data1.get(data1.size() - 1).stringValue(3));
		System.out.println("-------------");

		File fileDel = new File(file.getOriginalFilename());
		fileDel.delete();

		return knnFormList;

	}

	/**
	 * This method is to load the data set.
	 * 
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
		// loader.setSource(KNNDemo.class.getResourceAsStream("/" + fileName));
		// ConverterUtils.DataSource source = new
		// ConverterUtils.DataSource(fileName);
		loader.setSource(new File(fileName));
		/**
		 * we can also set the file like loader3.setFile(new
		 * File("test-confused.arff"));
		 */
		// loader.setFile(new File(fileName));
		Instances dataSet = loader.getDataSet();
		// Instances dataSet = source.getDataSet();
		/** set the index based on the data given in the arff files */
		dataSet.setClassIndex(classIdx);
		return dataSet;
	}
}
