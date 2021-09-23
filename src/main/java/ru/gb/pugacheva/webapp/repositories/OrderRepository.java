package ru.gb.pugacheva.webapp.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.gb.pugacheva.webapp.model.Order;
import ru.gb.pugacheva.webapp.model.Product;

import javax.persistence.NamedEntityGraph;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository <Order, Long> {

    @Query("select o from Order o where o.user.username = :username")
    @EntityGraph(value = "orders.for-front")
    List<Order> findAllByUsername (String username);
}
