package com.deloitte.aiml.linearreg.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.aiml.linearreg.entity.LinearRegPredictionDataEntity;
import com.deloitte.aiml.linearreg.service.LinearRegressionService;
import com.deloitte.aiml.repository.linearreg.LinearRegPredictionDataRepository;

@Service
public class LinearRegressionServiceImpl implements LinearRegressionService{
	
	@Autowired
	LinearRegPredictionDataRepository linearRegPredictionDataRepository;

	@Override
	public String saveLinearRegPredictedData(double yAxis1, double yAxis2, double xAxis) {
		String status = null;
		try{
			LinearRegPredictionDataEntity linearRegPredictionDataEntity = new LinearRegPredictionDataEntity();
			linearRegPredictionDataEntity.setyAxis1(yAxis1);
			linearRegPredictionDataEntity.setyAxis2(yAxis2);
			linearRegPredictionDataEntity.setxAxis(xAxis);
			LinearRegPredictionDataEntity linearRegPredictionDataEntityStat = linearRegPredictionDataRepository.save(linearRegPredictionDataEntity);
			if(linearRegPredictionDataEntityStat != null){
				status = "Success";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return status;
	}

}
