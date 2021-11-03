package ru.gb.pugacheva.webapp.core.integration;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import ru.gb.pugacheva.webapp.api.dtos.CartDto;

@Component
@RequiredArgsConstructor
public class CartServiceIntegration {
    private final WebClient cartServiceWebClient;

//    //первый вариант
//    public CartDto getUserCartDto (String username){
//        MultiValueMap <String, String> headers = new LinkedMultiValueMap<>();
//        headers.add("username", username);
//        HttpEntity rqEntity = new HttpEntity(headers);
//        CartDto cart = cartServiceWebClient.get()
//                .uri("/api/v1/cart/0", rqEntity) //есть вариант, где можно помим адреса обхект еще добавить
//                .retrieve()
//                .bodyToMono(CartDto.class)
//                .block();
//        return cart;
//    }
//
//    public void clearUserCart (String username){
//        MultiValueMap <String, String> headers = new LinkedMultiValueMap<>();
//        headers.add("username", username);
//        HttpEntity rqEntity = new HttpEntity(headers);
//        cartServiceWebClient.get()
//                .uri("api/v1/cart/0/clear",rqEntity)
//                .retrieve().toBodilessEntity().block();
//    }


    //второй вариант
    public CartDto getUserCartDto (String username){

        CartDto cart = cartServiceWebClient.get()
                .uri("/api/v1/cart/0")
                .header("username", username)
                .retrieve()
                .bodyToMono(CartDto.class)
                .block();
        return cart;
    }

    public void clearUserCart (String username){
        cartServiceWebClient.get()
                .uri("api/v1/cart/0/clear")
                .header("username", username)
                .retrieve().toBodilessEntity().block();
    }

}








////Старый вариант интеграции с RestTemplate
//    private final RestTemplate restTemplate;
//
//    @Value("${integration.cart-service.url}")
//    private String cartServiceUrl;
//
//    public CartDto getUserCartDto (String username){
//        MultiValueMap <String, String> headers = new LinkedMultiValueMap<>();
//        headers.add("username", username);
//        HttpEntity rqEntity = new HttpEntity(headers);
//        CartDto cart= restTemplate.exchange(cartServiceUrl, HttpMethod.GET, rqEntity, CartDto.class).getBody();
//        return cart;
//    }
//
//    public void clearUserCart (String username){
//        MultiValueMap <String, String> headers = new LinkedMultiValueMap<>();
//        headers.add("username", username);
//        HttpEntity rqEntity = new HttpEntity(headers);
//        restTemplate.exchange(cartServiceUrl + "/clear", HttpMethod.GET, rqEntity, void.class);
//
//    }