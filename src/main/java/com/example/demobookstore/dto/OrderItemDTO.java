package com.example.demobookstore.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long bookId;
    private int quantity;
}
