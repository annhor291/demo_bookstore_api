package com.example.demobookstore.controller;

import com.example.demobookstore.dto.CustomerDTO;
import com.example.demobookstore.service.CustomerService;
import com.example.demobookstore.service.impl.CustomerServiceIpml;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    // GET ALL
    @GetMapping
    public List<CustomerDTO> getAll() {
        return customerService.getAll();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public CustomerDTO getById(@PathVariable Long id) {
        return customerService.getById(id);
    }

    // CREATE
    @PostMapping
    public CustomerDTO create(@Valid @RequestBody CustomerDTO dto) {
        return customerService.create(dto);
    }

    // UPDATE
    @PutMapping("/{id}")
    public CustomerDTO update(@PathVariable Long id,
                              @RequestBody CustomerDTO dto) {
        return customerService.update(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        customerService.delete(id);
    }
}
