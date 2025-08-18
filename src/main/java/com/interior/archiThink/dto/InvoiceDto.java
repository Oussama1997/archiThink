package com.interior.archiThink.dto;

import com.interior.archiThink.model.InvoiceStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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
