package com.deloitte.aiml.nb.service;

import java.util.List;

import com.deloitte.aiml.nb.form.NBForm;

/**
 * 
 * @author soumsarkar
 *
 */
public interface NBService {
	
	public String saveNBPrediction(List<NBForm> nbFormList);
}
