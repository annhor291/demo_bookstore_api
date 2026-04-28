package com.example.demobookstore.service;

import com.example.demobookstore.dto.BookDTO;
import com.example.demobookstore.entity.Book;
import com.example.demobookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    private BookDTO toDTO(Book b) {
        BookDTO dto = new BookDTO();
        dto.setId(b.getId());
        dto.setTitle(b.getTitle());
        dto.setAuthor(b.getAuthor());
        dto.setPrice(b.getPrice());
        dto.setQuantity(b.getQuantity());
        dto.setCategory(b.getCategory());
        return dto;
    }

    private Book toEntity(BookDTO dto) {
        Book b = new Book();
        b.setTitle(dto.getTitle());
        b.setAuthor(dto.getAuthor());
        b.setPrice(dto.getPrice());
        b.setQuantity(dto.getQuantity());
        b.setCategory(dto.getCategory());
        return b;
    }

    // CRUD API
    // GET ALL + SEARCH + PAGINATION
    public Page<BookDTO> getAll(String keyword, Pageable pageable) {
        Page<Book> page;

        if (keyword != null && !keyword.isEmpty()) {
            page = bookRepository.findByTitleContaining(keyword, pageable);
        } else {
            page = bookRepository.findAll(pageable);
        }

        return page.map(this::toDTO);
    }

    // GET BY ID
    public BookDTO getById(Long id) {
        Book b = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return toDTO(b);
    }

    // CREATE
    public BookDTO create(BookDTO dto) {
        Book b = toEntity(dto);
        return toDTO(bookRepository.save(b));
    }

    // UPDATE
    public BookDTO update(Long id, BookDTO dto) {
        Book b = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        b.setTitle(dto.getTitle());
        b.setAuthor(dto.getAuthor());
        b.setPrice(dto.getPrice());
        b.setQuantity(dto.getQuantity());
        b.setCategory(dto.getCategory());

        return toDTO(bookRepository.save(b));
    }

    // DELETE
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

}
