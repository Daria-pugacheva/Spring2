package ru.gb.pugacheva.webapp.orders.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gb.pugacheva.webapp.orders.model.Order;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository <Order, Long> {

//    @Query("select o from Order o where o.user.username = :username")
//    @EntityGraph(value = "orders.for-front")
//    List<Order> findAllByUsername (String username);

    List <Order> findAllByUserName (String username);
}
