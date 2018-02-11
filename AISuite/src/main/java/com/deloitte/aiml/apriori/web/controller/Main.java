package com.deloitte.aiml.apriori.web.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import weka.associations.Apriori;
import weka.core.Instances;

public class Main

{

	public static void main(String[] args)

	{

		Instances data = null;

		try {

			BufferedReader reader = new BufferedReader(new

			FileReader("C:\\Users\\soumsarkar\\Documents\\New folder\\readTxt\\contact-lenses.arff"));

			data = new Instances(reader);

			reader.close();

			data.setClassIndex(data.numAttributes() - 1);

		}

		catch (IOException e) {

			e.printStackTrace();

		}

		double deltaValue = 0.05;

		double lowerBoundMinSupportValue = 0.05;

		double minMetricValue = 0.5;

		int numRulesValue = 100;

		double upperBoundMinSupportValue = 1.0;

		String resultapriori;

		Apriori apriori = new Apriori();

		apriori.setDelta(deltaValue);

		apriori.setLowerBoundMinSupport(lowerBoundMinSupportValue);

		apriori.setNumRules(numRulesValue);

		apriori.setUpperBoundMinSupport(upperBoundMinSupportValue);

		apriori.setMinMetric(minMetricValue);

		try

		{

			apriori.buildAssociations(data);

		}

		catch (Exception e) {

			e.printStackTrace();

		}

		resultapriori = apriori.toString();

		System.out.println(resultapriori);

	}

}
