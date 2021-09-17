package ru.gb.pugacheva.webapp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.pugacheva.webapp.exceptions.ResourceNotFoundException;
import ru.gb.pugacheva.webapp.model.Order;
import ru.gb.pugacheva.webapp.model.OrderItem;
import ru.gb.pugacheva.webapp.repositories.OrderItemsRepository;
import ru.gb.pugacheva.webapp.repositories.OrderRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    private final OrderItemsRepository orderItemsRepository;

    public void saveOrderItem(OrderItem orderItem) {
        orderItemsRepository.save(orderItem);
    }




}
