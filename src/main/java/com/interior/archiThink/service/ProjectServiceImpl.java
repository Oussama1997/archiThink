package com.interior.archiThink.service;

import com.interior.archiThink.dto.ProjectDto;
import com.interior.archiThink.mapper.ClientMapper;
import com.interior.archiThink.mapper.ProjectMapper;
import com.interior.archiThink.model.Client;
import com.interior.archiThink.model.Project;
import com.interior.archiThink.repository.ClientRepository;
import com.interior.archiThink.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService{

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ProjectMapper projectMapper;

    @Override
    public ProjectDto saveProject(ProjectDto projectDTO) {
        Project p = projectMapper.toEntity(projectDTO);
        Client client = clientRepository.findById(projectDTO.getClient().getId()).orElse(null);
        p.setClient(client);
        Project project = projectRepository.save(p);
        return projectMapper.toDTO(project);
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
        Project projectDB = projectRepository.findById(projectId).orElse(null);

        // Updates fields if they are not null or empty.
        if(Objects.nonNull(projectDB)){
            if (Objects.nonNull(projectDTO.getName()) && !"".equalsIgnoreCase(projectDTO.getName())) {
                projectDB.setName(projectDTO.getName());
            }
            if (Objects.nonNull(projectDTO.getDescription()) && !"".equalsIgnoreCase(projectDTO.getDescription())) {
                projectDB.setDescription(projectDTO.getDescription());
            }
            if (Objects.nonNull(projectDTO.getStartDate())) {
                projectDB.setStartDate(projectDTO.getStartDate());
            }
            if (Objects.nonNull(projectDTO.getEndDate())) {
                projectDB.setEndDate(projectDTO.getEndDate());
            }
            if (Objects.nonNull(projectDTO.getProjectStatus())) {
                projectDB.setProjectStatus(projectDTO.getProjectStatus());
            }
            if (Objects.nonNull(projectDTO.getProjectType())) {
                projectDB.setProjectType(projectDTO.getProjectType());
            }
            if (Objects.nonNull(projectDTO.getBudget())) {
                projectDB.setBudget(projectDTO.getBudget());
            }
            if (Objects.nonNull(projectDTO.getClient())) {
                Client client = clientRepository.findById(projectDTO.getClient().getId()).orElse(null);
                projectDB.setClient(client);
            }
            Project project = projectRepository.save(projectDB);
            return projectMapper.toDTO(project);
        }else
            return null;
    }

    @Override
    public Boolean deleteProjectById(Long id) {
        Optional<Project> ProjectOpt = projectRepository.findById(id);
        if (ProjectOpt.isPresent()) {
            projectRepository.deleteById(id);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
