package com.deloitte.aiml.ann.web.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.deloitte.aiml.ann.form.ANNForm;

import weka.classifiers.Evaluation;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Instances;
import weka.core.Utils;

@RestController
@RequestMapping(value = "/aiSuit")
public class ANNController {

	private static String fileTrain = "ANNTrain.arff";
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value = "/callAnn", method = RequestMethod.POST)
	@ResponseBody
	public Object getSaveAnnPrediction(
			@RequestParam("file") MultipartFile file,
			RedirectAttributes redirectAttributes) throws Exception {

		List<ANNForm> annFormList = new ArrayList<>();
		try {

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
			// Reading training arff or csv file
			FileReader trainreader = new FileReader(fileTrain);
			Instances train = new Instances(trainreader);
			train.setClassIndex(train.numAttributes() - 1);
			// Instance of NN
			MultilayerPerceptron mlp = new MultilayerPerceptron();
			// Setting Parameters
			/*
			 * mlp.setLearningRate(0.1); mlp.setMomentum(0.2);
			 * mlp.setTrainingTime(2000); mlp.setHiddenLayers("3?");
			 * mlp.buildClassifier(train);
			 */
			// Another way to set parameter
			mlp.setOptions(Utils
					.splitOptions("-L 0.3 -M 0.2 -N 500 -V 0 -S 0 -E 20 -H 4"));

			Instances datapredict = new Instances(
					new BufferedReader(
							new FileReader(file.getOriginalFilename())));
			datapredict.setClassIndex(datapredict.numAttributes() - 1);

			mlp.buildClassifier(train);
			Evaluation eval = new Evaluation(train);
			eval.evaluateModel(mlp, datapredict);
			System.out.println(eval.errorRate()); // Printing Training Mean root
													// squared Error
			System.out.println(eval.toSummaryString()); // Summary of Training
			// To apply K-Fold validation
			// eval.crossValidateModel(mlp, train, kfolds, new Random(1));

			Instances predicteddata = new Instances(datapredict);
			// Predict Part
			for (int i = 0; i < datapredict.numInstances(); i++) {
				double clsLabel = mlp.classifyInstance(datapredict.instance(i));
				predicteddata.instance(i).setClassValue(clsLabel);
			}
			// Storing again in arff
			/*
			 * BufferedWriter writer = new BufferedWriter( new
			 * FileWriter(<Output File Path>));
			 * writer.write(predicteddata.toString()); writer.newLine();
			 * writer.flush(); writer.close();
			 */

			for (int i = 0; i < predicteddata.size(); i++) {
				ANNForm annForm = new ANNForm();
				annForm.setYear(predicteddata.get(i).stringValue(0));
				annForm.setMonth(predicteddata.get(i).stringValue(1));
				annForm.setWeek(predicteddata.get(i).stringValue(2));
				annForm.setPredictedPrice(predicteddata.get(i).stringValue(3));

				annFormList.add(annForm);
				System.out.println("--");
				System.out.println(predicteddata.get(i).stringValue(0));
				System.out.println(predicteddata.get(i).stringValue(1));
				System.out.println(predicteddata.get(i).stringValue(2));
				System.out.println(predicteddata.get(i).stringValue(3));
			}

			File fileDel = new File(file.getOriginalFilename());
			fileDel.delete();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return annFormList;
	}

}
