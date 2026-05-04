package com.example.demobookstore.controller;

import com.example.demobookstore.dto.BookDTO;
import com.example.demobookstore.service.BookService;
import com.example.demobookstore.service.impl.BookServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    // GET ALL
    @GetMapping
    public Page<BookDTO> getAll(
            @RequestParam(required = false) String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "price") String sort
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return bookService.getAll(keyword, pageable);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public BookDTO getById(@PathVariable Long id) {
        return bookService.getById(id);
    }

    // CREATE
    @PostMapping
    public BookDTO create(@Valid @RequestBody BookDTO dto) {
        return bookService.create(dto);
    }

    // UPDATE
    @PutMapping("/{id}")
    public BookDTO update(@PathVariable Long id,
                          @RequestBody BookDTO dto) {

        return bookService.update(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bookService.delete(id);
    }
}
