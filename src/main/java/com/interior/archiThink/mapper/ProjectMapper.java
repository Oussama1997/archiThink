package com.interior.archiThink.mapper;

import com.interior.archiThink.dto.ProjectDto;
import com.interior.archiThink.model.Project;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {ClientMapper.class, InvoiceMapper.class}, unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ProjectMapper {
    ProjectDto toDTO(Project project);
    Project toEntity(ProjectDto dto);
    @Mapping(target = "id" ,ignore = true)
    void update(ProjectDto dto, @MappingTarget Project project);
}
