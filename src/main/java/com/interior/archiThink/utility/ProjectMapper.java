package com.interior.archiThink.utility;

import com.interior.archiThink.dto.ProjectDto;
import com.interior.archiThink.dto.ProjectVDto;
import com.interior.archiThink.model.Project;

public class ProjectMapper {

    public static ProjectDto mapToDTO(Project project) {
        if (project == null) {
            return null;
        }
        ProjectDto dto = new ProjectDto();
        dto.setId(project.getId());
        dto.setName(project.getName());
        dto.setDescription(project.getDescription());
        dto.setProjectStatus(project.getProjectStatus());
        dto.setProjectType(project.getProjectType());
        dto.setBudget(project.getBudget());
        dto.setStartDate(project.getStartDate());
        dto.setEndDate(project.getEndDate());
        dto.setCreationDate(project.getCreationDate());
        dto.setModificationDate(project.getModificationDate());
        dto.setClient(ClientMapper.mapVToDTO(project.getClient()));
        /*if (Objects.nonNull(project.getInvoices()) && !project.getInvoices().isEmpty()){
            dto.setInvoices(project.getInvoices().stream().map(InvoiceMapper::mapToDTO)
                    .collect(Collectors.toList()));
        }*/
        return dto;
    }

    public static Project mapToEntity(ProjectDto dto) {
        if (dto == null) {
            return null;
        }
        Project project = new Project();
        project.setId(dto.getId());
        project.setName(dto.getName());
        project.setDescription(dto.getDescription());
        project.setProjectStatus(dto.getProjectStatus());
        project.setProjectType(dto.getProjectType());
        project.setBudget(dto.getBudget());
        project.setStartDate(dto.getStartDate());
        project.setEndDate(dto.getEndDate());
        /*if (Objects.nonNull(dto.getInvoices()) && !dto.getInvoices().isEmpty()){
            project.setInvoices(dto.getInvoices().stream().map(InvoiceMapper::mapToEntity)
                    .collect(Collectors.toList()));
        }*/
        // Map other fields if they exist
        return project;
    }

    public static ProjectVDto mapVToDTO(Project project) {
        if (project == null) {
            return null;
        }
        ProjectVDto dto = new ProjectVDto();
        dto.setId(project.getId());
        dto.setName(project.getName());
        return dto;
    }

    public static Project mapVToEntity(ProjectVDto dto) {
        if (dto == null) {
            return null;
        }
        Project project = new Project();
        project.setId(dto.getId());
        project.setName(dto.getName());
        return project;
    }
}
