package com.isaranchuk.orders.domain;

import lombok.Data;

import java.util.List;

@Data
public class Order {
    private Long customerId;
    private List<Phone> phones;
}
