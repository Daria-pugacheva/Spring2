package ru.gb.pugacheva.webapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.pugacheva.webapp.dtos.ExecutionTimeInfo;
import ru.gb.pugacheva.webapp.utils.TimeCountAspect;

@RestController
@RequestMapping("/api/v1/statistic")
@RequiredArgsConstructor
public class ExecutionTimeController {
    private final TimeCountAspect timeCountAspect;

    @GetMapping
    public ExecutionTimeInfo showExecutionTimeOfServices() {
        ExecutionTimeInfo executionTimeInfo = new ExecutionTimeInfo(timeCountAspect.getCartServiceDuration(),
                timeCountAspect.getCategoryServiceDuration(), timeCountAspect.getOrderServiceDuration(),
                timeCountAspect.getProductServiceDuration(), timeCountAspect.getUserServiceDuration());
//        //Как вариант, можно обнулять сведения о времени исполнения методов после каждого запроса статистики.
//        //(но это опционально, если хотим выводить инфо за промежутки времени между запросами статистики.
//        timeCountAspect.setCartServiceDuration(0L);
//        timeCountAspect.setCategoryServiceDuration(0L);
//        timeCountAspect.setOrderServiceDuration(0L);
//        timeCountAspect.setProductServiceDuration(0L);
//        timeCountAspect.setUserServiceDuration(0L);

        return executionTimeInfo;
    }

}
