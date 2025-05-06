package com.evision.tms.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProjectDTO {

    @NotNull
        private int projectId;
        private String projectName;


    }


