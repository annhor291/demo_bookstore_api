package com.example.demobookstore.service.impl;

import com.example.demobookstore.dto.CustomerDTO;
import com.example.demobookstore.entity.Customer;
import com.example.demobookstore.repository.CustomerRepository;
import com.example.demobookstore.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceIpml implements CustomerService {
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

    @Override
    public List<CustomerDTO> getAll() {
            return customerRepository.findAll()
                    .stream()
                    .map(this::toDTO)
                    .toList();
    }

    @Override
    public CustomerDTO getById(Long id) {
        Customer c = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        return toDTO(c);
    }


    @Override
    public CustomerDTO create(CustomerDTO dto) {
        Customer c = toEntity(dto);
        return toDTO(customerRepository.save(c));
    }


    @Override
    public CustomerDTO update(Long id, CustomerDTO dto) {
        Customer c = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        if(dto.getName() != null){
            c.setName(dto.getName());
        }
        if(dto.getEmail() != null){
            c.setName(dto.getEmail());
        }
        if(dto.getPhone() != null){
            c.setPhone(dto.getPhone());
        }
        if(dto.getId() != null){
            c.setId(dto.getId());
        }

        return toDTO(customerRepository.save(c));
    }


    @Override
    public void delete(Long id) {
            customerRepository.deleteById(id);
    }


}
