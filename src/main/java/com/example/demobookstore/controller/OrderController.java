package com.example.demobookstore.controller;

import com.example.demobookstore.dto.OrderRequestDTO;
import com.example.demobookstore.dto.OrderResponseDTO;
import com.example.demobookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public OrderRequestDTO create(@RequestBody OrderRequestDTO dto) {
        return orderService.create(dto);
    }

    @GetMapping
    public List<OrderResponseDTO> getAll() {
        return orderService.getAll();
    }

    @GetMapping("/{id}")
    public OrderResponseDTO getById(@PathVariable Long id) {
        return orderService.getById(id);
    }
}
