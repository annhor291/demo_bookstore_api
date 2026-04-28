package com.example.demobookstore.repository;

import com.example.demobookstore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.orderDetail od WHERE o.id = :id")
    Optional<Order> findByIdWithDetails(@Param("id") Long id);
}
