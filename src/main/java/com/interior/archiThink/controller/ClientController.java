package com.interior.archiThink.controller;

import com.interior.archiThink.dto.ClientDto;
import com.interior.archiThink.service.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientServiceImpl clientTypeImpl;

    @GetMapping
    public ResponseEntity<?> getAllClient() {
        List<ClientDto> clientList = clientTypeImpl.getClientList();
        if (!clientList.isEmpty()) {
            return ResponseEntity.ok(clientList);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody ClientDto clientDTO) {
        try {
            ClientDto created = clientTypeImpl.saveClient(clientDTO);
            return new ResponseEntity<>(created, HttpStatus.CREATED); // 201 for creation
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while creating the client", HttpStatus.INTERNAL_SERVER_ERROR);
        }
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
        try{
            ClientDto updated = clientTypeImpl.updateClient(clientDTO, id);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred while creating the client", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteClient(@PathVariable Long id) {
        if (clientTypeImpl.deleteClientById(id)) {
            return ResponseEntity.ok("Client deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
