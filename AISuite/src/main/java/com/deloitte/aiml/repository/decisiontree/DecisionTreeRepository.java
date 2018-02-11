package com.deloitte.aiml.repository.decisiontree;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.aiml.decisiontree.entity.DecisionTreeEntity;


/**
 * 
 * @author soumsarkar
 *
 */
@Repository
public interface DecisionTreeRepository extends MongoRepository<DecisionTreeEntity, String>{

}
