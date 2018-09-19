package com.isaranchuk.orders.service;

import com.isaranchuk.orders.client.PhonesResponse;
import com.isaranchuk.orders.client.PhonesServiceClient;
import com.isaranchuk.orders.domain.Order;
import com.isaranchuk.orders.domain.Phone;
import com.isaranchuk.orders.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.Disposable;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@Service
public class OrderService {

    private final PhonesServiceClient phonesServiceClient;

    private final OrderRepository orderRepository;

    public OrderService(PhonesServiceClient phonesServiceClient, OrderRepository orderRepository) {
        this.phonesServiceClient = phonesServiceClient;
        this.orderRepository = orderRepository;
    }

    public Mono<Order> create(Order order) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        return phonesServiceClient.getPhones()
                .map(phonesResponse -> {
                    List<PhonesResponse.Phone> phones = phonesResponse.getPhones();
                    log.debug("Phones: ", phones);
                    order.getPhones().forEach(orderPhone -> {
                        // validate if it's a correct phone
                        totalPrice.add(orderPhone.getPrice());
                    });
                    return order;
                })
                .publish(orderMono -> orderRepository.insert(order));
    }
}
