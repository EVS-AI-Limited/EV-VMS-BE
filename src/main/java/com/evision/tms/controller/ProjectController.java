package com.evision.tms.controller;

import com.evision.tms.entity.ProjectEntity;
import com.evision.tms.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectRepository projectRepository;

    @GetMapping
    public List<ProjectEntity> getAllProjects() {
        return projectRepository.findAll();
    }

//    @GetMapping("/{id}")
//    public ProjectEntity<Project> getProjectById(@PathVariable(value = "id") Long projectId) {
//        Project project = projectRepository.findById(projectId)
//                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));
//
//        return ResponseEntity.ok(project);
//    }
//
    @PostMapping("/createProject")
    public ProjectEntity createProject(@RequestBody ProjectEntity  project) {

        return projectRepository.save(project);
    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Project> updateProject(@PathVariable(value = "id") Long projectId,
//                                                 @RequestBody Project projectDetails) {
//        Project project = projectRepository.findById(projectId)
//                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));
//
//        project.setName(projectDetails.getName());
//        project.setDescription(projectDetails.getDescription());
//
//        Project updatedProject = projectRepository.save(project);
//        return ResponseEntity.ok(updatedProject);
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteProject(@PathVariable(value = "id") Long projectId) {
//        Project project = projectRepository.findById(projectId)
//                .orElseThrow(() -> new ResourceNotFoundException("Project not found with id: " + projectId));
//
//        projectRepository.delete(project);
//        return ResponseEntity.noContent().build();
//    }
}

