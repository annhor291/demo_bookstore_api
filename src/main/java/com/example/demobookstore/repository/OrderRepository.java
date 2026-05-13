package com.example.demobookstore.repository;

import com.example.demobookstore.entity.Order;
import com.example.demobookstore.projection.CustomerOrderProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.orderDetail od WHERE o.id = :id")
    Optional<Order> findByIdWithDetails(@Param("id") Long id);

    @Query(value = """
        SELECT
            o.id,
            o.total_amount AS totalAmount,
            o.orders_date AS ordersDate
        FROM orders o
        WHERE o.customer_id = :customerId
        ORDER BY o.orders_date DESC
        """, nativeQuery = true)
    List<CustomerOrderProjection> findOrdersByCustomerId(
            @Param("customerId") Long customerId
    );
}
