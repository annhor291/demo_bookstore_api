package com.example.demobookstore.repository;

import com.example.demobookstore.entity.Customer;
import com.example.demobookstore.projection.CustomerSearchProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = """
        SELECT
            c.id,
            c.name,
            c.email
        FROM customer c
        WHERE c.name LIKE %:name%
        """, nativeQuery = true)
    List<CustomerSearchProjection> searchCustomerByName(
            @Param("name") String name
    );
}
