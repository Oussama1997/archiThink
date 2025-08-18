package com.interior.archiThink.mapper;

import com.interior.archiThink.dto.ClientVDto;
import com.interior.archiThink.dto.ServiceTypeDto;
import com.interior.archiThink.dto.ServiceTypeVDto;
import com.interior.archiThink.model.Client;
import com.interior.archiThink.model.ServiceType;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static com.interior.archiThink.utilities.Constants.SPACE;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServiceTypeMapper {

    ServiceTypeDto toDTO(ServiceType serviceType);
    ServiceType toEntity(ServiceTypeDto dto);

    default ServiceTypeVDto toVDTO(ServiceType serviceType) {
        if (serviceType == null) {
            return null;
        }
        ServiceTypeVDto serviceTypeVDto = new ServiceTypeVDto();
        serviceTypeVDto.setId(serviceType.getId());
        serviceTypeVDto.setName(serviceType.getName());
        return serviceTypeVDto;
    }
}
