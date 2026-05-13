package com.example.demobookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerBookDetailResponseDTO {
    private String title;

    private Integer totalQuantity;
}
