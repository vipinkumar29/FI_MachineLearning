package com.deloitte.aiml.kmeansclust.web.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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



import com.deloitte.aiml.kmeansclust.form.KMeansClustForm;

import weka.classifiers.Classifier;
import weka.classifiers.lazy.IBk;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;

@RestController
@RequestMapping(value="/aiSuit")
public class KMeansClustController {
	
		public static BufferedReader readDataFile(String filename) {
		BufferedReader inputReader = null;
			try {
			inputReader = new BufferedReader(new FileReader(filename));
			} catch (FileNotFoundException ex) {
		       System.err.println("File not found: " + filename);
		 }
		return inputReader;
		}
		@CrossOrigin(origins = "http://localhost:4200")
		@RequestMapping(value="/callKMeansClust", method = RequestMethod.POST)
		@ResponseBody
		public Object getSaveKMeansClustPrediction(@RequestParam("file") MultipartFile file,
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
			SimpleKMeans kmeans = new SimpleKMeans();
			kmeans.setSeed(10);
			//important parameter to set: preserver order, number of cluster.
			 kmeans.setPreserveInstancesOrder(true);
			kmeans.setNumClusters(5);
			BufferedReader datafile = readDataFile(file.getOriginalFilename());
			Instances data = new Instances(datafile);
			data.setClassIndex(data.numAttributes() - 1);
			kmeans.buildClusterer(data);
			
			Classifier ibk = new IBk(2);
			ibk.buildClassifier(data);
			double label1 = ibk.classifyInstance(data.lastInstance());
			data.lastInstance().setClassValue(label1);
			System.out.println("Prdeiction:=> "+data.lastInstance().stringValue(4));
			// This array returns the cluster number (starting with 0) for each instance
			// The array has as many elements as the number of instances
			int[] assignments = kmeans.getAssignments();
			int i=0;
			for(int clusterNum : assignments) {
				System.out.printf("Instance %d -> Cluster %d \n", i, clusterNum);
				i++;
			 	}
			List<KMeansClustForm> kMeansClustFormList = new ArrayList<>();
			for(int j=0; j<data.size(); j++){
				KMeansClustForm kMeansClustForm = new KMeansClustForm();
				kMeansClustForm.setAge(data.get(j).stringValue(0));
				kMeansClustForm.setSpectaclePrescrip(data.get(j).stringValue(1));
				kMeansClustForm.setAstigmatism(data.get(j).stringValue(2));
				kMeansClustForm.setTearProdRate(data.get(j).stringValue(3));
				kMeansClustForm.setContactLenses(data.get(j).stringValue(4));
				
				kMeansClustFormList.add(kMeansClustForm);
				System.out.println("--");
				
				System.out.println(data.lastInstance().stringValue(0));
				System.out.println(data.lastInstance().stringValue(1));
				System.out.println(data.lastInstance().stringValue(2));
				System.out.println(data.lastInstance().stringValue(3));
				System.out.println(data.lastInstance().stringValue(4));
			}
			File fileDel = new File(file.getOriginalFilename());
		    fileDel.delete();
		    
		    return kMeansClustFormList;
		}
}