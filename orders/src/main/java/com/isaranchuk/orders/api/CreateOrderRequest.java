package com.isaranchuk.orders.api;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class CreateOrderRequest {

    @NotBlank
    @Length(max = 50)
    private String firstName;

    @NotBlank
    @Length(max = 50)
    private String lastName;

    @NotEmpty
    private List<Long> phoneIds;
}
