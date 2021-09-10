package ru.gb.pugacheva.webapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.pugacheva.webapp.model.Order;
import ru.gb.pugacheva.webapp.model.Product;

@Repository
public interface OrderRepository extends JpaRepository <Order, Long> {
}
