package com.isaranchuk.orders.api;

import com.isaranchuk.orders.TestConstants;
import com.isaranchuk.orders.domain.Order;
import com.isaranchuk.orders.exception.UnknownPhoneException;
import com.isaranchuk.orders.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureWebTestClient
public class OrderControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private OrderService orderService;

    @Test
    public void shouldCreateOrder() throws Exception {
        given(orderService.create(any(CreateOrderRequest.class))).willReturn(Mono.just(new Order()));

        webClient.post().uri("/v1/orders")
                .syncBody(TestConstants.createOrderRequest())
                .exchange()
                .expectStatus().isCreated();
    }
}