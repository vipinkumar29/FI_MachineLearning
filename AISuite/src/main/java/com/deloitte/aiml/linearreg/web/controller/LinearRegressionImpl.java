package com.deloitte.aiml.linearreg.web.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.deloitte.aiml.linearreg.execution.methods.LinRegGradientDescent;
import com.deloitte.aiml.linearreg.execution.methods.LinRegNormalEquation;
import com.deloitte.aiml.linearreg.service.LinearRegressionService;

import Jama.Matrix;

/**
 * Linear Regression
 * 
 * 
 */

@RestController
@RequestMapping(value="/aiSuit")
public class LinearRegressionImpl {
	
	@Autowired
	LinearRegressionService linearRegressionService;
	
    private static final String TRAINING_FILE = "training_data_5.txt";
	
	//input training examples
	//each row is all one training tuple
	//each column is one feature
	private static Matrix X;
	//one column of outputs for each training tuple
	private static Matrix y;
	
	private static boolean NORMALIZED = false;
	private static boolean REGULARIZED = false;
	private static boolean DEBUG_INFO = false;
	
	//to what extent we punish non-zero theta
	private static double LAMBDA = 1.0;
	
	@RequestMapping(value="/callLinRegImpl", method = RequestMethod.GET)
	@ResponseBody
	public String getSaveLinRegPrediction(/*@ModelAttribute("loginForm")LoginForm loginForm,*/
			HttpServletRequest request,HttpServletResponse response,
			HttpSession session,RedirectAttributes redirectAttributes,ModelMap modelMap) {
		
		String status = null;
		
		//initialize X and y0
		readTrainingData();
		
		//add features such as x1*x2, x1^2, x2^2
		//add2ndDegreeFeatures();
		
		//normalize so all features have the same distribution
		//approximately -2.5 <-> 2.5
		//gradient descent converges poorly when features are out of proportion
//		normalize();
		
		//add fake feature to bias so that we can matrix multiple
		//for ex. 
		// y = th1 * x1 + th0 -> y = th1 * x1 + th0 * x0
		//x0, the fake feature, is always 1
		//so we add a column of ones to input
		addBiasFeature();
		
		//experiment 3
//		Matrix theta = GradientDescent.run(X, y, 1500, 0.01, LAMBDA, REGULARIZED, DEBUG_INFO);
		//experiment 4
//		Matrix theta = GradientDescent.run(X, y, 400, 0.03, LAMBDA, REGULARIZED, DEBUG_INFO);
//		Matrix theta = LinRegNormalEquation.run(X, y);
		//experiment 5
		Matrix theta = LinRegGradientDescent.run(X, y, 25, 0.0001, LAMBDA, REGULARIZED, DEBUG_INFO);
		
		//display results
		int n = theta.getRowDimension();
		DecimalFormat df = new DecimalFormat();
		df.setMaximumFractionDigits(3);
		
		System.out.println("Best Estimate for Equation:");
		String str = "y = ";
		for(int i = 0; i < n; i++){
			str += df.format(theta.getArray()[i][0]) + " ";
			if(i != 0){
				str += "x" + (i) + " ";
			}
			str += "+ ";
		}
		
		System.out.println(str.substring(0, str.length() - 3));
		System.out.println("");
		System.out.println("Total Error:");
		System.out.println(df.format(error(theta, X, y)));
		System.out.println("");
		System.out.println("Press Y to predict an input, and N to close program");
		Scanner s = new Scanner(System.in);
		String token = s.next().toLowerCase();
		
		while(!token.contains("n")){
			System.out.println("Enter Input Features");
			double[][] in = new double[1][n];
			in[0][0] = 1.0;
			for(int i = 1; i < n; i++){
				in[0][i] = s.nextDouble();
				if(NORMALIZED){
					in[0][i] = (in[0][i] - FEATURE_MEAN[i - 1]) / FEATURE_STD[i - 1];
				}
			}
			System.out.println(Arrays.deepToString(in));
			Matrix x = new Matrix(in);
			//TODO need to normalize input features if training set is normalized
			System.out.println("Expected Output is " + predict(theta, x));
			double yAxis1 = in[0][1];
			double yAxis2 = in[0][2];
			double xAxis = predict(theta, x);
			status = linearRegressionService.saveLinearRegPredictedData(yAxis1, yAxis2, xAxis);
			
			System.out.println("Press Y to predict an input, and N to close program");
			token = s.next().toLowerCase();
		}
		s.close();
		
		return status;
	}

	public static double predict(Matrix theta, Matrix x){
		return x.times(theta).getArray()[0][0];
	}
	
	public static double error(Matrix theta, Matrix X, Matrix y){
		double error = 0.0;
		
		int m = X.getRowDimension();
		int n = X.getColumnDimension();
		for(int row = 0; row < m; row++){			
			//									get each row of matrix
			double predicted = predict(theta, X.getMatrix(row, row, 0, n-1));
			double actual = y.getArray()[row][0];
			double diff = predicted - actual;
			error += Math.pow(diff, 2);
			
		}
		//punish each theta for having non-zero values
		//should help avoid overfitting(tends to make unneeded features 0)
		if (REGULARIZED) {
			//feature start at 1 to avoid editing bias (theta0)
			for (int feature = 1; feature < n; feature++) {
				//TODO: would be more efficient is only did LAMBDA multiplication once at end of sum
				error += LAMBDA * Math.pow(theta.getArray()[feature][0], 2);
			}
		}
		
		return error / (2 * m);
	}
	//will add features in the following way
	//	x1	  x2	x1x1	x1x2	x2x2
	//	---old--	-------new----------
	//	or
	//	x1	 x2	  x3	x1x1	x1x2	x1x3	x2x2	x2x3	x3x3
	//	---old------    -----------------new-------------------------
	private static void add2ndDegreeFeatures(){
		int n = X.getColumnDimension();
		//number of ways to select two from n, with repitition, without order 
		//(Combination with rep)
		int k = 2;
		int newFeatures = combination(n + k - 1, k);
		
		double[][] soFar = X.getArray();
		double[][] newX = new double[soFar.length][soFar[0].length + newFeatures];
		//fill in old part of matrix
		for(int i = 0; i < newX.length; i++){
			for(int j = 0; j < n; j++){
				newX[i][j] = soFar[i][j];					
			}
		}
		//fill in new part of matrix
		for(int i = 0; i < newX.length; i ++){
			//feature 1
			for(int f1 = 0; f1 < n; f1++){
				//feature 2
				for(int f2 = f1; f2 < n; f2++){
					double newFeature = soFar[i][f1] * soFar[i][f2];
					//add to matrix
					newX[i][n + f1 + f2] = newFeature;
				}
			}
		}
		X = new Matrix(newX);
	}
	
	private static void addBiasFeature() {
		double[][] soFar = X.getArray();
		double[][] newX = new double[soFar.length][soFar[0].length + 1];
		for(int i = 0; i < newX.length; i++){
			for(int j = 0; j < newX[i].length; j++){
				if(j == 0){
					newX[i][j] = 1.0;
				}else{
					newX[i][j] = soFar[i][j-1];					
				}
			}
		}
		X = new Matrix(newX);
	}
	
	private static double[] FEATURE_MEAN;
	private static double[] FEATURE_STD;
	public static void normalize(){
		NORMALIZED = true;
		int m = X.getRowDimension();
		int n = X.getColumnDimension();
		
		FEATURE_MEAN = new double[n];
		FEATURE_STD  = new double[n];
		//for each feature
		for(int col = 0; col < n; col++){
			//get all values, for given feature
			double[][] features = X.getMatrix(0, m-1, col, col).getArray();
			
			//calculate mean
			FEATURE_MEAN[col] = 0;
			for(int i = 0; i < m; i++){
				FEATURE_MEAN[col] += features[i][0];
			}
			FEATURE_MEAN[col] /= m;
			
			//calculate standard deviation
			FEATURE_STD[col] = 0;
			for(int i = 0; i < m; i++){
				FEATURE_STD[col] += Math.pow((features[i][0] - FEATURE_MEAN[col]), 2);
			}
			FEATURE_STD[col] /= m;
			FEATURE_STD[col] = Math.sqrt(FEATURE_STD[col]);
			
			//okay now replace values with normalized
			//normalized means (val - mean) / std
			for(int j = 0; j < m; j++){
				double val = X.getArray()[j][col];
				val = (val - FEATURE_MEAN[col]) / FEATURE_STD[col];
				X.getArray()[j][col] = val;
			}
		}
	}
	
	public static void readTrainingData(){ 
		ArrayList<TrainingData> list = new ArrayList<TrainingData>();
		int inputs = -1; //start at -1 to exclude last input, which is output
		try {
			Scanner s = new Scanner(new File(TRAINING_FILE));
			//read header to determine number of variables
			Scanner lineScanner = new Scanner(s.nextLine());
			while(lineScanner.hasNext()){
				inputs++;
				lineScanner.next();
			}
			lineScanner.close();
			s.nextLine();
			
			while(s.hasNextLine()){
				String line = s.nextLine();
				lineScanner = new Scanner(line);
				double[] input = new double[inputs];
				for(int i = 0; i < inputs; i++){
					input[i] = lineScanner.nextDouble();
				}
				list.add(new TrainingData(input, lineScanner.nextDouble()));
			}
			s.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not Found");
			e.printStackTrace();
		}
		//convert to matrix
		double[][] input = new double[list.size()][inputs];
		double[][] output = new double[list.size()][1];
		for(int i = 0; i < input.length; i++){
			for(int j = 0; j < input[i].length + 1; j++){
				if(j == input[i].length){			
					output[i][0] = list.get(i).output;
				}else{
					input[i][j] = list.get(i).input[j];					
				}
			}
		}
		X = new Matrix(input);
		y = new Matrix(output);
	}

	// computes n choose k for big integers
	private static int combination(int n, int k) {
		int answer = 1;
		for (int i = 0; i < k; i++) {
			answer = answer * (n - i) / (i + 1);
		}
		return answer;
	}

}
class TrainingData {
	public double[] input;
	public double output;
	public TrainingData(double[] i, double o){
		input = i;
		output = o;
	}

}
