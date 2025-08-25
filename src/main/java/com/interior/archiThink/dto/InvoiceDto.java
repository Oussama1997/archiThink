package com.interior.archiThink.dto;

import com.interior.archiThink.model.InvoiceStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InvoiceDto {
    private Long id;
    private String name;
    private ClientVDto client;
    private ProjectVDto project;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
    private LocalDateTime paymentDate;
    private BigDecimal totalPrice;
    private InvoiceStatus status;
    private List<InvoiceItemDto> items;
}
