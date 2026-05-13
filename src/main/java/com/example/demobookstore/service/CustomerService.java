package com.example.demobookstore.service;

import com.example.demobookstore.dto.CustomerDTO;
import com.example.demobookstore.dto.CustomerSearchResponseDTO;

import java.util.List;

public interface CustomerService {
    public List<CustomerDTO> getAll();
    public CustomerDTO getById(Long id);
    public CustomerDTO create(CustomerDTO dto);
    public CustomerDTO update(Long id, CustomerDTO dto);
    public void delete(Long id);
    List<CustomerSearchResponseDTO> searchCustomerByName(String name);
}
