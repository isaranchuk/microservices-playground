package com.isaranchuk.orders.service;

import com.isaranchuk.orders.TestConstants;
import com.isaranchuk.orders.api.CreateOrderRequest;
import com.isaranchuk.orders.client.PhonesServiceClient;
import com.isaranchuk.orders.domain.Order;
import com.isaranchuk.orders.exception.UnknownPhoneException;
import com.isaranchuk.orders.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Mock
    private PhonesServiceClient phonesServiceClient;

    @Mock
    private OrderRepository orderRepository;

    private OrderService orderService;

    @Before
    public void setUp() throws Exception {
        orderService = new OrderService(phonesServiceClient, orderRepository);
    }

    @Test
    public void shouldCreateOrder() {
        // given
        BigDecimal expectedTotalPrice = BigDecimal.TEN;
        CreateOrderRequest request = TestConstants.createOrderRequest();

        doReturn(Mono.just(TestConstants.singlePhone(expectedTotalPrice))).when(phonesServiceClient).getPhones();
        doReturn(Mono.just(Order.valueOf(request, expectedTotalPrice))).when(orderRepository).insert(any(Order.class));

        // when
        Order order = orderService.create(request).block();

        // then
        assertThat(order.getTotalPrice()).isEqualTo(expectedTotalPrice);
        assertThat(order.getFirstName()).isEqualTo(request.getFirstName());
        assertThat(order.getLastName()).isEqualTo(request.getLastName());
        assertThat(order.getPhoneIds()).isEqualTo(request.getPhoneIds());
    }

    @Test
    public void shouldNotCreateOrderWhenInvalidPhoneId() {
        // given
        BigDecimal expectedTotalPrice = BigDecimal.TEN;
        CreateOrderRequest request = TestConstants.createOrderRequest();
        Long unknownPhoneId = Long.MAX_VALUE;

        request.getPhoneIds().add(unknownPhoneId);

        doReturn(Mono.just(TestConstants.singlePhone(expectedTotalPrice))).when(phonesServiceClient).getPhones();

        // when
        Throwable throwable = Assertions.catchThrowable(() -> orderService.create(request).block());

        // then
        assertThat(throwable).isInstanceOf(UnknownPhoneException.class);
    }
}