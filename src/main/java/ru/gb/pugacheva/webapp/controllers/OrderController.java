package ru.gb.pugacheva.webapp.controllers;

import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.pugacheva.webapp.dtos.OrderDetailsDto;
import ru.gb.pugacheva.webapp.model.Order;
import ru.gb.pugacheva.webapp.model.OrderItem;
import ru.gb.pugacheva.webapp.services.CartService;
import ru.gb.pugacheva.webapp.services.OrderItemService;
import ru.gb.pugacheva.webapp.services.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final CartService cartService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder (@RequestBody OrderDetailsDto orderDetailsDto){
        String orderPhone = orderDetailsDto.getPhone();
        String orderAddress = orderDetailsDto.getAddress();
        int orderPrice = cartService.getCartForCurrentUser().getTotalPrice();
        Order currentNotFullOrder = orderService.saveOrderWithoutItems(new Order(orderPhone,orderAddress,orderPrice));
        List<OrderItem> orderItems = cartService.getCartForCurrentUser().getItems()
                .stream().map(orderItemDto -> new OrderItem (orderItemDto, currentNotFullOrder)).collect(Collectors.toList());
        for (OrderItem oi: orderItems) {
            orderItemService.saveOrderItem(oi);
            System.out.println("Сохранен item " + oi);
        }
        //orderService.saveItemsInOrder(currentNotFullOrder.getId(), orderItems);
        cartService.clearCart();
        orderService.printOrderInfo(currentNotFullOrder.getId());
    }

}
