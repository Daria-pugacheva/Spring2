package ru.gb.pugacheva.webapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.pugacheva.webapp.model.Order;
import ru.gb.pugacheva.webapp.model.OrderItem;

@Repository
public interface OrderItemsRepository extends JpaRepository <OrderItem, Long> {
}
