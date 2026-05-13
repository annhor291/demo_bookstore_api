package com.example.demobookstore.controller;

import com.example.demobookstore.dto.CustomerDTO;
import com.example.demobookstore.service.BookService;
import com.example.demobookstore.service.CustomerService;
import com.example.demobookstore.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    // GET ALL ORDER OF CUSTOMER BY ID
    private final OrderService orderService;
    @GetMapping("/{id}/orders")
    public ResponseEntity<?> getCustomerOrders(@PathVariable Long id) {

        return ResponseEntity.ok(
                orderService.getOrdersByCustomerId(id)
        );
    }

    // GET BOOKS BOUGHT BY CUSTOMER USING ID
    private final BookService bookService;
    @GetMapping("/{id}/books")
    public ResponseEntity<?> getBooksBoughtByCustomer(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                bookService.getBooksBoughtByCustomer(id)
        );
    }

    // GET BOOK DETAIL BY CUSTOMER
    @GetMapping("/{id}/books-detail")
    public ResponseEntity<?> getBookDetails(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                bookService.getBookDetailsByCustomer(id)
        );
    }

    // SEARCH CUSTOMER BY NAME
    @GetMapping("/search")
    public ResponseEntity<?> searchCustomer(
            @RequestParam String name
    ) {

        return ResponseEntity.ok(
                customerService.searchCustomerByName(name)
        );
    }
}
