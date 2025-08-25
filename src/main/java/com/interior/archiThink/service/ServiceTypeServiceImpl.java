package com.interior.archiThink.service;

import com.interior.archiThink.dto.ServiceTypeDto;
import com.interior.archiThink.mapper.ServiceTypeMapper;
import com.interior.archiThink.model.ServiceType;
import com.interior.archiThink.repository.ServiceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ServiceTypeServiceImpl implements ServiceTypeService{

    @Autowired
    ServiceTypeRepository serviceTypeRepository;
    @Autowired
    ServiceTypeMapper serviceTypeMapper;

    @Override
    public ServiceTypeDto saveServiceType(ServiceTypeDto serviceTypeDTO) {
        ServiceType savedServiceType = serviceTypeMapper.toEntity(serviceTypeDTO);
        serviceTypeRepository.save(savedServiceType);
        serviceTypeDTO.setId(savedServiceType.getId());
        return serviceTypeDTO;
    }

    @Override
    public ServiceTypeDto getServiceType(Long serviceTypeId) {
        ServiceType serviceType = serviceTypeRepository.findById(serviceTypeId).orElse(null);
        return serviceTypeMapper.toDTO(serviceType);
    }

    @Override
    public List<ServiceTypeDto> getServiceTypeList() {
        return serviceTypeRepository.findAll()
                .stream()
                .map(serviceTypeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ServiceTypeDto updateServiceType(ServiceTypeDto serviceTypeDTO, Long serviceTypeId) {
        ServiceType serTypeDB = serviceTypeRepository.findById(serviceTypeId).orElse(null);

        // Updates fields if they are not null or empty.
        if(Objects.nonNull(serTypeDB)){
            if (Objects.nonNull(serviceTypeDTO.getName()) && !"".equalsIgnoreCase(serviceTypeDTO.getName())) {
                serTypeDB.setName(serviceTypeDTO.getName());
            }
            if (Objects.nonNull(serviceTypeDTO.getHourlyRate())) {
                serTypeDB.setHourlyRate(serviceTypeDTO.getHourlyRate());
            }
            return serviceTypeMapper.toDTO(serviceTypeRepository.save(serTypeDB));
        }else
            return null;
    }

    @Override
    public Boolean deleteServiceTypeById(Long id) {
        ServiceType serviceType = serviceTypeRepository.findById(id).orElse(null);
        if (serviceType == null)
            return Boolean.FALSE;
        serviceTypeRepository.delete(serviceType);
        return Boolean.TRUE;
    }
}
