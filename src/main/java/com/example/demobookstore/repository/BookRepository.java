package com.example.demobookstore.repository;

import com.example.demobookstore.entity.Book;
import com.example.demobookstore.projection.CustomerBookDetailProjection;
import com.example.demobookstore.projection.CustomerBookProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long> {
    Page<Book> findByTitleContaining(String keyword, Pageable pageable);

    @Query(value = """
        SELECT DISTINCT
            b.id,
            b.title,
            b.price
        FROM order_detail od
        JOIN orders o ON od.order_id = o.id
        JOIN book b ON od.book_id = b.id
        WHERE o.customer_id = :customerId
        """, nativeQuery = true)
    List<CustomerBookProjection> findBooksBoughtByCustomer(
            @Param("customerId") Long customerId
    );

    @Query(value = """
        SELECT
            b.title,
            SUM(od.quantity) AS totalQuantity
        FROM order_detail od
        JOIN orders o ON od.order_id = o.id
        JOIN book b ON od.book_id = b.id
        WHERE o.customer_id = :customerId
        GROUP BY b.title
        """, nativeQuery = true)
    List<CustomerBookDetailProjection> getBookDetailsByCustomer(
            @Param("customerId") Long customerId
    );
}
