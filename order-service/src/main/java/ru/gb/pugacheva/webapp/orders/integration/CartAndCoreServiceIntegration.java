package ru.gb.pugacheva.webapp.orders.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.gb.pugacheva.webapp.api.dtos.CartDto;
import ru.gb.pugacheva.webapp.api.dtos.ProductDto;


@Component
@RequiredArgsConstructor
public class CartAndCoreServiceIntegration {
    private final RestTemplate restTemplate;

    @Value("${integration.cart-service.url}")
    private String cartServiceUrl;

    @Value("${integration.product-service.url}")
    private String productServiceUrl;

    public CartDto getUserCartDto (String username){
        MultiValueMap <String, String> headers = new LinkedMultiValueMap<>();
        headers.add("username", username);
        HttpEntity rqEntity = new HttpEntity(headers);
        CartDto cart= restTemplate.exchange(cartServiceUrl, HttpMethod.GET, rqEntity, CartDto.class).getBody();
        return cart;
    }

    public void clear (String username){
        MultiValueMap <String, String> headers = new LinkedMultiValueMap<>();
        headers.add("username", username);
        HttpEntity rqEntity = new HttpEntity(headers);
        restTemplate.exchange(cartServiceUrl + "/clear", HttpMethod.GET, rqEntity, void.class);

    }

    public ProductDto getProductDtoById(Long productId){
        return restTemplate.getForObject(productServiceUrl + "/api/v1/products/" + productId,ProductDto.class);
    }

}
