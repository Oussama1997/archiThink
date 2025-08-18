package com.interior.archiThink.service;

import com.interior.archiThink.dto.ServiceTypeDto;

import java.util.List;

public interface ServiceTypeService {
    
    ServiceTypeDto saveServiceType(ServiceTypeDto serviceType);
    ServiceTypeDto getServiceType(Long serviceTypeId);
    List<ServiceTypeDto> getServiceTypeList();
    ServiceTypeDto updateServiceType(ServiceTypeDto serviceType, Long serviceTypeId);
    Boolean deleteServiceTypeById(Long serviceTypeId);
}
