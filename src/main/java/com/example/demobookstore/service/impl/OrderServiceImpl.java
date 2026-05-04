package com.example.demobookstore.service.impl;

import com.example.demobookstore.dto.OrderRequestDTO;
import com.example.demobookstore.dto.OrderDetailDTO;
import com.example.demobookstore.dto.OrderItemDTO;
import com.example.demobookstore.dto.OrderResponseDTO;
import com.example.demobookstore.entity.Book;
import com.example.demobookstore.entity.Customer;
import com.example.demobookstore.entity.Order;
import com.example.demobookstore.entity.OrderDetail;
import com.example.demobookstore.repository.BookRepository;
import com.example.demobookstore.repository.CustomerRepository;
import com.example.demobookstore.repository.OrderRepository;
import com.example.demobookstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;
    private final CustomerRepository customerRepository;

    // Chỉ dùng cho getAll() - không gọi orderDetail()
    private OrderResponseDTO toSummaryDTO(Order order) {
        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setCustomerName(order.getCustomer().getName());
        dto.setTotalAmount(order.getTotalAmount());
        // Không set items → không trigger lazy load
        return dto;
    }

    private OrderResponseDTO toDTO(Order order) {

        OrderResponseDTO dto = new OrderResponseDTO();
        dto.setId(order.getId());
        dto.setCustomerName(order.getCustomer().getName());
        dto.setTotalAmount(order.getTotalAmount());

        List<OrderDetailDTO> items = order.getOrderDetail()
                .stream()
                .map(d -> {
                    OrderDetailDTO item = new OrderDetailDTO();
                    item.setBookName(d.getBook().getTitle());
                    item.setQuantity(d.getQuantity());
                    item.setPrice(d.getPrice());
                    return item;
                })
                .toList();

        dto.setItems(items);
        return dto;
    }


    @Override
    public OrderRequestDTO create(OrderRequestDTO dto) {

            Customer customer = customerRepository.findById(dto.getCustomerId())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));

            Order order = new Order();
            order.setCustomer(customer);
            order.setOrderDate(LocalDateTime.now());

            List<OrderDetail> details = new ArrayList<>();
            double total = 0;

            for (OrderItemDTO item : dto.getItems()) {

                Book book = bookRepository.findById(item.getBookId())
                        .orElseThrow(() -> new RuntimeException("Book not found"));

                OrderDetail d = new OrderDetail();
                d.setBook(book);
                d.setOrder(order);
                d.setQuantity(item.getQuantity());
                d.setPrice(book.getPrice());

                total += item.getQuantity() * book.getPrice();
                details.add(d);
            }

            order.setOrderDetail(details);
            order.setTotalAmount(total);

            Order saved = orderRepository.save(order);

            // map lại DTO
            OrderRequestDTO result = new OrderRequestDTO();
            result.setId(saved.getId());
            result.setCustomerId(customer.getId());
            result.setTotalAmount(saved.getTotalAmount());

            return result;
    }

    @Override
    public List<OrderResponseDTO> getAll() {
            return orderRepository.findAll()
                    .stream()
                    .map(this::toSummaryDTO)
                    .toList();
    }

    @Override
    @Transactional
    public OrderResponseDTO getById(Long id) {
            Order order = orderRepository.findByIdWithDetails(id)
                    .orElseThrow(() -> new RuntimeException("Order not found"));

            System.out.println("DETAIL SIZE = " + order.getOrderDetail().size());

            return toDTO(order);
    }


}
