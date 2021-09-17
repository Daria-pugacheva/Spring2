package ru.gb.pugacheva.webapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "total_price")
    private int totalPrice;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems;

    public Order(String phone, String address, int totalPrice, List<OrderItem> orderItems, User currentUser) {
        this.phone = phone;
        this.address = address;
        this.totalPrice = totalPrice;
        this.orderItems = orderItems;
        this.user = currentUser;
    }

}
