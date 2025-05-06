package com.evision.tms.service;

import com.evision.tms.dto.ProjectDTO;
import com.evision.tms.dto.UserDetailResponseDTO;
import com.evision.tms.entity.ProjectEntity;

import java.util.List;

public interface ProjectSer {
     List<UserDetailResponseDTO> getAllProjects();

        UserDetailResponseDTO getProject(int projectId);

        ProjectEntity createProject(ProjectEntity project);



    public UserDetailResponseDTO updateProject(int projectId, ProjectDTO projectDTO);

        void deleteProject(int projectId);


    }


