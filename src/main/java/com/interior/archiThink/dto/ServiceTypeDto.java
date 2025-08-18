package com.interior.archiThink.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ServiceTypeDto {
    private Long id;
    private String name;
    private BigDecimal hourlyRate;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
}
