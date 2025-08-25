package com.interior.archiThink.controller;

import com.interior.archiThink.dto.ClientDto;
import com.interior.archiThink.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientServiceImpl clientTypeImpl;

    @GetMapping
    public ResponseEntity<?> getAllClient() {
        List<ClientDto> clientList = clientTypeImpl.getClientList();
        if (!clientList.isEmpty()) {
            return ResponseEntity.ok(clientList);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody ClientDto clientDTO,
            UriComponentsBuilder uriBuilder) {
        ClientDto created = clientTypeImpl.saveClient(clientDTO);
        if(created==null)
            return ResponseEntity.badRequest().build();
        var uri = uriBuilder.path("client/{id}").buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uri).body(created); // 201 for creation
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getClient(@PathVariable Long id) {
        ClientDto clientDTO = clientTypeImpl.getClient(id);
        if(Objects.nonNull(clientDTO)) {
            return ResponseEntity.ok(clientDTO);
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateClient(@PathVariable Long id, @RequestBody ClientDto clientDTO) {
        ClientDto updated = clientTypeImpl.updateClient(clientDTO, id);
        if(updated == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        if (clientTypeImpl.deleteClientById(id))
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}
