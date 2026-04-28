package com.example.demobookstore.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderResponseDTO {
    private Long id;
    private String customerName;
    private double totalAmount;
    private List<OrderDetailDTO> items;
}
