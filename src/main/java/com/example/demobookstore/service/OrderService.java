package com.example.demobookstore.service;

import com.example.demobookstore.dto.CustomerOrderResponseDTO;
import com.example.demobookstore.dto.OrderRequestDTO;
import com.example.demobookstore.dto.OrderResponseDTO;

import java.util.List;

public interface OrderService {
    public OrderRequestDTO create(OrderRequestDTO dto);
    public List<OrderResponseDTO> getAll();
    public OrderResponseDTO getById(Long id);
    List<CustomerOrderResponseDTO> getOrdersByCustomerId(Long customerId);
}
