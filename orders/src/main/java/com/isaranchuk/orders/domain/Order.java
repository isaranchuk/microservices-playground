package com.isaranchuk.orders.domain;

import com.isaranchuk.orders.api.CreateOrderRequest;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Data
@Document(collection = "orders")
public class Order {
    private String firstName;
    private String lastName;
    private String email;
    private BigDecimal totalPrice;
    private List<Long> phoneIds;

    public static Order valueOf(CreateOrderRequest request, BigDecimal totalPrice) {
        Order order = new Order();
        order.setFirstName(request.getFirstName());
        order.setLastName(request.getLastName());
        order.setPhoneIds(request.getPhoneIds());
        order.setEmail(request.getEmail());
        order.setTotalPrice(totalPrice);
        return order;
    }
}
