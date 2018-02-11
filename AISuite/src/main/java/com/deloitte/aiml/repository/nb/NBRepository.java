package com.deloitte.aiml.repository.nb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.deloitte.aiml.nb.entity.NBEntity;

/**
 * 
 * @author soumsarkar
 *
 */
@Repository
public interface NBRepository extends MongoRepository<NBEntity, String>{

}
