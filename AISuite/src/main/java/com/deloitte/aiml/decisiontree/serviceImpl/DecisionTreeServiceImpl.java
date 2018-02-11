package com.deloitte.aiml.decisiontree.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.aiml.decisiontree.DecisionTree;
import com.deloitte.aiml.decisiontree.Node;
import com.deloitte.aiml.decisiontree.data.DataSample;
import com.deloitte.aiml.decisiontree.entity.DTPredictionDataEntity;
import com.deloitte.aiml.decisiontree.entity.DecisionTreeEntity;
import com.deloitte.aiml.decisiontree.service.DecisionTreeService;
import com.deloitte.aiml.repository.decisiontree.DTPredictionDataRepository;
import com.deloitte.aiml.repository.decisiontree.DecisionTreeRepository;
import com.google.common.collect.Lists;

import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.api.ops.BaseAccumulation;
import org.nd4j.linalg.api.ops.impl.accum.distances.EuclideanDistance;
import org.nd4j.linalg.dataset.api.DataSet;
import org.nd4j.linalg.factory.Nd4j;

/**
 * 
 * @author soumsarkar
 *
 */
@Service
public class DecisionTreeServiceImpl implements DecisionTreeService{
	
	@Autowired
	DTPredictionDataRepository dtpredictionDataRepository;
	
	@Autowired
	DecisionTreeRepository decisionTreeRepository;
	
	
	@Override
	public String saveDecisionTree(Node tree) {
		try{
			DecisionTreeEntity decisionTreeEntity = new DecisionTreeEntity();
			decisionTreeEntity.setDecisionTree(tree);
			decisionTreeRepository.save(decisionTreeEntity);
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String savePrediction(List<DataSample> testingData, DecisionTree tree) {
		String status = null;
		List<String> predictions = Lists.newArrayList();
        // classify all test data
		List<DTPredictionDataEntity> predictionDataEntityList = new ArrayList<>();
		DTPredictionDataEntity predictionDataEntity = null;
        for (DataSample dataSample : testingData) {
        	predictionDataEntity = new DTPredictionDataEntity();
        	/*predictionDataEntity.setOrganizationId(String.valueOf(dataSample.getValue("OrganizationId").get()));
        	predictionDataEntity.setSalesResult(String.valueOf(tree.classify(dataSample).getPrintValue()));*/
        	predictionDataEntity.setAge(String.valueOf(dataSample.getValue("age").get()));
        	predictionDataEntity.setJob(String.valueOf(dataSample.getValue("job").get()));
        	predictionDataEntity.setY(String.valueOf(tree.classify(dataSample).getPrintValue()));
        	predictionDataEntityList.add(predictionDataEntity);
        	
        	
        	//predictions.add(dataSample.getValue("PassengerId").get() + "," + tree.classify(dataSample).getPrintValue());
        }
        System.out.println("predictionDataEntityList Size = "+ predictionDataEntityList.size());
        List<DTPredictionDataEntity> predictionDataEntitySave = dtpredictionDataRepository.save(predictionDataEntityList);
		if(predictionDataEntitySave != null ){
			status = "Success";
		}
		return status;
	}   
	
}
