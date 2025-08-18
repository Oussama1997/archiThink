package com.interior.archiThink.mapper;

import com.interior.archiThink.dto.InvoiceDto;
import com.interior.archiThink.dto.InvoiceItemDto;
import com.interior.archiThink.model.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = {ClientMapper.class, ProjectMapper.class, ServiceTypeMapper.class},
        unmappedTargetPolicy = ReportingPolicy.IGNORE)public interface InvoiceMapper {

    // ===== Invoice Mapping =====
    InvoiceDto toDto(Invoice entity);
    Invoice toEntity(InvoiceDto dto);

    // ===== InvoiceItem Mapping =====
    @Mapping(target = "serviceType", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "unitPrice", ignore = true)
    InvoiceItemDto toItemDto(InvoiceItem entity);

    default InvoiceItem toItemEntity(InvoiceItemDto dto) {
        if (dto == null) {
            return null;
        }
        if (dto.getItemType() == ItemType.SERVICE) {
            ServiceInvoiceItem service = new ServiceInvoiceItem();
            //service.setServiceType(dto.getServiceType());
            service.setQuantity(dto.getQuantity());
            service.setPrice(dto.getPrice());
            service.setItemType(dto.getItemType());
            service.setId(dto.getId());
            return service;
        } else if (dto.getItemType() == ItemType.PRODUIT) {
            ProductInvoiceItem product = new ProductInvoiceItem();
            product.setProduct(dto.getProduct());
            product.setUnitPrice(dto.getUnitPrice());
            product.setQuantity(dto.getQuantity());
            product.setPrice(dto.getPrice());
            product.setItemType(dto.getItemType());
            product.setId(dto.getId());
            return product;
        }
        throw new IllegalArgumentException("Unknown item type: " + dto.getItemType());
    }
    // ===== List Mappings =====
    List<InvoiceDto> toDtoList(List<Invoice> entities);
    List<Invoice> toEntityList(List<InvoiceDto> dtos);

    List<InvoiceItemDto> toItemDtoList(List<InvoiceItem> entities);
    List<InvoiceItem> toItemEntityList(List<InvoiceItemDto> dtos);

    // ===== Custom logic for subclass creation =====
    @AfterMapping
    default void mapExtraItemFields(InvoiceItem entity, @MappingTarget InvoiceItemDto dto) {
        if (entity instanceof ServiceInvoiceItem service) {
            //dto.setServiceType(service.getServiceType());
            dto.setUnitPrice(service.getServiceType() != null ? service.getServiceType().getHourlyRate() : null);
        } else if (entity instanceof ProductInvoiceItem product) {
            dto.setProduct(product.getProduct());
            dto.setUnitPrice(product.getUnitPrice());
        }
    }

    @AfterMapping
    default void mapExtraItemEntities(InvoiceItemDto dto, @MappingTarget InvoiceItem entity) {
        if (dto.getItemType() == ItemType.SERVICE) {
            ServiceInvoiceItem service = new ServiceInvoiceItem();
            //service.setServiceType(dto.getServiceType());
            service.setQuantity(dto.getQuantity());
            service.setPrice(dto.getPrice());
            service.setItemType(dto.getItemType());
            service.setId(dto.getId());
            entity = service;
        } else if (dto.getItemType() == ItemType.PRODUIT) {
            ProductInvoiceItem product = new ProductInvoiceItem();
            product.setProduct(dto.getProduct());
            product.setUnitPrice(dto.getUnitPrice());
            product.setQuantity(dto.getQuantity());
            product.setPrice(dto.getPrice());
            product.setItemType(dto.getItemType());
            product.setId(dto.getId());
            entity = product;
        }
    }
}