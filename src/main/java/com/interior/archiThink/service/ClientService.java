package com.interior.archiThink.service;

import com.interior.archiThink.dto.ClientDto;

import java.util.List;

public interface ClientService {
    ClientDto saveClient(ClientDto client);
    ClientDto getClient(Long clientId);
    List<ClientDto> getClientList();
    ClientDto updateClient(ClientDto client, Long clientId);
    Boolean deleteClientById(Long clientId);
}
