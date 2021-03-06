package com.deloitte.aiml.apriori.web.controller;

import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.deloitte.aiml.apriori.form.AprioriForm;

import weka.associations.Apriori;
import weka.associations.AprioriItemSet;
import weka.classifiers.Classifier;
import weka.classifiers.functions.SMO;
import weka.classifiers.lazy.IBk;
import weka.core.FastVector;
import weka.core.Instances;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/aiSuit")
public class AprioriController {
    static Logger log = Logger.getLogger(AprioriController.class.getName());

    public static BufferedReader readDataFile(String filename) {
        BufferedReader inputReader = null;
        
        try {
            inputReader = new BufferedReader(new FileReader(filename));
        } catch (FileNotFoundException ex) {
        }
        return inputReader;
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/callApriori", method = RequestMethod.POST)
	@ResponseBody
	public Object getSaveAprioriClustPrediction(@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes) throws Exception{
    	
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
    	
    	 BufferedReader datafile = readDataFile(file.getOriginalFilename());
         
         Instances data = new Instances(datafile);
         data.setClassIndex(data.numAttributes() - 1);
         
         //Define ArrayList to Add Clustered Information
         Apriori apriori = new Apriori();
    	 apriori.setClassIndex(data.classIndex());
        // apriori.setNumRules(10);
         apriori.buildAssociations(data);
         //apriori.b
         System.out.println(data.lastInstance().stringValue(4));
         
         Classifier ibk = new IBk(2);
	     ibk.buildClassifier(data);
	     
	     double label1 = ibk.classifyInstance(data.lastInstance());
		 data.lastInstance().setClassValue(label1);
        
        log.debug("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-");
        log.debug("Number of Associations : " + apriori.getNumRules());
        log.debug("Adding Association Information to ArrayList ..");
        int num = apriori.getAllTheRules().length;
        System.out.println("Num :" + num);
        System.out.println(apriori.getAllTheRules().length);
        for (FastVector fastVector : apriori.getAllTheRules()){
        	//fastVector.get(index))AprioriItemSet premise = (AprioriItemSet) m_allTheRules[0].elementAt(i);
        	// consequence = (AprioriItemSet) m_allTheRules[1].elementAt(i); 
        	/**/
        	log.debug("rules " + fastVector.toArray());
        }
        System.out.println("*****************");
        List<AprioriForm> aprioriFormList = new ArrayList<>();
        for(int j=0; j<data.size(); j++){
        	AprioriForm aprioriForm = new AprioriForm();
        	aprioriForm.setAge(data.get(j).stringValue(0));
        	aprioriForm.setSpectaclePrescrip(data.get(j).stringValue(1));
        	aprioriForm.setAstigmatism(data.get(j).stringValue(2));
        	aprioriForm.setTearProdRate(data.get(j).stringValue(3));
        	aprioriForm.setContactLenses(data.get(j).stringValue(4));
        	aprioriFormList.add(aprioriForm);
			System.out.println("--");
        	System.out.println(data.get(j).stringValue(0));
        	System.out.println(data.get(j).stringValue(1));
        	System.out.println(data.get(j).stringValue(2));
        	System.out.println(data.get(j).stringValue(3));
        	System.out.println(data.get(j).stringValue(4));
        	System.out.println("----");
        }
        System.out.println("Last Index Prediction "+data.lastInstance().stringValue(4));
        System.out.println("*****************");
        System.out.println("associator => "+apriori);
        
        File fileDel = new File(file.getOriginalFilename());
	    fileDel.delete();
	    
	    return aprioriFormList;
    }
 
}
