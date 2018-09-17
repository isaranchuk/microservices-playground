package com.isaranchuk.orders.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "orders")
public class Order {
    private Long customerId;
    private List<Phone> phones;
}
