package com.interior.archiThink.service;

import com.interior.archiThink.dto.ProjectDto;

import java.util.List;

public interface ProjectService {
    ProjectDto saveProject(ProjectDto project);
    ProjectDto getProject(Long projectId);
    List<ProjectDto> getProjectList();
    ProjectDto updateProject(ProjectDto ProjectDTO, Long projectId);
    Boolean deleteProjectById(Long projectId);
}
