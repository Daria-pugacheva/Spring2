package ru.gb.pugacheva.webapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.gb.pugacheva.webapp.dtos.OrderDetailsDto;
import ru.gb.pugacheva.webapp.exceptions.ResourceNotFoundException;
import ru.gb.pugacheva.webapp.model.Order;
import ru.gb.pugacheva.webapp.model.OrderItem;
import ru.gb.pugacheva.webapp.model.User;
import ru.gb.pugacheva.webapp.services.CartService;
import ru.gb.pugacheva.webapp.services.OrderItemService;
import ru.gb.pugacheva.webapp.services.OrderService;
import ru.gb.pugacheva.webapp.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final CartService cartService;
    private final OrderService orderService;
    private final OrderItemService orderItemService;
    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder (@RequestBody OrderDetailsDto orderDetailsDto, Principal principal){
        User currentUser = userService.findByUsername(principal.getName())
                .orElseThrow(()->new ResourceNotFoundException("Пользователь с имененм " + principal.getName() + "  отсутствует"));
        String orderPhone = orderDetailsDto.getPhone();
        String orderAddress = orderDetailsDto.getAddress();
        int orderPrice = cartService.getCartForCurrentUser().getTotalPrice();
        List<OrderItem> orderItems = cartService.getCartForCurrentUser().getItems()
                .stream().map(orderItemDto -> new OrderItem (orderItemDto)).collect(Collectors.toList());
        Order currentOrder = orderService.saveOrder(new Order(orderPhone,orderAddress,orderPrice,orderItems,currentUser));
        for (OrderItem oi: orderItems) {
            orderItemService.saveOrderItem(oi);
            oi.setOrder(currentOrder);
        }
        cartService.clearCart();
        orderService.printOrderInfo(currentOrder.getId());
    }
}
