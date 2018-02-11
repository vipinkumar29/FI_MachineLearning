package com.deloitte.aiml.kaggle.titatic;

import static com.deloitte.aiml.decisiontree.feature.P.betweenD;
import static com.deloitte.aiml.decisiontree.feature.P.lessThanD;
import static com.deloitte.aiml.decisiontree.feature.P.moreThan;
import static com.deloitte.aiml.decisiontree.feature.P.moreThanD;
import static com.deloitte.aiml.decisiontree.feature.P.startsWith;
import static com.deloitte.aiml.decisiontree.feature.PredicateFeature.newFeature;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseBool;
import org.supercsv.cellprocessor.ParseDouble;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvListReader;
import org.supercsv.io.ICsvListReader;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.util.CsvContext;

import com.deloitte.aiml.decisiontree.DecisionTree;
import com.deloitte.aiml.decisiontree.data.DataSample;
import com.deloitte.aiml.decisiontree.data.SimpleDataSample;
import com.deloitte.aiml.decisiontree.feature.Feature;
import com.deloitte.aiml.decisiontree.label.BooleanLabel;
import com.google.common.collect.Lists;

public class DecisionTreeExecutionPlan {

    /*public static void main(String[] args) throws FileNotFoundException, IOException {
        List<DataSample> trainingData = readData(true);
        DecisionTree tree = new DecisionTree();
        
        List<Feature> features = getFeatures();
        
        tree.train(trainingData, features);
        
        // print tree after training
        tree.printTree();
        
        // read test data
        List<DataSample> testingData = readData(false);
        List<String> predictions = Lists.newArrayList();
        // classify all test data
        for (DataSample dataSample : testingData) {
            predictions.add(dataSample.getValue("PassengerId").get() + "," + tree.classify(dataSample).getPrintValue());
        }
        
        // write predictions to file
        FileWriter fileWriter = new FileWriter(new File("predictions.csv"));
        fileWriter.append("PassengerId,Survived").append("\n");
        for (String prediction : predictions) {
            fileWriter.append(prediction).append("\n");
        }
        fileWriter.flush();
        fileWriter.close();
        
    }
    */
    private static List<Feature> getFeatures() {
    	/*Feature firstClassPassenger = newFeature("Pclass", 1);
        Feature secondClassPassenger = newFeature("Pclass", 2);
        Feature thirdClassPassenger = newFeature("Pclass", 3);*/
        Feature isMale = newFeature("Type", "Internet");
        Feature isFemale = newFeature("Type", "Service");
        Feature ageLessThan10 = newFeature("YoB", lessThanD(10.0), "less than 10");
        Feature ageBewteen10And30 = newFeature("YoB", betweenD(10.0, 30.0), "between 10 and 30");
        Feature ageBewteen30And50 = newFeature("YoB", betweenD(30.0, 50.0), "between 30 and 50");
        Feature ageBewteen50And60 = newFeature("YoB", betweenD(50.0, 60.0), "between 50 and 60");
        Feature ageMoreThan60 = newFeature("YoB", moreThanD(60.0), "more than 60");
        /*Feature hasSiblings = newFeature("SibSp", moreThan(0), "more than 0");
        Feature moreThan2Siblings = newFeature("SibSp", moreThan(2), "more than 2");
        Feature hasParentsChildren = newFeature("Parch", moreThan(0), "more than 0");
        Feature moreThan2Children = newFeature("Parch", moreThan(2), "more than 2");*/
        Feature fareMoreThan7 = newFeature("Revenue", lessThanD(7.89), "less than 7.89");
        Feature fareBetween7And15 = newFeature("Revenue", betweenD(7.89, 15.78), "between 7.89 and 15.78");
        Feature fareBetween15And23 = newFeature("Revenue", betweenD(15.78, 23.67), "between 15.78 and 23.67");
        Feature fareBetween23And31 = newFeature("Revenue", betweenD(23.67, 31.56), "between 23.67 and 31.56");
        Feature fareBetween31And71 = newFeature("Revenue", betweenD(31.56, 71.01), "between 31.56 and 71.01");
        Feature fareMoreThan71 = newFeature("Revenue", moreThanD(71.01), "more than 71.01");
        /*Feature cabinA = newFeature("Cabin", startsWith("A"), "starts with A");
        Feature cabinB = newFeature("Cabin", startsWith("B"), "starts with B");
        Feature cabinC = newFeature("Cabin", startsWith("C"), "starts with C");
        Feature cabinD = newFeature("Cabin", startsWith("D"), "starts with D");
        Feature cabinE = newFeature("Cabin", startsWith("E"), "starts with E");
        Feature cabinF = newFeature("Cabin", startsWith("F"), "starts with F");*/
        Feature embarkedC = newFeature("EmbarkedMilestone", "C");
        Feature embarkedS = newFeature("EmbarkedMilestone", "S");
        Feature embarkedQ = newFeature("EmbarkedMilestone", "Q");
        
        return Arrays.asList(/*firstClassPassenger, secondClassPassenger, thirdClassPassenger,*/ isMale, isFemale, /*hasSiblings, moreThan2Siblings,
                hasParentsChildren, moreThan2Children,*/ ageLessThan10, ageBewteen10And30, ageBewteen30And50, ageBewteen50And60, ageBewteen50And60, ageMoreThan60,
                fareMoreThan7, fareBetween7And15, fareBetween15And23, fareBetween23And31, fareBetween31And71, fareMoreThan71, fareMoreThan71, /*cabinA, cabinB, cabinC,
                cabinD, cabinE, cabinF,*/ embarkedC, embarkedS, embarkedQ);
    }
    
    private static List<DataSample> readData(boolean training) throws IOException {
        List<DataSample> data = Lists.newArrayList();
        String filename = training ? "Sales_train.csv" : "Sales_test.csv";
        InputStreamReader stream = new InputStreamReader(DecisionTreeExecutionPlan.class.getResourceAsStream(filename));
        try (ICsvListReader listReader = new CsvListReader(stream, CsvPreference.STANDARD_PREFERENCE);) {
            
            // the header elements are used to map the values to the bean (names must match)
            final String[] header = listReader.getHeader(true);
            
            List<Object> values;
            while ((values = listReader.read(getProcessors(training))) != null) {
//                System.out.println(String.format("lineNo=%s, rowNo=%s, data=%s", listReader.getLineNumber(), listReader.getRowNumber(), values));
                data.add(SimpleDataSample.newSimpleDataSample("SalesResult", header, values.toArray()));
            }
        }
        return data;
    }
    
    private static CellProcessor[] getProcessors(boolean training) {
        // TODO fix this is ugly
        if (training) {
            final CellProcessor[] processors = new CellProcessor[] { 
                    new Optional(new ParseInt()),
                    new Optional(new ParseBooleanLabel()),
                    new Optional(),
                    new Optional(),
                    new Optional(),
                    new Optional(),
                    new Optional(new ParseDouble()),
                    new Optional()
            };
            return processors;
        } else {
            final CellProcessor[] processors = new CellProcessor[] { 
            		new Optional(new ParseInt()),
                    new Optional(),
                    new Optional(),
                    new Optional(),
                    new Optional(),
                    new Optional(new ParseDouble()),
                    new Optional()
            };
            return processors;
        }
    }
    
    private static class ParseBooleanLabel extends ParseBool {
        
        public Object execute(final Object value, final CsvContext context) {
            Boolean parsed = (Boolean)super.execute(value, context);
            return parsed ? BooleanLabel.TRUE_LABEL : BooleanLabel.FALSE_LABEL;
        }
        
    }
    
}
