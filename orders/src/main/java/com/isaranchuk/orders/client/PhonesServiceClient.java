package com.isaranchuk.orders.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class PhonesServiceClient {

    private static final String GET_PHONES = "/v1/phones";

    private final WebClient webClient;

    private final String baseUrl;

    public PhonesServiceClient(WebClient.Builder webClientBuilder, @Value("${phones.base-url}") String baseUrl) {
        this.webClient = webClientBuilder.build();
        this.baseUrl = baseUrl;
    }

    public Mono<PhonesResponse> getPhones() {
        return webClient.get().uri(baseUrl + GET_PHONES).retrieve().bodyToMono(PhonesResponse.class);
    }
}
