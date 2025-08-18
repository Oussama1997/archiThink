package com.interior.archiThink.controller;

import com.interior.archiThink.dto.ServiceTypeDto;
import com.interior.archiThink.service.ServiceTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/servicetypes")
public class ServiceTypeController {

    @Autowired
    private ServiceTypeServiceImpl serviceTypeImpl;

    @GetMapping
    public ResponseEntity<?> getAllServiceType() {
        List<ServiceTypeDto> serviceTypeList = serviceTypeImpl.getServiceTypeList();
        if (!serviceTypeList.isEmpty()) {
            return ResponseEntity.ok(serviceTypeList);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> createServiceType(@RequestBody ServiceTypeDto serviceType) {
        try {
            ServiceTypeDto created = serviceTypeImpl.saveServiceType(serviceType);
            return new ResponseEntity<>(created, HttpStatus.CREATED); // 201 for creation
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while creating the service type", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getServiceType(@PathVariable Long id) {
        ServiceTypeDto serviceTypeDTO = serviceTypeImpl.getServiceType(id);
        if(Objects.nonNull(serviceTypeDTO)) {
            return ResponseEntity.ok(serviceTypeDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<ServiceTypeDto> updateServiceType(@PathVariable Long id, @RequestBody ServiceTypeDto serviceType) {
        ServiceTypeDto updated = serviceTypeImpl.updateServiceType(serviceType, id);
        if(Objects.nonNull(updated)) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteServiceType(@PathVariable Long id) {
        if (serviceTypeImpl.deleteServiceTypeById(id)) {
            return ResponseEntity.ok("ServiceType deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
