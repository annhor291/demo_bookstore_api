package com.example.demobookstore.service;

import com.example.demobookstore.dto.BookDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    public Page<BookDTO> getAll (String keyword, Pageable pageable);
    public BookDTO getById(Long id);
    public BookDTO create(BookDTO dto);
    public BookDTO update(Long id, BookDTO dto);
    public void delete(Long id);
}
