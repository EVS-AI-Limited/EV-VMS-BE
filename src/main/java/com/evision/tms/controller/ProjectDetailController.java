package com.evision.tms.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.evision.tms.entity.ManualTimeSheetEntity;
import com.evision.tms.service.impl.ProjectDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/Manual")
public class ProjectDetailController {
	@Autowired
	ProjectDetailServiceImpl projectDetailService;
	
	@PostMapping(value="/add", consumes = "application/json")
	public ResponseEntity<ManualTimeSheetEntity> addProjectInfo(@RequestBody ManualTimeSheetEntity manualTimeSheetEntity){
	ManualTimeSheetEntity proj=	projectDetailService.add(manualTimeSheetEntity);
		return new ResponseEntity<>(proj, HttpStatus.OK);
	}
	@GetMapping(value="/getAll") 
	public ResponseEntity<List<ManualTimeSheetEntity>> getAll(){
	List<ManualTimeSheetEntity> proj=	projectDetailService.getAll();
		return new ResponseEntity<>(proj, HttpStatus.OK);
	}
	@GetMapping(value="/getById/{id}") 
	public ResponseEntity<Optional<ManualTimeSheetEntity>> getById(@PathVariable("id") Integer id)throws SQLException{
	Optional<ManualTimeSheetEntity> proj=	projectDetailService.getRecordById(id);
		return new ResponseEntity<>(proj, HttpStatus.OK);
	}
	
}