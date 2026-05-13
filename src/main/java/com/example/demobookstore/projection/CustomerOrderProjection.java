package com.example.demobookstore.projection;

import java.time.LocalDateTime;

public interface CustomerOrderProjection {
    Long getId();

    Double getTotalAmount();

    LocalDateTime getOrdersDate();
}
