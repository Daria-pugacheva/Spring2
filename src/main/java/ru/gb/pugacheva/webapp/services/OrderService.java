package ru.gb.pugacheva.webapp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.pugacheva.webapp.exceptions.ResourceNotFoundException;
import ru.gb.pugacheva.webapp.model.Order;
import ru.gb.pugacheva.webapp.model.OrderItem;
import ru.gb.pugacheva.webapp.repositories.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order findOrderById(Long orderId) {
        return orderRepository.findById(orderId).orElseThrow(()-> new ResourceNotFoundException
                ("Order id= " + orderId + " not found"));
    }

    @Transactional
    public void printOrderInfo (Long orderId){
        Order order = findOrderById(orderId);
        List <OrderItem> itemsInOrder = order.getOrderItems();
        System.out.printf("Информация о заказе: id=%d, phone=%s, address=%s, totalPrice=%d",
                order.getId(),order.getPhone(),order.getAddress(),order.getTotalPrice());
        System.out.println("\n Список товаров в заказе: " + itemsInOrder);
        System.out.printf("\n Информация по покупателе: id = %d, имя = %s ", order.getUser().getId(), order.getUser().getUsername());

    }

    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}
