package com.interior.archiThink.dto;

import com.interior.archiThink.model.ItemType;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InvoiceItemDto {
    private Long id;
    private int quantity;
    private BigDecimal unitPrice;
    private ItemType itemType; // "SERVICE" or "PRODUCT"
    private ServiceTypeDto serviceType;  // nullable
    private String product;      // nullable
    private BigDecimal price;
}
