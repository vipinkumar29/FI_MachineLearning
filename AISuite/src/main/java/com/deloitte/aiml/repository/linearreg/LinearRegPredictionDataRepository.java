package com.deloitte.aiml.repository.linearreg;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.aiml.linearreg.entity.LinearRegPredictionDataEntity;

@Repository
public interface LinearRegPredictionDataRepository extends MongoRepository<LinearRegPredictionDataEntity, String>{

}
