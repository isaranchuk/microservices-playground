package com.isaranchuk.orders.domain;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Phone {
    private Integer phoneId;
    private String name;
    private BigDecimal price;
    private String description;
    private String imageUrl;
}
