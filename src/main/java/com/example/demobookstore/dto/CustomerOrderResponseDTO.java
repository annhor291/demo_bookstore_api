package com.example.demobookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerOrderResponseDTO {
    private Long id;

    private Double totalAmount;

    private String ordersDate;

}
