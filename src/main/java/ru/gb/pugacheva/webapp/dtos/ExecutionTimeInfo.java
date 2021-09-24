package ru.gb.pugacheva.webapp.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

@Data
@NoArgsConstructor
public class ExecutionTimeInfo {

    private long cartServiceDuration;
    private long categoryServiceDuration;
    private long orderServiceDuration;
    private long productServiceDuration;
    private long userServiceDuration;

    public ExecutionTimeInfo(long cartServiceDuration, long categoryServiceDuration, long orderServiceDuration, long productServiceDuration, long userServiceDuration) {
        this.cartServiceDuration = cartServiceDuration;
        this.categoryServiceDuration = categoryServiceDuration;
        this.orderServiceDuration = orderServiceDuration;
        this.productServiceDuration = productServiceDuration;
        this.userServiceDuration = userServiceDuration;
    }

    //если говорить о расширении функционала,то можно насоздавать стопку конструкторов с разными наборами параметров

}
