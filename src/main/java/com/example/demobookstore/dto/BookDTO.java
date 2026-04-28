package com.example.demobookstore.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookDTO {
    private Long id;

    @NotBlank(message = "Title is required")
    private String title;

    private String author;

    @Min(value = 1, message = "Price must > 0")
    private double price;

    private int quantity;

    private String category;
}
