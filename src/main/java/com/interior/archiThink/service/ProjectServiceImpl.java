package com.interior.archiThink.service;

import com.interior.archiThink.dto.ProjectDto;
import com.interior.archiThink.mapper.ProjectMapper;
import com.interior.archiThink.model.Client;
import com.interior.archiThink.model.Project;
import com.interior.archiThink.repository.ClientRepository;
import com.interior.archiThink.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ProjectMapper projectMapper;

    @Override
    public ProjectDto saveProject(ProjectDto projectDTO) {
        Client client = clientRepository.findById(projectDTO.getClient().getId()).orElse(null);
        if(client==null){
            return null;
        }
        Project savedProject = projectMapper.toEntity(projectDTO);
        savedProject.setClient(client);
        projectRepository.save(savedProject);
        projectDTO.setId(savedProject.getId());
        return projectDTO;
    }

    @Override
    public ProjectDto getProject(Long projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        return  projectMapper.toDTO(project);
    }

    @Override
    public List<ProjectDto> getProjectList() {
        return projectRepository.findAll()
                .stream()
                .map(projectMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProjectDto updateProject(ProjectDto projectDTO, Long projectId) {
        Project project = projectRepository.findById(projectId).orElse(null);
        if(project == null)
            return null;
        Client client = clientRepository.findById(projectDTO.getClient().getId()).orElse(null);
        if(client == null)
            return null;
        projectMapper.update(projectDTO, project);
        project.setClient(client);
        projectRepository.save(project);
        projectDTO.setId(project.getId());
        return projectDTO;
    }

    @Override
    public Boolean deleteProjectById(Long id) {
        Project project = projectRepository.findById(id).orElse(null);
        if (project == null)
            return Boolean.FALSE;
        projectRepository.delete(project);
        return Boolean.TRUE;
    }
}
