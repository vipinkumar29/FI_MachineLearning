package com.deloitte.aiml.nb.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deloitte.aiml.nb.entity.NBEntity;
import com.deloitte.aiml.nb.form.NBForm;
import com.deloitte.aiml.nb.service.NBService;
import com.deloitte.aiml.repository.nb.NBRepository;

/**
 * 
 * @author soumsarkar
 *
 */
@Service
public class NBServiceImpl implements NBService{
	
	@Autowired
	NBRepository nbRepository;

	@Override
	public String saveNBPrediction(List<NBForm> nbFormList) {
		String status = "not saved";
		List<NBEntity> NBEntityList = new ArrayList<>();
		NBEntity nBEntity = null;
		for(NBForm nBFormItr : nbFormList){
			nBEntity = new NBEntity();
			nBEntity.setRowInstance(nBFormItr.getRowInstance());
			nBEntity.setPrediction(nBFormItr.getPrediction());
			NBEntityList.add(nBEntity);
		}
		if(null != nbRepository.save(NBEntityList))
			status = "Saved Success";
		return status;
	}

}
