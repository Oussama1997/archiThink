package com.interior.archiThink.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServiceTypeDto {
    private Long id;
    private String name;
    private BigDecimal hourlyRate;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
}
