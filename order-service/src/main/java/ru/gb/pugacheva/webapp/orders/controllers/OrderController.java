package ru.gb.pugacheva.webapp.orders.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;;
import ru.gb.pugacheva.webapp.api.dtos.OrderDetailsDto;
import ru.gb.pugacheva.webapp.api.dtos.OrderDto;
import ru.gb.pugacheva.webapp.orders.services.OrderService;
import ru.gb.pugacheva.webapp.orders.utils.Converter;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {
    private final OrderService orderService;
    private final Converter converter;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder (@RequestBody OrderDetailsDto orderDetailsDto, @RequestHeader String username){
       orderService.createOrder(username, orderDetailsDto);
    }

//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public void createOrder (@RequestBody OrderDetailsDto orderDetailsDto, Principal principal){
//        orderService.createOrder(principal, orderDetailsDto);
//    }

    @GetMapping
    public List<OrderDto> getOrdersForCurrentUser (@RequestHeader String username){
       return orderService.findAllByUsername(username).stream()
               .map(o -> converter.orderToDto(o)).collect(Collectors.toList());
    }

//    @GetMapping
//    public List<OrderDto> getOrdersForCurrentUser (Principal principal){
//        return orderService.findAllByUsername(principal.getName()).stream()
//                .map(o -> converter.orderToDto(o)).collect(Collectors.toList());
//    }

}
