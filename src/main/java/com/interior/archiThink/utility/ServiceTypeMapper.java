package com.interior.archiThink.utility;

import com.interior.archiThink.dto.ServiceTypeDto;
import com.interior.archiThink.model.ServiceType;

public class ServiceTypeMapper {

    public static ServiceTypeDto mapToDTO(ServiceType serviceType) {
        if (serviceType == null) {
            return null;
        }
        ServiceTypeDto dto = new ServiceTypeDto();
        dto.setId(serviceType.getId());
        dto.setName(serviceType.getName());
        dto.setHourlyRate(serviceType.getHourlyRate());
        dto.setCreationDate(serviceType.getCreationDate());
        dto.setModificationDate(serviceType.getModificationDate());
        return dto;
    }

    public static ServiceType mapToEntity(ServiceTypeDto dto) {
        if (dto == null) {
            return null;
        }
        ServiceType serviceType = new ServiceType();
        serviceType.setId(dto.getId());
        serviceType.setName(dto.getName());
        serviceType.setHourlyRate(dto.getHourlyRate());
        // Map other fields if they exist
        return serviceType;
    }
}
