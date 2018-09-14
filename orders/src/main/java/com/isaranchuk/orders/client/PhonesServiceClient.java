package com.isaranchuk.orders.client;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PhonesServiceClient {

    private static final String GET_PHONES = "/v1/phones";

    private final RestTemplate restTemplate;

    private final String baseUrl;

    public PhonesServiceClient(RestTemplateBuilder restTemplateBuilder, String baseUrl) {
        this.restTemplate = restTemplateBuilder.build();
        this.baseUrl = baseUrl;
    }

    public PhonesResponse getPhones() {
        return restTemplate.getForObject(baseUrl + GET_PHONES, PhonesResponse.class);
    }
}
