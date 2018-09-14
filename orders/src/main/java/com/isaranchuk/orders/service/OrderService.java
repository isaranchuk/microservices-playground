package com.isaranchuk.orders.service;

import com.isaranchuk.orders.client.PhonesResponse;
import com.isaranchuk.orders.client.PhonesServiceClient;
import com.isaranchuk.orders.domain.Order;
import com.isaranchuk.orders.domain.Phone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OrderService {

    private final PhonesServiceClient phonesServiceClient;

    public OrderService(PhonesServiceClient phonesServiceClient) {
        this.phonesServiceClient = phonesServiceClient;
    }

    public void create(Order order) {
        BigDecimal totalPrice = BigDecimal.ZERO;
        order.getPhones().forEach(orderPhone -> {
            PhonesResponse.Phone phoneInCatalog = findPhoneInCatalog(orderPhone)
                    .orElseThrow(() -> new IllegalStateException("Phone in order doesn't match catalog: " + orderPhone));
            totalPrice.add(phoneInCatalog.getPrice());
        });
        log.info("Order created: {}, total price: {}", order, totalPrice);
    }

    private Optional<PhonesResponse.Phone> findPhoneInCatalog(Phone orderPhone) {
        List<PhonesResponse.Phone> catalog = phonesServiceClient.getPhones().getPhones();
        return catalog.stream()
                .filter(phone -> phone.getPhoneId().equals(orderPhone.getPhoneId()))
                .findAny();
    }
}
