package com.isaranchuk.orders.service;

import com.isaranchuk.orders.api.CreateOrderRequest;
import com.isaranchuk.orders.client.PhonesResponse;
import com.isaranchuk.orders.client.PhonesServiceClient;
import com.isaranchuk.orders.domain.Order;
import com.isaranchuk.orders.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class OrderService {

    private final PhonesServiceClient phonesServiceClient;

    private final OrderRepository orderRepository;

    public OrderService(PhonesServiceClient phonesServiceClient, OrderRepository orderRepository) {
        this.phonesServiceClient = phonesServiceClient;
        this.orderRepository = orderRepository;
    }

    public Mono<Order> create(CreateOrderRequest request) {
        return phonesServiceClient.getPhones()
                .map(phonesResponse -> getOrder(request, phonesResponse))
                .flatMap(orderRepository::insert);
    }

    private Order getOrder(CreateOrderRequest request, PhonesResponse phonesResponse) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        List<PhonesResponse.Phone> catalog = phonesResponse.getPhones();

        for (Long orderPhoneId : request.getPhoneIds()) {
            PhonesResponse.Phone phone = findPhoneInCatalog(orderPhoneId, catalog);
            totalPrice = totalPrice.add(phone.getPrice());
        }

        return Order.valueOf(request, totalPrice);
    }

    private PhonesResponse.Phone findPhoneInCatalog(Long phoneId, List<PhonesResponse.Phone> catalog) {
        return catalog.stream().filter(phone -> phone.getPhoneId().equals(phoneId)).findAny()
                        .orElseThrow(() -> new IllegalStateException("Invalid phone id: " + phoneId));
    }
}
