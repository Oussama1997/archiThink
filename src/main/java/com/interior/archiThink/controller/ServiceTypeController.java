package com.interior.archiThink.controller;

import com.interior.archiThink.dto.ServiceTypeDto;
import com.interior.archiThink.service.ServiceTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@RestController
@RequestMapping("/servicetypes")
public class ServiceTypeController {

    @Autowired
    ServiceTypeServiceImpl serviceTypeImpl;

    @GetMapping
    public ResponseEntity<?> getAllServiceType() {
        List<ServiceTypeDto> serviceTypeList = serviceTypeImpl.getServiceTypeList();
        if (!serviceTypeList.isEmpty()) {
            return ResponseEntity.ok(serviceTypeList);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> createServiceType(@RequestBody ServiceTypeDto serviceTypeDto,
            UriComponentsBuilder uriBuilder) {
        ServiceTypeDto created = serviceTypeImpl.saveServiceType(serviceTypeDto);
        if(created==null)
            return ResponseEntity.badRequest().build();
        var uri = uriBuilder.path("client/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(created); // 201 for creation
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
        if(updated == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteServiceType(@PathVariable Long id) {
        if (serviceTypeImpl.deleteServiceTypeById(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
