package com.evision.tms.service.impl;

import com.evision.tms.dto.ProjectDTO;
import com.evision.tms.dto.UserDetailResponseDTO;
import com.evision.tms.entity.ProjectEntity;
import com.evision.tms.repository.ProjectRepository;
import com.evision.tms.service.ProjectSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjectServiceImpl implements ProjectSer {
    @Autowired
    ProjectRepository projectRepository;
    @Override
    public List<UserDetailResponseDTO> getAllProjects() {
        return getAllProjects();
    }

    @Override
    public UserDetailResponseDTO getProject(int projectId) {
        return null;
    }



    @Override
    public ProjectEntity createProject(ProjectEntity project) {


        return projectRepository.save(project);
    }


    @Override
    public UserDetailResponseDTO updateProject(int projectId, ProjectDTO projectDTO) {
        return null;
    }

    @Override
    public void deleteProject(int projectId) {

    }
}
