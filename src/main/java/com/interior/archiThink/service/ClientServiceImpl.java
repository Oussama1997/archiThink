package com.interior.archiThink.service;

import com.interior.archiThink.dto.ClientDto;
import com.interior.archiThink.mapper.ClientMapper;
import com.interior.archiThink.model.Client;
import com.interior.archiThink.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientMapper clientMapper;

    @Override
    public ClientDto saveClient(ClientDto clientDTO) {
        Client client = clientMapper.toEntity(clientDTO);
        return clientMapper.toDTO(clientRepository.save(client));
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
    public ClientDto updateClient(ClientDto client, Long clientId) {
        Client clientDB = clientRepository.findById(clientId).orElse(null);

        // Updates fields if they are not null or empty.
        if(Objects.nonNull(clientDB)){
            if (Objects.nonNull(client.getFirstName()) && !"".equalsIgnoreCase(client.getFirstName())) {
                clientDB.setFirstName(client.getFirstName());
            }
            if (Objects.nonNull(client.getLastName()) && !"".equalsIgnoreCase(client.getLastName())) {
                clientDB.setLastName(client.getLastName());
            }
            if (Objects.nonNull(client.getPhoneNumber()) && !"".equalsIgnoreCase(client.getPhoneNumber())) {
                clientDB.setPhoneNumber(client.getPhoneNumber());
            }
            if (Objects.nonNull(client.getAddress()) && !"".equalsIgnoreCase(client.getAddress())) {
                clientDB.setAddress(client.getAddress());
            }
            return clientMapper.toDTO(clientRepository.save(clientDB));
        }else
            return null;
    }

    @Override
    public Boolean deleteClientById(Long id) {
        Optional<Client> clientOpt = clientRepository.findById(id);
        if (clientOpt.isPresent()) {
            clientRepository.deleteById(id);
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
}
