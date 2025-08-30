package com.interior.archiThink.mapper;

import com.interior.archiThink.dto.UserDto;
import com.interior.archiThink.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    UserDto toDTO(User User);
    User toEntity(UserDto dto);
    void update(UserDto dto, @MappingTarget User user);
}
