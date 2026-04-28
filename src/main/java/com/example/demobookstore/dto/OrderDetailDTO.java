package com.example.demobookstore.dto;

import lombok.Data;

@Data
public class OrderDetailDTO {
    private String bookName;
    private int quantity;
    private double price;
}
