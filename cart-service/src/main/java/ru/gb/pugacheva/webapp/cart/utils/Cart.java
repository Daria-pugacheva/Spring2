package ru.gb.pugacheva.webapp.cart.utils;

import lombok.Data;
import ru.gb.pugacheva.webapp.api.dtos.OrderItemDto;
import ru.gb.pugacheva.webapp.api.dtos.ProductDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Data
public class Cart {
    private List<OrderItemDto> items;
    private BigDecimal totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public void add(ProductDto product) {
        for (OrderItemDto i : items) {
            if (i.getProductId().equals(product.getId())) {
                i.changeQuantity(1);
                recalculate();
                return;
            }
        }
        items.add(new OrderItemDto(product.getId(),product.getTitle(), 1, product.getPrice(), product.getPrice()));
        recalculate();
    }

    public void decrement(Long productId) {
        Iterator<OrderItemDto> iter = items.iterator();
        while (iter.hasNext()) {
            OrderItemDto i = iter.next();
            if (i.getProductId().equals(productId)) {
                i.changeQuantity(-1);
                if (i.getQuantity() <= 0) {
                    iter.remove();
                }
                recalculate();
                return;
            }
        }
    }

    public void remove(Long productId) {
        items.removeIf(i -> i.getProductId().equals(productId));
        recalculate();
    }

    public void clear(){
        items.clear();
        totalPrice = null;
    }
    private void recalculate (){
        totalPrice = BigDecimal.ZERO;
        for (OrderItemDto i: items) {
            totalPrice = totalPrice.add(i.getPrice());
        }
    }

    public void merge (Cart another){
        for (OrderItemDto anotherItem : another.items){
            boolean merged = false;
            for (OrderItemDto myItem : items){
                if(myItem.getProductId().equals(anotherItem.getProductId())){
                    myItem.changeQuantity(anotherItem.getQuantity());
                    merged = true;
                    break;
                }
            }
            if(!merged){
                items.add(anotherItem);
            }
        }
        recalculate();
        another.clear();
    }


}
