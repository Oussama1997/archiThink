package com.interior.archiThink.mapper;

import com.interior.archiThink.dto.ProjectDto;
import com.interior.archiThink.model.Project;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", uses = {ClientMapper.class, InvoiceMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMapper {
    ProjectDto toDTO(Project project);
    Project toEntity(ProjectDto dto);
}
