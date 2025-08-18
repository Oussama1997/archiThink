package com.interior.archiThink.service;

import com.interior.archiThink.dto.ServiceTypeDto;
import com.interior.archiThink.mapper.ServiceTypeMapper;
import com.interior.archiThink.model.ServiceType;
import com.interior.archiThink.repository.ServiceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ServiceTypeServiceImpl implements ServiceTypeService{

    @Autowired
    private ServiceTypeRepository serviceTypeRepository;
    @Autowired
    private ServiceTypeMapper serviceTypeMapper;

    @Override
    public ServiceTypeDto saveServiceType(ServiceTypeDto serviceTypeDTO) {
        ServiceType serviceType = serviceTypeRepository.save(serviceTypeMapper.toEntity(serviceTypeDTO));
        return serviceTypeMapper.toDTO(serviceType);
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
        Optional<ServiceType> serviceTypeOpt = serviceTypeRepository.findById(id);
        if (serviceTypeOpt.isPresent()) {
            serviceTypeRepository.deleteById(id);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
