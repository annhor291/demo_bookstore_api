package com.example.demobookstore.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "book")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private double price;
    private int quantity;
    private String category;

    @OneToMany(mappedBy = "book")
    private List<OrderDetail> orderDetailList;

}
