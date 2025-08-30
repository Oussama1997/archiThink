package com.interior.archiThink.dto;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClientDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private LocalDateTime creationDate;
    private LocalDateTime modificationDate;
}
