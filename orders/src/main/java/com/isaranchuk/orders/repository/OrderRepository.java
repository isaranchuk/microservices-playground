package com.isaranchuk.orders.repository;

import com.isaranchuk.orders.domain.Order;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface OrderRepository extends ReactiveMongoRepository<Order, String> {
}
