package ru.gb.pugacheva.webapp.orders.utils;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.pugacheva.webapp.api.dtos.OrderDto;
import ru.gb.pugacheva.webapp.api.dtos.OrderItemDto;
import ru.gb.pugacheva.webapp.orders.integration.CartAndCoreServiceIntegration;
import ru.gb.pugacheva.webapp.orders.model.Order;
import ru.gb.pugacheva.webapp.orders.model.OrderItem;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class Converter {
    private final CartAndCoreServiceIntegration orderServiceIntegration;

    public OrderItemDto orderItemToDto(OrderItem orderItem){

        return new OrderItemDto(orderItem.getProductId(),orderItem.getProductTitle(),
                orderItem.getQuantity(),orderItem.getPricePerProduct(), orderItem.getPrice());

//        return new OrderItemDto(orderItem.getProduct().getId(),orderItem.getProduct().getTitle(),
//                orderItem.getQuantity(),orderItem.getPricePerProduct(), orderItem.getPrice());
    }

    public OrderDto orderToDto (Order order){
        return new OrderDto(order.getId(),
                order.getItems().stream().map(oi -> orderItemToDto(oi)).collect(Collectors.toList()),
                order.getAddress(), order.getPhone(), order.getPrice());
    }

}
