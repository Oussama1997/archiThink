package com.interior.archiThink.service;

import com.interior.archiThink.dto.ClientDto;
import com.interior.archiThink.mapper.ClientMapper;
import com.interior.archiThink.model.Client;
import com.interior.archiThink.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientMapper clientMapper;

    @Override
    public ClientDto saveClient(ClientDto clientDTO) {
        Client savedClient = clientMapper.toEntity(clientDTO);
        clientRepository.save(savedClient);
        clientDTO.setId(savedClient.getId());
        return clientDTO;
    }

    @Override
    public ClientDto getClient(Long clientId) {
        Client client = clientRepository.findById(clientId).orElse(null);
        return clientMapper.toDTO(client);
    }

    @Override
    public List<ClientDto> getClientList() {
        return clientRepository.findAll()
                .stream()
                .map(clientMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDto updateClient(ClientDto clientDto, Long clientId) {
        Client client = clientRepository.findById(clientId).orElse(null);
        if(client == null)
            return null;
        clientMapper.update(clientDto, client);
        clientRepository.save(client);
        return clientDto;

    }

    @Override
    public Boolean deleteClientById(Long id) {
        Client client = clientRepository.findById(id).orElse(null);
        if (client == null)
            return Boolean.FALSE;
        clientRepository.delete(client);
        return Boolean.TRUE;
    }
}
