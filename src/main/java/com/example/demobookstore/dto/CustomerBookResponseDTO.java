package com.example.demobookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerBookResponseDTO {
    private Long id;

    private String title;

    private Double price;
}
