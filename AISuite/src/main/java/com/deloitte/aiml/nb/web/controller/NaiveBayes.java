package com.deloitte.aiml.nb.web.controller;

//Jun Xie 
//xie@eecs.oregonstate.edu
//Implementation for text classification using Naive Bayes Model
//2012
//There are two variants for Naive Bayes Model: one is Bernoulli model, which means whether the specific word occurs in this document or not, the other is multinomial model, which means how many words occus in the document. 
//Two model are the same, just need to calculate their probability and multiply them toghether and calculate its posterior probability, and compare the two cases, which one is bigger, then the prediction.

import java.util.*;
import java.io.*;


/*THIS IS THE ANOTHER APPROACH FOR NAIVE BAYES ALGO,
IN THIS WE NEED TO FIND A SOLUTION TO FIND SEPCIFIC PREDICTION*/

public class NaiveBayes {


protected static LinkedHashMap<String, LinkedHashMap<String, Double>> traindata = new LinkedHashMap<String, LinkedHashMap<String,Double>>();
protected static LinkedHashMap<String, LinkedHashMap<String, Double>> multinomial_traindata = new LinkedHashMap<String, LinkedHashMap<String,Double>>();
protected static LinkedHashMap<String, LinkedHashMap<String, Double>> multinomial_traindata_probability = new LinkedHashMap<String, LinkedHashMap<String,Double>>();
protected static LinkedHashMap<String, LinkedHashMap<String, Double>> bernoulli_traindata_probability = new LinkedHashMap<String, LinkedHashMap<String,Double>>();
protected static LinkedHashMap<String, LinkedHashMap<String, Double>> testdata  = new LinkedHashMap<String, LinkedHashMap<String,Double>>();
protected static LinkedHashMap<String, LinkedHashMap<String, Double>> trainclassdata = new LinkedHashMap<String, LinkedHashMap<String,Double>>();
protected static List<String> testlabel = new ArrayList<String>();
protected static List<String> trainlabel = new ArrayList<String>();
protected static List<String> wordvocabulary = new ArrayList<String>();
protected static List<Double> train_class_frequencey = new ArrayList<Double>();
protected static List<Double> train_class_double_frequence = new ArrayList<Double>();
protected static List<String> newsgrouplabels = new ArrayList<String>();
protected static ArrayList<Double> totalValues = new ArrayList<Double>();
protected static List<String> englishstop = new ArrayList<String>();
protected static HashMap<String, Integer> englishstop_position = new HashMap<String, Integer>();
protected static int multi_confusion_matrix_with_stopword[][] = new int[20][20];
protected static int multi_confusion_matrix_without_stopword[][] = new int[20][20];
protected static int bernoulli_confusion_matrix_with_stopword[][] = new int[20][20];
protected static int bernoulli_confusion_matrix_without_stopword[][] = new int[20][20];

/**
* Default Constructor
**/
public NaiveBayes() {
	  
	  
}

/**
* 
* @param data
* @param label
* @param vocabulary
*/
private static void train_multinomial_nb(LinkedHashMap<String, LinkedHashMap<String, Double>> data, List<String> label, List<String> vocabulary) {
	  
	  // for each c \in C
	  for (int i = 0; i < newsgrouplabels.size(); i++ ){
		  
		  // Total of word count
		  double totalValue = 0.0;
		  multinomial_traindata.put(Integer.toString(i+1), new LinkedHashMap<String, Double>());
		  multinomial_traindata_probability.put(Integer.toString(i+1), new LinkedHashMap<String, Double>());
		  
		  // Find how many documents in this class
		  ArrayList<String> position = new ArrayList<String>();
		  
		  /*For each the element in the trainlabel*/
		  for (int j = 0; j < trainlabel.size(); j++) {
			  
			  String document_label = trainlabel.get(j);		  
			  if (document_label.equals(Integer.toString(i+1))) {
				  
				  position.add(Integer.toString(j+1));
			  }
		  }
		  
		  /*calculate the frequency of each document class*/
		  train_class_frequencey.add(Double.parseDouble(Integer.toString(position.size())));
		  
		// text_{c} <--- ConcatenateTextofallDocsinclass(D, C)
		  for (int j = 0; j < position.size(); j++ ) {
			  
			  // Read the single_document
			  LinkedHashMap<String, Double> single_document = data.get(position.get(j));
			  Set<String> singledocumentset = single_document.keySet();
			  Iterator<String> it = singledocumentset.iterator();
			  while (it.hasNext()) {
				  
				  String word = it.next().toString();
				  totalValue = totalValue + data.get(position.get(j)).get(word);
				  boolean iscontains = multinomial_traindata.get(Integer.toString(i+1)).containsKey(word);
				  
				  double count = data.get(position.get(j)).get(word);
				  double existcount = 0;
				  if (iscontains) {
					  
					  
					  existcount = multinomial_traindata.get(Integer.toString(i+1)).get(word);
				  }
				  else {
					  
					  multinomial_traindata.get(Integer.toString(i+1)).put(word, count);
				  }
				  
				  
				  multinomial_traindata.get(Integer.toString(i+1)).put(word, count + existcount);
			  }   
		  }
		  
		  totalValues.add(totalValue);
		  
		  // for each t \in V do condprob
		  for (int k = 0; k < vocabulary.size(); k++) { 
			  
			  boolean iscontains = multinomial_traindata.get(Integer.toString(i+1)).containsKey(Integer.toString(k+1));
			  
			  if (iscontains) {
				  
				  double prob = Math.log((multinomial_traindata.get(Integer.toString(i+1)).get(Integer.toString(k+1)) + 1)/(totalValues.get(i) + vocabulary.size()));
				  multinomial_traindata_probability.get(Integer.toString(i+1)).put(Integer.toString(k+1), prob);
			  }
			  else {
				  
				  double prob = Math.log(1 / (totalValues.get(i) + vocabulary.size()));
				  multinomial_traindata_probability.get(Integer.toString(i+1)).put(Integer.toString(k+1), prob);
			  }
			  
		  }
	  }
	  
}

/**
* 
* @param data
* @param label
* @param frequence
* @return
*/
private static Double apply_multinomial_nb(LinkedHashMap<String, LinkedHashMap<String, Double>> data, List<String> label,  List<Double> frequence, boolean flag) {
	  
	  /*document size*/
	  Integer datasize = data.size();
	  double error = 0;
	  
	  /*find the largest one and use that one to predict and compare with the ground truth*/
	  for (int i = 0; i < datasize; i++) {
		  ArrayList<Double> classprobability = new ArrayList<Double>();
		  
		  //LinkedHashMap<String, Double> singledocument = data.get(Integer.toString(i+1));
		  //Set<String> singledocumentset = singledocument.keySet();
		  
		  //Iterator<String> it = singledocumentset.iterator();
		  
		  for (int j = 0; j < multinomial_traindata_probability.size(); j++ ) {
			  
			  LinkedHashMap<String, Double> singledocument = data.get(Integer.toString(i+1));
			  Set<String> singledocumentset = singledocument.keySet();
			  
			  Iterator<String> it = singledocumentset.iterator();
			  
			  double prob = Math.log(frequence.get(j));
			  
			  while (it.hasNext()) {
				  
				  String word = it.next().toString();
				  
				  boolean iscontains = englishstop_position.containsKey(word);
				  
				  
				  if (flag && iscontains) {
					  
					  
				  }
				  else {
					  prob = prob + multinomial_traindata_probability.get(Integer.toString(j+1)).get(word);
				  }
				  //System.out.println(multinomial_traindata_probability.get(Integer.toString(j+1)).get(word));
			  }
			  
			  classprobability.add(prob);
		  }
		  
		  Object obj = Collections.max(classprobability);
		  // System.out.println(classprobability);
		  String predicte_class1 = obj.toString();
		  // System.out.println(predicte_class1);
		  // System.out.println(Double.parseDouble(predicte_class1));
		  int index = classprobability.indexOf(Double.parseDouble(predicte_class1));
		  // System.out.println(index);
		  String predicte_class = Integer.toString(index + 1);
		  // System.out.println(predicte_class);
		  // If the predicted class is wrong, then we should incresea the error
		  if (!predicte_class.equals(label.get(i))) {
			  
			  error = error + 1;
			  
			  if (flag) {
				  
				  multi_confusion_matrix_without_stopword[Integer.parseInt(label.get(i)) - 1 ][Integer.parseInt(predicte_class) - 1] = multi_confusion_matrix_without_stopword[Integer.parseInt(label.get(i))-1][Integer.parseInt(predicte_class)-1] + 1;
			  }
			  else {
				
				  multi_confusion_matrix_with_stopword[Integer.parseInt(label.get(i)) - 1 ][Integer.parseInt(predicte_class) - 1] = multi_confusion_matrix_with_stopword[Integer.parseInt(label.get(i))-1][Integer.parseInt(predicte_class)-1] + 1;
			  }
			  
		  }
		  else
		  {
			  if (flag) {
				  
				  multi_confusion_matrix_without_stopword[Integer.parseInt(label.get(i)) - 1 ][Integer.parseInt(predicte_class) - 1] = multi_confusion_matrix_without_stopword[Integer.parseInt(label.get(i))-1][Integer.parseInt(predicte_class)-1] + 1;
			  }
			  else {
				
				  multi_confusion_matrix_with_stopword[Integer.parseInt(label.get(i)) - 1 ][Integer.parseInt(predicte_class) - 1] = multi_confusion_matrix_with_stopword[Integer.parseInt(label.get(i))-1][Integer.parseInt(predicte_class)-1] + 1;
			  }
			  
		  }
		  
		  
	  }
	  
	  return (1-error/label.size());
}

/**
* 
* @param data
* @param label
* @param vocabulary
*/
private static void train_bernoulli_nb(LinkedHashMap<String, LinkedHashMap<String, Double>> data, List<String> label, List<String> vocabulary) {
	  
	// for each c \in C
	  for (int i = 0; i < newsgrouplabels.size(); i++ ){
		  	  
		  bernoulli_traindata_probability.put(Integer.toString(i+1), new LinkedHashMap<String, Double>());
		  
		  // Find how many documents in this class
		  ArrayList<String> position = new ArrayList<String>();
		  
		  /*For each the element in the trainlabel*/
		  for (int j = 0; j < trainlabel.size(); j++) {
			  
			  String document_label = trainlabel.get(j);		  
			  if (document_label.equals(Integer.toString(i+1))) {
				  
				  position.add(Integer.toString(j+1));
			  }
		  }
		  
		  for (int k = 0; k < vocabulary.size(); k++) {
			  
			  double occurance = 0.0;
			  
			  for (int j = 0; j < position.size(); j++ ) { 
				  
				  LinkedHashMap<String, Double> single_document = data.get(position.get(j));
				  
				  boolean iscontains = single_document.containsKey(Integer.toString(k+1));
				  
				  if (iscontains) {
					  
					  occurance = occurance + 1;
				  }
			  }
			  
			  double prob = (occurance + 1)/(position.size() + 20);
			  bernoulli_traindata_probability.get(Integer.toString(i+1)).put(Integer.toString(k+1), prob);
			  
			  
		  }
	  }
}

/**
* 
* @param data
* @param label
* @param frequence
* @return
*/
private static Double apply_bernoulli_nb(LinkedHashMap<String, LinkedHashMap<String, Double>> data, List<String> label,  List<Double> frequence, boolean flag) {
	  
	  /*document size*/
	  Integer datasize = data.size();
	  double error = 0;
	  
	  /*find the largest one and use that one to predict and compare with the ground truth*/
	  for (int i = 0; i < datasize; i++) {
		  
		  ArrayList<Double> classprobability = new ArrayList<Double>();
		  
		  for (int j = 0; j < bernoulli_traindata_probability.size(); j++ ) {
			  
			  LinkedHashMap<String, Double> singledocument = data.get(Integer.toString(i+1));			  
			  double prob = Math.log(frequence.get(j));
			  
			  for (int k = 0; k < wordvocabulary.size(); k++) {
				  
				  boolean iscontainsstopword = englishstop_position.containsKey(Integer.toString(k+1));
				  
				  if (flag && iscontainsstopword) {
					  
				  
				  }
				  else {
					  
					  boolean iscontains = singledocument.containsKey(Integer.toString(k+1));
					  
					  if (iscontains) {
						  
						  prob = prob + Math.log(bernoulli_traindata_probability.get(Integer.toString(j+1)).get(Integer.toString(k+1)));
					  }
					  else {
						  
						  prob = prob + Math.log(1 - bernoulli_traindata_probability.get(Integer.toString(j+1)).get(Integer.toString(k+1)));
					  }
				  }
				  
			  }
			 
			  classprobability.add(prob);
		  }
		  
		  Object obj = Collections.max(classprobability);
		  // System.out.println(classprobability);
		  String predicte_class1 = obj.toString();
		  // System.out.println(predicte_class1);
		  // System.out.println(Double.parseDouble(predicte_class1));
		  int index = classprobability.indexOf(Double.parseDouble(predicte_class1));
		  // System.out.println(index);
		  String predicte_class = Integer.toString(index + 1);
		  // System.out.println(predicte_class);
		  // If the predicted class is wrong, then we should incresea the error
		  if (!predicte_class.equals(label.get(i))) {
			  
			  error = error + 1;
			  
			  if (flag) {
				  
				  bernoulli_confusion_matrix_without_stopword[Integer.parseInt(label.get(i)) - 1 ][Integer.parseInt(predicte_class) - 1] = bernoulli_confusion_matrix_without_stopword[Integer.parseInt(label.get(i))-1][Integer.parseInt(predicte_class)-1] + 1;
			  }
			  else {
				
				  bernoulli_confusion_matrix_with_stopword[Integer.parseInt(label.get(i)) - 1 ][Integer.parseInt(predicte_class) - 1] = bernoulli_confusion_matrix_with_stopword[Integer.parseInt(label.get(i))-1][Integer.parseInt(predicte_class)-1] + 1;
			  }
		  }
		  else {
			  
			  if (flag) {
				  
				  bernoulli_confusion_matrix_without_stopword[Integer.parseInt(label.get(i)) - 1 ][Integer.parseInt(predicte_class) - 1] = bernoulli_confusion_matrix_without_stopword[Integer.parseInt(label.get(i))-1][Integer.parseInt(predicte_class)-1] + 1;
			  }
			  else {
				
				  bernoulli_confusion_matrix_with_stopword[Integer.parseInt(label.get(i)) - 1 ][Integer.parseInt(predicte_class) - 1] = bernoulli_confusion_matrix_with_stopword[Integer.parseInt(label.get(i))-1][Integer.parseInt(predicte_class)-1] + 1;
			  }
		  }
		  
		  
	  }
	  
	  return (1-error/label.size());
}


/** 
* Code for reading a file. 
**/
private static List<String> readFile(String fileName) {
	
	List<String> al = new ArrayList<String>();
	
 try {
   
   BufferedReader input = new BufferedReader(new FileReader(fileName));
   for(String line = input.readLine(); line != null; line = input.readLine()) {
     
 	  al.add(line);
   }
   input.close();

   return al;
   
 } catch(IOException e) {
   e.printStackTrace();
   System.exit(1);
   return null;
 } 
}



private static LinkedHashMap<String, LinkedHashMap<String, Double>> readFileIntoHashMap_remove_stopwords(LinkedHashMap<String, LinkedHashMap<String, Double>> data, List<String> stopwords) {
		
	  LinkedHashMap<String, LinkedHashMap<String, Double>> returndata = new LinkedHashMap<String, LinkedHashMap<String,Double>>();
	  
	  for (int i = 0; i < stopwords.size(); i++) {
		  
		  
	  }
	  
	  return returndata;
}

/** 
* Code for reading a file. 
**/
private static LinkedHashMap<String, LinkedHashMap<String, Double>> readFileIntoHashMap(String fileName, List<String> label) {
	
	LinkedHashMap<String, LinkedHashMap<String, Double>> linkedHashMap = new LinkedHashMap<String, LinkedHashMap<String,Double>>();
	
	/*For every document, is a HashMap*/
	for (int i = 0; i < label.size(); i++) {
		
		linkedHashMap.put(Integer.toString(i+1), new LinkedHashMap<String, Double>());
	}
	
 try {
   
   BufferedReader input = new BufferedReader(new FileReader(fileName));
   for(String line = input.readLine(); line != null; line = input.readLine()) {
     
 	  String[] stringlist = line.split(" ");
 	  
 	  LinkedHashMap<String, Double> innerHashMap;
 	  
 	  if (linkedHashMap.containsKey(stringlist[0])) {

 		  innerHashMap = linkedHashMap.get(stringlist[0]);
 		  
 	  }
 	  else {

 		  innerHashMap = new LinkedHashMap<String, Double>();
 		  linkedHashMap.put(stringlist[0], innerHashMap);
 	  }
 	  
 	  /**
 	   * This section of code is to find whether the key value exists in the innerHashMap
 	   * If not, then just 0 + its value
 	   * If yes, then need to retrieve the count + its value
 	   */
 	  double count = 0.0;
 	  if (innerHashMap.containsKey(stringlist[1])) {
 		  
 		  count = innerHashMap.get(stringlist[1]);
 	  }
 	  
 	  innerHashMap.put(stringlist[1], count + Double.parseDouble(stringlist[2]));
 	  
   }
   input.close();

   return linkedHashMap;
   
 } catch(IOException e) {
   e.printStackTrace();
   System.exit(1);
   return null;
 } 
}

/**
* Print the arraylist information
* @param al
*/
public static void printArrayList(List<?> al) {
	  
	  for (int i = 0; i < al.size(); i++) {
		  
		  System.out.println(al.get(i));
	  }
	  
}

public static void printHashMap(LinkedHashMap<String, LinkedHashMap<String, Double>> data) {
	  
	  for (int i = 0; i < data.size(); i++ ) {
	    	
 	  Set<String> singledocumentset = data.get(Integer.toString(i+1)).keySet();
		  Iterator<String> it = singledocumentset.iterator();
		  
		  while (it.hasNext()) {
			  
			  String word = it.next().toString();
			  System.out.println(Integer.toString(i+1) + "\t" + word + "\t" + data.get(Integer.toString(i+1)).get(word) );
		  }
 }
}

/**
* 
* @param vocabulary
* @param stopword
* @return
*/
public static HashMap<String, Integer> find_stop_words_position(List<String> vocabulary, List<String> stopword) {
	  
	  HashMap<String, Integer> stop_word_position = new HashMap<String, Integer>();
	  
	  for (int i = 0; i < vocabulary.size(); i++) {
		  
		  for (int j = 0; j < stopword.size(); j++) {
			  
			  
			  if (vocabulary.get(i).toString().equals(stopword.get(j).toString())) {
				  
				  
				  stop_word_position.put(Integer.toString(i+1), i );
			  }
		  }
	  }
	  
	  return stop_word_position;
}

/**
* 
* @param confusionmatrix
*/
private static void printConfusionMatrix(int[][] confusionmatrix) {
	  
	  for (int i = 0; i < confusionmatrix.length; i++) {
		  
		  for (int j = 0; j < confusionmatrix[i].length; j ++) {
			  
			  System.out.print(Integer.toString(confusionmatrix[i][j]) + "    " );
		  }
		  
		  System.out.println("");
	  }
}

 
/**
* Main Function
* @param args
*/
public static void main(String[] args) {

	System.out.println("Experiment on Newsgroup dataset using two Naive Bayes Model: respectively Bernoulli case and Multinomial case:");
	System.out.println("Just for Note: the execution may take several minutes.");
	System.out.println("===============================================================================================================");
	String testData= "C:\\Users\\soumsarkar\\Documents\\New folder\\readTxt\\data\\test.data";
	String testLabel= "C:\\Users\\soumsarkar\\Documents\\New folder\\readTxt\\data\\test.label";
	String trainData= "C:\\Users\\soumsarkar\\Documents\\New folder\\readTxt\\data\\train.data";
	String trainLabel= "C:\\Users\\soumsarkar\\Documents\\New folder\\readTxt\\data\\train.label";
	String vocabularyTxt= "C:\\Users\\soumsarkar\\Documents\\New folder\\readTxt\\data\\vocabulary.txt";
	String newsgrouplabelsTxt = "C:\\Users\\soumsarkar\\Documents\\New folder\\readTxt\\data\\newsgrouplabels.txt";
	String englishStop = "C:\\Users\\soumsarkar\\Documents\\New folder\\readTxt\\data\\english.stop";
	//String[] inputParameters = {"data/test.data", "data/test.label", "data/train.data", "data/train.label", "data/vocabulary.txt", "data/newsgrouplabels.txt", "data/english.stop"};
	String[] inputParameters = {testData, testLabel, trainData, trainLabel, vocabularyTxt, newsgrouplabelsTxt, englishStop};
	
	
	System.out.println("Step 1: Load Data from six separate datafiles: ");
	System.out.println("========================================================================");
 /*Import the data to the according list*/
	// Note: the index is from 0
	
 testlabel = readFile(inputParameters[1]);
 System.out.println("Test Label loaded..........................");
	trainlabel = readFile(inputParameters[3]);
	System.out.println("Train Label loaded..........................");
	wordvocabulary = readFile(inputParameters[4]);
	System.out.println("Vocabulary loaded...........................");
	newsgrouplabels = readFile(inputParameters[5]);
	System.out.println("New group label loaded.........................");
	traindata = readFileIntoHashMap(inputParameters[2], trainlabel);
	System.out.println("Train data loaded..............................");
	testdata = readFileIntoHashMap(inputParameters[0], testlabel);
	System.out.println("Test data loaded..............................");
	englishstop = readFile(inputParameters[6]);
	System.out.println("English Stop Words loaded...................");
	englishstop_position = find_stop_words_position(wordvocabulary, englishstop);
	System.out.println("Find the stopwords position in the vocabulary");
	
	
	
	System.out.println("\n");
	System.out.println("Step 2: Train the model:");
	System.out.println("=======================================================================");
	
 train_multinomial_nb(traindata, trainlabel, wordvocabulary);
 train_bernoulli_nb(traindata, trainlabel, wordvocabulary);
 
 //printHashMap(multinomial_traindata_probability);
 
 System.out.println("Document Stats: ");
 
 double totalnumberword = 0.0;
 for (int i = 0; i < totalValues.size(); i++ ) {
 	
 	totalnumberword = totalnumberword + totalValues.get(i);
 }
 
 System.out.println("Total Number of words = " + Double.toString(totalnumberword));
 
 for (int i = 0; i < totalValues.size(); i++) {
 	
 	
 	System.out.println(Integer.toString(i+1) + "\t\t" + Double.toString(train_class_frequencey.get(i)) + "\t\t" + Double.toString(totalValues.get(i)) + "\t\t" + Integer.toString((multinomial_traindata.get(Integer.toString(i+1)).size())));
 }
 
 
 System.out.println("\n");
 System.out.println("Step 3: Calculate the accuracy on test data using the Bernoulli Model:");
 System.out.println("======================================================================");
 
 // calculate the each class frequencey according to train_class_frequencey
 for (int i = 0; i < train_class_frequencey.size(); i++ ) {
 	
 	double frequency = train_class_frequencey.get(i) / Double.parseDouble(Integer.toString(trainlabel.size()));
 	train_class_double_frequence.add(frequency);
 }
 
 for (int i = 0; i < 20; i++) {
 	
 	for (int j = 0; j < 20; j++ ) {
 		
 		multi_confusion_matrix_with_stopword[i][j] = 0;
 		multi_confusion_matrix_without_stopword[i][j] = 0;
 		bernoulli_confusion_matrix_with_stopword[i][j] = 0;
 		bernoulli_confusion_matrix_without_stopword[i][j] = 0;
 	}
 }
 
 
 double bernoulliaccuracywithstopword = apply_bernoulli_nb(testdata, testlabel, train_class_double_frequence, false);
 System.out.println("The accuracy on test data using the Bernoulli with stop word:    " + Double.toString(bernoulliaccuracywithstopword));
 printConfusionMatrix(bernoulli_confusion_matrix_with_stopword);
 
 
 double bernoulliaccuracywithoutstopword = apply_bernoulli_nb(testdata, testlabel, train_class_double_frequence, true);
 System.out.println("The accuracy on test data using the Bernoulli with stop word:    " + Double.toString(bernoulliaccuracywithoutstopword));
 printConfusionMatrix(bernoulli_confusion_matrix_without_stopword);

 System.out.println("\n");
 System.out.println("Step 4: Calculate the accuracy on test data using the Multinomial Model:");
 System.out.println("======================================================================");

 double multinomialaccuraywithstopwords = apply_multinomial_nb(testdata, testlabel, train_class_double_frequence, false);
 System.out.println("The accuracy on test data using the Multinominal with stopwords:    " + Double.toString(multinomialaccuraywithstopwords));
 printConfusionMatrix(multi_confusion_matrix_with_stopword);
 
 double multinomialaccuraywithoutstopwords = apply_multinomial_nb(testdata, testlabel, train_class_double_frequence, true);
 System.out.println("The accuracy on test data using the Multinominal with stopwords:    " + Double.toString(multinomialaccuraywithoutstopwords));
 printConfusionMatrix(multi_confusion_matrix_without_stopword);

 System.out.println("\n");
 System.out.println("done...........");
}
}
