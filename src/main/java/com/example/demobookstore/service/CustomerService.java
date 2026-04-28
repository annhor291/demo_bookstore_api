package com.example.demobookstore.service;

import com.example.demobookstore.dto.CustomerDTO;
import com.example.demobookstore.entity.Customer;
import com.example.demobookstore.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    // mapping dto <-> entity
    private CustomerDTO toDTO(Customer c) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(c.getId());
        dto.setName(c.getName());
        dto.setEmail(c.getEmail());
        dto.setPhone(c.getPhone());
        return dto;
    }

    private Customer toEntity(CustomerDTO dto) {
        Customer c = new Customer();
        c.setName(dto.getName());
        c.setEmail(dto.getEmail());
        c.setPhone(dto.getPhone());
        return c;
    }

    //crud api
    public List<CustomerDTO> getAll() {
        return customerRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public CustomerDTO getById(Long id) {
        Customer c = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return toDTO(c);
    }

    public CustomerDTO create(CustomerDTO dto) {
        Customer c = toEntity(dto);
        return toDTO(customerRepository.save(c));
    }

    public CustomerDTO update(Long id, CustomerDTO dto) {
        Customer c = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        c.setName(dto.getName());
        c.setEmail(dto.getEmail());
        c.setPhone(dto.getPhone());

        return toDTO(customerRepository.save(c));
    }

    public void delete(Long id) {
        customerRepository.deleteById(id);
    }
}
