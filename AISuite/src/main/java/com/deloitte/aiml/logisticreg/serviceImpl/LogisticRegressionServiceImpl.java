package com.deloitte.aiml.logisticreg.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.aiml.linearreg.entity.LinearRegPredictionDataEntity;
import com.deloitte.aiml.logisticreg.entity.LogisticRegPredictionDataEntity;
import com.deloitte.aiml.logisticreg.service.LogisticRegressionService;
import com.deloitte.aiml.repository.logisticreg.LogisticRegPredictionDataRepository;

@Service
public class LogisticRegressionServiceImpl implements LogisticRegressionService{
	
	@Autowired
	LogisticRegPredictionDataRepository logisticRegPredictionDataRepository;

	@Override
	public String saveLogisticRegPredictedData(double yAxis1, double yAxis2,
			double xAxis) {
		String status = null;
		try{
			LogisticRegPredictionDataEntity logisticRegPredictionDataEntity = new LogisticRegPredictionDataEntity();
			logisticRegPredictionDataEntity.setyAxis1(yAxis1);
			logisticRegPredictionDataEntity.setyAxis2(yAxis2);
			logisticRegPredictionDataEntity.setxAxis(xAxis);
			LogisticRegPredictionDataEntity logisticRegPredictionDataEntityStat = logisticRegPredictionDataRepository.save(logisticRegPredictionDataEntity);
			if(logisticRegPredictionDataEntityStat != null){
				status = "Success";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return status;
	}

}
