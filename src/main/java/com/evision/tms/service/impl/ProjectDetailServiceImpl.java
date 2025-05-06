package com.evision.tms.service.impl;

import java.util.List;
import java.util.Optional;

import com.evision.tms.entity.ManualTimeSheetEntity;
import com.evision.tms.repository.ProjectDetailRepository;
import com.evision.tms.service.ProjectDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




@Service
public class ProjectDetailServiceImpl implements ProjectDetailService {
	
@Autowired
ProjectDetailRepository projectDetailRepository;

	@Override
	public ManualTimeSheetEntity add(ManualTimeSheetEntity manualTimeSheetEntity)  {
		return projectDetailRepository.save(manualTimeSheetEntity);
	}

	@Override
	public List<ManualTimeSheetEntity> getAll(){
		return projectDetailRepository.findAll();
	}

	@Override
	public Optional<ManualTimeSheetEntity> getRecordById(Integer id)  {
		Optional<ManualTimeSheetEntity> project =projectDetailRepository.findById(id);
	 if (project.isPresent()) {
         System.out.println(project.get());
     } else {
         System.out.printf("No employee found with id %d%n",project.get() );
     }
	return project;

}

}
