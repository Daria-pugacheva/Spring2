package ru.gb.pugacheva.webapp.orders.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.pugacheva.webapp.api.dtos.CartDto;
import ru.gb.pugacheva.webapp.api.dtos.OrderDetailsDto;
import ru.gb.pugacheva.webapp.api.dtos.OrderItemDto;
import ru.gb.pugacheva.webapp.api.dtos.ProductDto;
import ru.gb.pugacheva.webapp.orders.integration.CartAndCoreServiceIntegration;
import ru.gb.pugacheva.webapp.orders.model.Order;
import ru.gb.pugacheva.webapp.orders.model.OrderItem;
import ru.gb.pugacheva.webapp.orders.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    //private final UserService userService;
    //private final CartServiceIntegration cartServiceIntegration;
    private final CartAndCoreServiceIntegration cartAndCoreServiceIntegration;
   // private final ProductService productService;


    @Transactional
    public void createOrder(String username, OrderDetailsDto orderDetailsDto) { //изначально прилетал Principal
//        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException
//                ("Не удалось найти в базе пользователя с именем " + principal.getName()));
        CartDto cart = cartAndCoreServiceIntegration.getUserCartDto(username);
        Order order = new Order();
//        order.setUser(user);
        order.setUserName(username);
        order.setPrice(cart.getTotalPrice());
        order.setAddress(orderDetailsDto.getAddress());
        order.setPhone(orderDetailsDto.getPhone());
        List<OrderItem> items = new ArrayList<>();
        for (OrderItemDto i : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setPrice(i.getPrice());
            orderItem.setPricePerProduct(i.getPricePerProduct());
            orderItem.setQuantity(i.getQuantity());
            ProductDto product = cartAndCoreServiceIntegration.getProductDtoById(i.getProductId()); //Здесь вы как-то перехватить исключение от продуктового сервиса, если продукт не найден?
//            orderItem.setProduct(productService.findByID(i.getProductId()).orElseThrow(() -> new ResourceNotFoundException
//                    ("Не удалось найти продукт ID продукта: " + i.getProductId())));
            orderItem.setProductId(product.getId());
            orderItem.setProductTitle(product.getTitle());
            items.add(orderItem);
        }
        order.setItems(items);
        orderRepository.save(order);
        cartAndCoreServiceIntegration.clear(username);
    }

    public List<Order> findAllByUsername(String username) {
        return orderRepository.findAllByUserName(username);
    }


}


