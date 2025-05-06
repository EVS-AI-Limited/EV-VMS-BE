package com.evision.tms.service;

import com.evision.tms.entity.ManualTimeSheetEntity;

import java.util.List;
import java.util.Optional;

public interface ProjectDetailService {

	public ManualTimeSheetEntity add(ManualTimeSheetEntity manualTimeSheetEntity);
	public List<ManualTimeSheetEntity>getAll();
	public Optional<ManualTimeSheetEntity> getRecordById(Integer id);

}
