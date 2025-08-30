package com.interior.archiThink.utility;

import com.interior.archiThink.dto.InvoiceDto;
import com.interior.archiThink.dto.InvoiceItemDto;
import com.interior.archiThink.mapper.ServiceTypeMapper;
import com.interior.archiThink.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class InvoiceMapper {

    @Autowired
    private ServiceTypeMapper serviceTypeMapper;

    // ===== Mapping Methods =====
    public static InvoiceDto mapToDTO(Invoice invoice) {
        InvoiceDto dto = new InvoiceDto();
        dto.setId(invoice.getId());
        dto.setName(invoice.getName());
        dto.setClient(ClientMapper.mapVToDTO(invoice.getClient()));
        dto.setProject(ProjectMapper.mapVToDTO(invoice.getProject()));
        dto.setCreationDate(invoice.getCreationDate());
        dto.setModificationDate(invoice.getModificationDate());
        dto.setCreationDate(invoice.getPaymentDate());
        dto.setStatus(invoice.getStatus());
        if (Objects.nonNull(invoice.getItems()) && !invoice.getItems().isEmpty()){
            dto.setItems(
                    invoice.getItems().stream()
                            .map(InvoiceMapper::mapItemToDTO)
                            .collect(Collectors.toList())
            );
        }
        dto.setTotalPrice(invoice.getTotalPrice());
        return dto;
    }

    public static Invoice mapToEntity(InvoiceDto dto) {
        Invoice invoice = new Invoice();
        invoice.setId(dto.getId());
        invoice.setName(dto.getName());
        invoice.setPaymentDate(dto.getPaymentDate());
        invoice.setStatus(dto.getStatus());
        if (Objects.nonNull(dto.getItems()) && !dto.getItems().isEmpty()){
            invoice.setItems(
                    dto.getItems().stream()
                            .map(InvoiceMapper::mapItemToEntity)
                            .collect(Collectors.toList())
            );
        }
        invoice.setTotalPrice(dto.getTotalPrice());
        return invoice;
    }

    private static InvoiceItemDto mapItemToDTO(InvoiceItem item) {
        InvoiceItemDto dto = new InvoiceItemDto();
        dto.setId(item.getId());
        dto.setQuantity(item.getQuantity());
        dto.setItemType(item.getItemType());

        if (item instanceof ServiceInvoiceItem serviceItem) {
            //dto.setServiceType(serviceItem.getServiceType());
            dto.setUnitPrice(serviceItem.getServiceType().getHourlyRate());
            dto.setPrice(serviceItem.getPrice());

        } else if (item instanceof ProductInvoiceItem productItem) {
            dto.setProduct(productItem.getProduct());
            dto.setUnitPrice(productItem.getUnitPrice());
            dto.setPrice(productItem.getPrice());
        } else {
            throw new RuntimeException("Unknown InvoiceItem subtype");
        }
        return dto;
    }

    public static InvoiceItem mapItemToEntity(InvoiceItemDto dto) {
        InvoiceItem entity;
        if (dto.getItemType() == ItemType.PRODUIT) {
            ProductInvoiceItem productItem = new ProductInvoiceItem();
            productItem.setProduct(dto.getProduct());
            entity = productItem;
        } else if (dto.getItemType() == ItemType.SERVICE) {
            ServiceInvoiceItem serviceItem = new ServiceInvoiceItem();
            //serviceItem.setServiceType(dto.getServiceType());
            entity = serviceItem;
        } else {
            throw new IllegalArgumentException("Unknown itemType: " + dto.getItemType());
        }
        entity.setId(dto.getId());
        entity.setQuantity(dto.getQuantity());
        entity.setPrice(dto.getPrice());
        entity.setItemType(dto.getItemType());
        return entity;
    }
}
