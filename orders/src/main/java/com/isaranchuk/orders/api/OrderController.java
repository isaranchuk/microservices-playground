package com.isaranchuk.orders.api;

import com.isaranchuk.orders.domain.Order;
import com.isaranchuk.orders.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/v1/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Order> create(@Valid @RequestBody CreateOrderRequest request) {
        log.info("Creating new order: {}", request);
        return orderService.create(request);
    }
}
