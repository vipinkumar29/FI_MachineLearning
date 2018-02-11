package com.deloitte.aiml.decisiontree.web.controller;

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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
import com.deloitte.aiml.decisiontree.form.DTVo;
import com.deloitte.aiml.decisiontree.label.BooleanLabel;
import com.deloitte.aiml.decisiontree.service.DecisionTreeService;
import com.deloitte.aiml.kaggle.titatic.DecisionTreeExecutionPlan;
import com.google.common.collect.Lists;

import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequestMapping(value="/aiSuit")
public class DecisionTreeController {
	
	@Autowired
	DecisionTreeService decisionTreeService;
	
	String UPLOADED_FOLDER = "C:/Users/soumsarkar/Documents/AI/AISuite/";
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/callDT", method = RequestMethod.POST)
	@ResponseBody
	public Object getSaveDTPrediction(@RequestParam("file") MultipartFile file,
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
		String status = null;
		List<DataSample> trainingData = readData(true);
        DecisionTree tree = new DecisionTree();
        
        List<Feature> features = getFeatures();
        
        tree.train(trainingData, features);
        
        // print tree after training
        tree.printTree();
        decisionTreeService.saveDecisionTree(tree.getRoot());
        
        // read test data
        List<DataSample> testingData = readData(false);
        List<String> predictions = Lists.newArrayList();
        List<DTVo> dtVoList = Lists.newArrayList();
        DTVo dtVo = null;
        // classify all test data
        for (DataSample dataSample : testingData) {
            predictions.add(dataSample.getValue("age").get() + "," + dataSample.getValue("job").get() + ","+ tree.classify(dataSample).getPrintValue());
        }
        
        // write predictions to file
        FileWriter fileWriter = new FileWriter(new File(file.getOriginalFilename().replaceAll(".csv", "Predictions.csv")));
        fileWriter.append("age,job,y").append("\n");
        //predictions.add(0, "age,job,y");
        for (String prediction : predictions) {
            fileWriter.append(prediction).append("\n");
            dtVo = new DTVo();
            String[] stringArr = prediction.split(",");
            dtVo.setAge(stringArr[0]);
            dtVo.setJob(stringArr[1]);
            if(stringArr[2]=="1" || stringArr[2].equalsIgnoreCase("1")){
            	dtVo.setPrediction("yes");
            }else if(stringArr[2]=="0" || stringArr[2].equalsIgnoreCase("0")){
            	dtVo.setPrediction("no");
            }
            dtVoList.add(dtVo);
            
        }
        fileWriter.flush();
        fileWriter.close();
        
       // status = decisionTreeService.savePrediction(testingData, tree);
        
        File fileDel = new File(file.getOriginalFilename());
        fileDel.delete();
        return dtVoList;
		
	}
	
	 private static List<Feature> getFeatures() {
	        /*Feature firstClassPassenger = newFeature("Pclass", 1);
	        Feature secondClassPassenger = newFeature("Pclass", 2);
	        Feature thirdClassPassenger = newFeature("Pclass", 3);*/
	        /*Feature isMale = newFeature("Type", "Internet");
	        Feature isFemale = newFeature("Type", "Service");
	        Feature ageLessThan10 = newFeature("YoB", lessThanD(10.0), "less than 10");
	        Feature ageBewteen10And30 = newFeature("YoB", betweenD(10.0, 30.0), "between 10 and 30");
	        Feature ageBewteen30And50 = newFeature("YoB", betweenD(30.0, 50.0), "between 30 and 50");
	        Feature ageBewteen50And60 = newFeature("YoB", betweenD(50.0, 60.0), "between 50 and 60");
	        Feature ageMoreThan60 = newFeature("YoB", moreThanD(60.0), "more than 60");
	        Feature hasSiblings = newFeature("SibSp", moreThan(0), "more than 0");
	        Feature moreThan2Siblings = newFeature("SibSp", moreThan(2), "more than 2");
	        Feature hasParentsChildren = newFeature("Parch", moreThan(0), "more than 0");
	        Feature moreThan2Children = newFeature("Parch", moreThan(2), "more than 2");
	        Feature fareMoreThan7 = newFeature("Revenue", lessThanD(7.89), "less than 7.89");
	        Feature fareBetween7And15 = newFeature("Revenue", betweenD(7.89, 15.78), "between 7.89 and 15.78");
	        Feature fareBetween15And23 = newFeature("Revenue", betweenD(15.78, 23.67), "between 15.78 and 23.67");
	        Feature fareBetween23And31 = newFeature("Revenue", betweenD(23.67, 31.56), "between 23.67 and 31.56");
	        Feature fareBetween31And71 = newFeature("Revenue", betweenD(31.56, 71.01), "between 31.56 and 71.01");
	        Feature fareMoreThan71 = newFeature("Revenue", moreThanD(71.01), "more than 71.01");
	        Feature cabinA = newFeature("Cabin", startsWith("A"), "starts with A");
	        Feature cabinB = newFeature("Cabin", startsWith("B"), "starts with B");
	        Feature cabinC = newFeature("Cabin", startsWith("C"), "starts with C");
	        Feature cabinD = newFeature("Cabin", startsWith("D"), "starts with D");
	        Feature cabinE = newFeature("Cabin", startsWith("E"), "starts with E");
	        Feature cabinF = newFeature("Cabin", startsWith("F"), "starts with F");
	        Feature embarkedC = newFeature("EmbarkedMilestone", "C");
	        Feature embarkedS = newFeature("EmbarkedMilestone", "S");
	        Feature embarkedQ = newFeature("EmbarkedMilestone", "Q");*/
	        
	        Feature isMarried = newFeature("marital","married");
	        Feature isSingle = newFeature("marital","single");
	        Feature isDivorced = newFeature("marital","divorced");
	        
	        Feature eduPrimary = newFeature("education","primary");
	        Feature eduSecondary = newFeature("education","secondary");
	        Feature eduTertiary = newFeature("education","tertiary");
	        Feature eduUnknown = newFeature("education","unknown");
	        
	        
	        Feature housingYes = newFeature("housing","yes");
	        Feature housingNo = newFeature("housing","no");
	        
	        
	        Feature loanYes = newFeature("loan","yes");
	        Feature loanNo = newFeature("loan","no");
	        
	        
	        Feature poutcomeUn = newFeature("poutcome","unknown");
	        Feature poutcomeSu = newFeature("poutcome","success");
	        Feature poutcomeFa = newFeature("poutcome","failure");
	        
	        
	        return Arrays.asList(/*firstClassPassenger, secondClassPassenger, thirdClassPassenger,*/ /*isMale, isFemale,*/ /*hasSiblings, moreThan2Siblings,
	                hasParentsChildren, moreThan2Children,*/ /*ageLessThan10, ageBewteen10And30, ageBewteen30And50, ageBewteen50And60, ageBewteen50And60, ageMoreThan60,
	                fareMoreThan7, fareBetween7And15, fareBetween15And23, fareBetween23And31, fareBetween31And71, fareMoreThan71, fareMoreThan71, cabinA, cabinB, cabinC,
	                cabinD, cabinE, cabinF, embarkedC, embarkedS, embarkedQ*/isMarried,isSingle,isDivorced,eduPrimary,eduSecondary,
	                eduTertiary,eduUnknown,housingYes,housingNo,loanYes,loanNo,poutcomeUn,poutcomeSu,poutcomeFa);
	    }
	    
	    private static List<DataSample> readData(boolean training) throws IOException {
	        List<DataSample> data = Lists.newArrayList();
	        String filename = training ? "bankTrain.csv" : "bankTest.csv";
	        InputStreamReader stream = new InputStreamReader(DecisionTreeExecutionPlan.class.getResourceAsStream(filename));
	        try (ICsvListReader listReader = new CsvListReader(stream, CsvPreference.STANDARD_PREFERENCE);) {
	            
	            // the header elements are used to map the values to the bean (names must match)
	            final String[] header = listReader.getHeader(true);
	            
	            List<Object> values;
	            while ((values = listReader.read(getProcessors(training))) != null) {
	            	System.out.println("----------------> "+values.get(0).toString());
//	                System.out.println(String.format("lineNo=%s, rowNo=%s, data=%s", listReader.getLineNumber(), listReader.getRowNumber(), values));
	                data.add(SimpleDataSample.newSimpleDataSample("y", header, values.toArray()));
	            }
	        }
	        return data;
	    }
	    
	    private static CellProcessor[] getProcessors(boolean training) {
	        // TODO fix this is ugly
	        if (training) {
	            final CellProcessor[] processors = new CellProcessor[] { 
	            		new Optional(new ParseInt()),
	                    new Optional(),
	                    new Optional(),
	                    new Optional(),
	                    new Optional(),
	                    new Optional(new ParseDouble()),
	                    new Optional(),
	                    new Optional(),
	                    new Optional(),
	                    new Optional(new ParseDouble()),
	                    new Optional(),
	                    new Optional(new ParseDouble()),
	                    new Optional(new ParseInt()),
	                    new Optional(new ParseDouble()),
	                    new Optional(new ParseInt()),
	                    new Optional(),
	                    new Optional(new ParseBooleanLabel())
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
	                    new Optional(),
	                    new Optional(),
	                    new Optional(),
	                    new Optional(new ParseDouble()),
	                    new Optional(),
	                    new Optional(new ParseDouble()),
	                    new Optional(new ParseInt()),
	                    new Optional(new ParseDouble()),
	                    new Optional(new ParseInt()),
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
