package com.isaranchuk.phones.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class Phone {
    @Id
    private Integer phoneId;
    private String name;
    private BigDecimal price;
    private String description;
    private String imageUrl;
}
