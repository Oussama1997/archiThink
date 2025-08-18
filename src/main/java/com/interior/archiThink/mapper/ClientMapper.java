package com.interior.archiThink.mapper;

import com.interior.archiThink.dto.ClientDto;
import com.interior.archiThink.dto.ClientVDto;
import com.interior.archiThink.dto.InvoiceItemDto;
import com.interior.archiThink.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static com.interior.archiThink.utilities.Constants.SPACE;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClientMapper {
    ClientDto toDTO(Client client);
    Client toEntity(ClientDto dto);

    default ClientVDto toVDTO(Client client) {
        if (client == null) {
            return null;
        }
        ClientVDto clientVDto = new ClientVDto();
        clientVDto.setId(client.getId());
        clientVDto.setFullName(client.getFirstName() + SPACE + client.getLastName());
        return clientVDto;
    }
}
