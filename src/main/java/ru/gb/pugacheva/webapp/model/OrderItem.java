package ru.gb.pugacheva.webapp.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.gb.pugacheva.webapp.dtos.OrderItemDto;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
@Data
@NoArgsConstructor
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "product_title")
    private String productTitle;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price_per_product")
    private int pricePerProduct;

    @Column(name = "price")
    private int price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem(OrderItemDto orderItemDto, Order order) {
        this.productId = orderItemDto.getProductId();
        this.productTitle = orderItemDto.getProductTitle();
        this.quantity = orderItemDto.getQuantity();
        this.pricePerProduct = orderItemDto.getPricePerProduct();
        this.price = orderItemDto.getPrice();
        this.order = order;
    }
}
