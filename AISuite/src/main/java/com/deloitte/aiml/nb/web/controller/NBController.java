package com.deloitte.aiml.nb.web.controller;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.deloitte.aiml.nb.form.NBForm;
import com.deloitte.aiml.nb.service.NBService;

import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

@RestController
@RequestMapping(value="/aiSuit")
public class NBController {
	
	@Autowired
	NBService nbService;

	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/callNb", method = RequestMethod.POST)
	@ResponseBody
    public Object getSaveNBPrediction(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) throws Exception {

		String status = null;
        ConverterUtils.DataSource source1 = new ConverterUtils.DataSource("weather.arff");
        Instances train = source1.getDataSet();
        // setting class attribute if the data format does not provide this information
        // For example, the XRFF format saves the class attribute information as well
        if (train.classIndex() == -1)
            train.setClassIndex(train.numAttributes() - 1);

       // ConverterUtils.DataSource source2 = new ConverterUtils.DataSource("C:\\Users\\soumsarkar\\Documents\\New folder\\readTxt\\weatherTest.arff");
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
        ConverterUtils.DataSource source2 = new ConverterUtils.DataSource(file.getOriginalFilename());
        Instances test = source2.getDataSet();
        // setting class attribute if the data format does not provide this information
        // For example, the XRFF format saves the class attribute information as well
        if (test.classIndex() == -1)
            test.setClassIndex(train.numAttributes() - 1);

        // model

        NaiveBayes naiveBayes = new NaiveBayes();
        naiveBayes.buildClassifier(train);

        // this does the trick  
        for(int i=0; i<test.numInstances(); i++){
        	double label = naiveBayes.classifyInstance(test.instance(i));
        	test.instance(i).setClassValue(label);
        	System.out.println(test.instance(i).stringValue(4));
        }
        /*double label0 = naiveBayes.classifyInstance(test.instance(0));
        double label1 = naiveBayes.classifyInstance(test.instance(1));
        test.instance(0).setClassValue(label0);
        test.instance(1).setClassValue(label1);

        System.out.println(test.instance(0).stringValue(4));
        System.out.println(test.instance(1).stringValue(4));*/
        
        NBForm nbForm = null;
        List<NBForm> nbFormList = new ArrayList<>();
        
        
        System.out.println("***********"+ test.numAttributes() + "&&&&&& " + test.numInstances());
        for(int i=0; i<test.numInstances(); i++){
        	nbForm = new NBForm();
        	nbForm.setRowInstance(i+1);
        	nbForm.setOutlook(test.instance(i).stringValue(0));
        	nbForm.setTemperature(test.instance(i).value(1));
        	nbForm.setHumidity(test.instance(i).value(2));
        	nbForm.setWindy(test.instance(i).stringValue(3));
        	nbForm.setPrediction(test.instance(i).stringValue(4));
        	nbFormList.add(nbForm);
        }
       // status = nbService.saveNBPrediction(nbFormList);
        
        File fileDel = new File(file.getOriginalFilename());
        fileDel.delete();
        
        return nbFormList;
    }
}
