package ru.gb.pugacheva.webapp.utils;

import lombok.Data;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Data
public class TimeCountAspect {
    @Value("0")
    private long cartServiceDuration;
    @Value("0")
    private long categoryServiceDuration;
    @Value("0")
    private long orderServiceDuration;
    @Value("0")
    private long productServiceDuration;
    @Value("0")
    private long userServiceDuration;

    @Around("execution(public * ru.gb.pugacheva.webapp.services.CartService.*(..))")
    public Object countCartServiceDuration(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        cartServiceDuration += duration;
        return out;
    }

    @Around("execution(public * ru.gb.pugacheva.webapp.services.CategoryService.*(..))")
    public Object countCategoryServiceDuration(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        categoryServiceDuration += duration;
        return out;
    }

    @Around("execution(public * ru.gb.pugacheva.webapp.services.OrderService.*(..))")
    public Object countOrderServiceDuration(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        orderServiceDuration += duration;
        return out;
    }

    @Around("execution(public * ru.gb.pugacheva.webapp.services.ProductService.*(..))")
    public Object countProductServiceDuration(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        productServiceDuration += duration;
        return out;
    }

    @Around("execution(public * ru.gb.pugacheva.webapp.services.UserService.*(..))")
    public Object countUserServiceDuration(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long end = System.currentTimeMillis();
        long duration = end - begin;
        userServiceDuration += duration;
        return out;
    }

}
