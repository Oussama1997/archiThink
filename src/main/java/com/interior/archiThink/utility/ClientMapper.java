package com.interior.archiThink.utility;

import com.interior.archiThink.dto.ClientDto;
import com.interior.archiThink.dto.ClientVDto;
import com.interior.archiThink.model.Client;

import static com.interior.archiThink.utility.Constants.SPACE;

public class ClientMapper {

    public static ClientDto mapToDTO(Client client) {
        if (client == null) {
            return null;
        }
        ClientDto dto = new ClientDto();
        dto.setId(client.getId());
        dto.setFirstName(client.getFirstName());
        dto.setLastName(client.getLastName());
        dto.setPhoneNumber(client.getPhoneNumber());
        dto.setAddress(client.getAddress());
        dto.setCreationDate(client.getCreationDate());
        dto.setModificationDate(client.getModificationDate());
        return dto;
    }

    public static Client mapToEntity(ClientDto dto) {
        if (dto == null) {
            return null;
        }
        Client client = new Client();
        client.setId(dto.getId());
        client.setFirstName(dto.getFirstName());
        client.setLastName(dto.getLastName());
        client.setPhoneNumber(dto.getPhoneNumber());
        client.setAddress(dto.getAddress());
        // Map other fields if they exist
        return client;
    }

    public static ClientVDto mapVToDTO(Client client) {
        if (client == null) {
            return null;
        }
        ClientVDto dto = new ClientVDto();
        dto.setId(client.getId());
        dto.setFullName(client.getFirstName()+ SPACE + client.getLastName());
        return dto;
    }

    public static Client mapVToEntity(ClientVDto dto) {
        if (dto == null) {
            return null;
        }
        Client client = new Client();
        client.setId(dto.getId());
        String[] tab = dto.getFullName().split(SPACE);
        client.setFirstName(tab[0]);
        client.setLastName(tab[1]);
        return client;
    }
}
