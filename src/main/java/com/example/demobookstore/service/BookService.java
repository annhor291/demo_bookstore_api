package com.example.demobookstore.service;

import com.example.demobookstore.dto.BookDTO;
import com.example.demobookstore.dto.CustomerBookDetailResponseDTO;
import com.example.demobookstore.dto.CustomerBookResponseDTO;
import com.example.demobookstore.projection.CustomerOrderProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    public Page<BookDTO> getAll (String keyword, Pageable pageable);
    public BookDTO getById(Long id);
    public BookDTO create(BookDTO dto);
    public BookDTO update(Long id, BookDTO dto);
    public void delete(Long id);
    List<CustomerBookResponseDTO> getBooksBoughtByCustomer(Long customerId);
    List<CustomerBookDetailResponseDTO> getBookDetailsByCustomer(Long customerId);
}
