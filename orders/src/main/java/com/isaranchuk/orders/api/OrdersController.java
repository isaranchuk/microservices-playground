package com.isaranchuk.orders.api;

import com.isaranchuk.orders.domain.Order;
import com.isaranchuk.orders.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrdersController {

    private final OrderService orderService;

    public OrdersController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/v1/orders")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(@RequestBody Order order) {
        orderService.create(order);
    }
}
