package com.deloitte.aiml.rf.validation;

import static us.parr.lib.ParrtStats.mean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

import com.deloitte.aiml.kaggle.titatic.DecisionTreeExecutionPlan;
import com.deloitte.aiml.rf.classifier.ClassifierModel;
import com.deloitte.aiml.rf.data.DataTable;


public class Validation {
	
	public static final int SEED = 333888333; // need randomness but use same seed to get reproducibility
	public static final Random random = new Random(SEED);
	
	public static List<RFForm> kFoldCross(ClassifierModel classifier,DataTable data) throws IOException{
		data.shuffle(random);
		int n = data.size();
		
		classifier.train(data); // wipes old data, retrains
		
		DataTable toPredict = DataTable.loadCSV("C:\\Users\\vipinkumar\\Desktop\\FI_frontend\\predict.csv", null, null, null, true);
		//DataTable toPredict = DataTable.loadCSV(file, null, null, null, true);
		//System.out.println(toPredict.);
		int[] predictedValues = new int[toPredict.size()];
		int i=0;
		for (int[] row : toPredict) {
			predictedValues[i] = classifier.classify(row);
			i++;
			
		}
		System.out.println("Predicted Values:");
		List<RFForm> rfFormList = new ArrayList<>();
		for(i = 0; i<predictedValues.length; i++)
		{
			RFForm rfForm = new RFForm();
			/*rfForm.setSepalLen(predictedValues.);
			rfForm.setSepalWidth(sepalWidth);
			rfForm.setPetalLen(petalLen);
			rfForm.setPetalWidth(petalWidth);*/
			if(predictedValues[i]==0)
			{
				rfForm.setPrediction("Flower is Iris-setosa");
				System.out.println("Flower is Iris-setosa");
			}
			else if(predictedValues[i]==1)
			{
				rfForm.setPrediction("Flower is Iris-versicolor");
				System.out.println("Flower is Iris-versicolor");
			}
			else
			{
				rfForm.setPrediction("Flower is Iris-virginica");
				System.out.println("Flower is Iris-virginica");
			}
			rfFormList.add(rfForm);
		}
		return rfFormList;
	}	
}
