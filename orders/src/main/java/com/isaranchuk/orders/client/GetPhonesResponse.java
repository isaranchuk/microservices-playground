package com.isaranchuk.orders.client;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GetPhonesResponse {
    private List<Phone> phones;

    @Data
    public static class Phone {
        private Long phoneId;
        private BigDecimal price;
    }
}
