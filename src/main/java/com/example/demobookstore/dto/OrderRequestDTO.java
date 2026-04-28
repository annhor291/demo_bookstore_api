package com.example.demobookstore.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {
    private Long id;

    private Long customerId;

    private List<OrderItemDTO> items;

    private double totalAmount;
}
