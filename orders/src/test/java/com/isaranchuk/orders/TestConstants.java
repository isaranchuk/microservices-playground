package com.isaranchuk.orders;

import com.isaranchuk.orders.api.CreateOrderRequest;
import com.isaranchuk.orders.client.GetPhonesResponse;
import com.isaranchuk.orders.domain.Order;
import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@UtilityClass
public class TestConstants {

    public CreateOrderRequest createOrderRequest() {
        CreateOrderRequest request = new CreateOrderRequest();
        request.setFirstName("Tony");
        request.setLastName("Tester");
        request.setEmail("tt@test.com");

        ArrayList<Long> phoneIds = new ArrayList<>();
        phoneIds.add(1L);
        request.setPhoneIds(phoneIds);

        return request;
    }

    public GetPhonesResponse singlePhone(BigDecimal price) {
        GetPhonesResponse.Phone phone = new GetPhonesResponse.Phone();
        phone.setPhoneId(1L);
        phone.setPrice(price);

        GetPhonesResponse response = new GetPhonesResponse();
        response.setPhones(Collections.singletonList(phone));

        return response;
    }
}
