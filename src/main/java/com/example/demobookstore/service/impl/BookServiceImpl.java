package com.example.demobookstore.service.impl;

import com.example.demobookstore.dto.BookDTO;
import com.example.demobookstore.entity.Book;
import com.example.demobookstore.repository.BookRepository;
import com.example.demobookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
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


    @Override
    public Page<BookDTO> getAll(String keyword, Pageable pageable) {
            Page<Book> page;

            if (keyword != null && !keyword.isEmpty()) {
                page = bookRepository.findByTitleContaining(keyword, pageable);
            } else {
                page = bookRepository.findAll(pageable);
            }

            return page.map(this::toDTO);
    }

    @Override
    public BookDTO getById(Long id) {
            Book b = bookRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Book not found"));
            return toDTO(b);
    }

    @Override
    public BookDTO create(BookDTO dto) {
            Book b = toEntity(dto);
            return toDTO(bookRepository.save(b));
    }

    @Override
    public BookDTO update(Long id, BookDTO dto) {
            Book b = bookRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Book not found"));

            if(dto.getAuthor() != null){
                b.setAuthor(dto.getAuthor());
            }
            if(dto.getTitle() != null){
                b.setTitle(dto.getTitle());
            }
            if(dto.getPrice() != null){
                b.setPrice(dto.getPrice());
            }
            if(dto.getQuantity() != null){
                b.setQuantity(dto.getQuantity());
            }
            if(dto.getCategory() != null){
                b.setCategory(dto.getCategory());
            }
            if(dto.getId() != null){
                b.setId(dto.getId());
            }


            return toDTO(bookRepository.save(b));
    }

    @Override
    public void delete(Long id) {
            bookRepository.deleteById(id);
    }




}
