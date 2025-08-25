package com.interior.archiThink.dto;

import com.interior.archiThink.model.ProjectStatus;
import com.interior.archiThink.model.ProjectType;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectDto {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
    private LocalDate startDate;
    private LocalDate endDate;
    private ProjectStatus projectStatus;
    private BigDecimal budget;
    private ProjectType projectType;
    private ClientVDto client;
    private List<InvoiceDto> invoices = new ArrayList<>();
}
