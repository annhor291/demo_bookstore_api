package com.example.demobookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerSearchResponseDTO {
    private Long id;
    private String name;
    private String email;
}
