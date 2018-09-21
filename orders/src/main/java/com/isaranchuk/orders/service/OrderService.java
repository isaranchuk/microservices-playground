package com.isaranchuk.orders.service;

import com.isaranchuk.orders.api.CreateOrderRequest;
import com.isaranchuk.orders.client.GetPhonesResponse;
import com.isaranchuk.orders.client.PhonesServiceClient;
import com.isaranchuk.orders.domain.Order;
import com.isaranchuk.orders.exception.UnknownPhoneException;
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
                .map(getPhonesResponse -> getOrder(request, getPhonesResponse))
                .flatMap(orderRepository::insert);
    }

    private Order getOrder(CreateOrderRequest request, GetPhonesResponse getPhonesResponse) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        List<GetPhonesResponse.Phone> catalog = getPhonesResponse.getPhones();

        for (Long orderPhoneId : request.getPhoneIds()) {
            GetPhonesResponse.Phone phone = findPhoneInCatalog(orderPhoneId, catalog);
            totalPrice = totalPrice.add(phone.getPrice());
        }

        return Order.valueOf(request, totalPrice);
    }

    private GetPhonesResponse.Phone findPhoneInCatalog(Long phoneId, List<GetPhonesResponse.Phone> catalog) {
        return catalog.stream().filter(phone -> phone.getPhoneId().equals(phoneId)).findAny()
                        .orElseThrow(() -> new UnknownPhoneException("Unknown phone id: " + phoneId));
    }
}
