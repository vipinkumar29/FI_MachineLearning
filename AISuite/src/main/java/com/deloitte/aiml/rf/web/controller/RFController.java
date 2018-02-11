package com.deloitte.aiml.rf.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.deloitte.aiml.rf.data.DataTable;
import com.deloitte.aiml.rf.trees.RandomForest;
import com.deloitte.aiml.rf.validation.RFForm;
import com.deloitte.aiml.rf.validation.Validation;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(value="/aiSuit")
public class RFController {
	
	public static final int MIN_LEAF_SIZE = 20;
	
	@CrossOrigin(origins = "http://localhost:4200")
	@RequestMapping(value="/callRf", method = RequestMethod.GET)
	@ResponseBody
	public Object getSaveRFPrediction(HttpServletRequest request,HttpServletResponse response,
			HttpSession session,RedirectAttributes redirectAttributes,ModelMap modelMap) throws Exception {
	
		/*if (file.isEmpty()) {
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
        }*/
		String status = null;
		RFController rfController =  new RFController();
		List<RFForm> rfFormList = rfController.anyNonStaticFunction();
		
		/*File fileDel = new File(file.getOriginalFilename());
        fileDel.delete();*/
		
		return rfFormList;
	}

	
	
	public List<RFForm> anyNonStaticFunction() throws IOException
	{
		// read file
		System.out.println("Start testIriskFoldCrossErrors");
		String fileName = "iris.csv";
		
		//URL url = this.getClass().getClassLoader().getResource("iris.csv");
		//URL url = this.getClass().getClassLoader().getResource("iris.csv");
		// end read file
		
		// make it into Data Table
		DataTable data = DataTable.loadCSV("C:\\Users\\vipinkumar\\Desktop\\FI_frontend\\iris.csv", null, null, null, true); 
		// end of make it into Data Table
		
		//making some default setting
		int minLeafSize = MIN_LEAF_SIZE;
		int n_estimators = 10;// number of trees in forest
		int nodeSampleSize = (int)(0.15 * data.size());
		nodeSampleSize = 111;
		double bootstrapSampleRate = 0.5;
		//end making some default setting
		
		// generate list of decision tree
		RandomForest rf = new RandomForest(n_estimators, minLeafSize, nodeSampleSize, bootstrapSampleRate);
		return Validation.kFoldCross(rf, data);		
		// end of generate list of decision tree
	}

}
