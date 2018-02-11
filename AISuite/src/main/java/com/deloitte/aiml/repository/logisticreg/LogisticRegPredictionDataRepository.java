package com.deloitte.aiml.repository.logisticreg;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.aiml.logisticreg.entity.LogisticRegPredictionDataEntity;

@Repository
public interface LogisticRegPredictionDataRepository extends MongoRepository<LogisticRegPredictionDataEntity, String>{

}
