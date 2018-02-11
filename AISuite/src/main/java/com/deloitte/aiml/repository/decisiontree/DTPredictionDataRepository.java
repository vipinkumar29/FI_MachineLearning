package com.deloitte.aiml.repository.decisiontree;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.deloitte.aiml.decisiontree.entity.DTPredictionDataEntity;
/**
 * 
 * @author soumsarkar
 *
 */
@Repository
public interface DTPredictionDataRepository extends MongoRepository<DTPredictionDataEntity, String>{

	
}
