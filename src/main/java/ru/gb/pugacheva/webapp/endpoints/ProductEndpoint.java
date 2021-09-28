package ru.gb.pugacheva.webapp.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.gb.pugacheva.webapp.dtos.soap.GetAllProductsRequest;
import ru.gb.pugacheva.webapp.dtos.soap.GetAllProductsResponse;
import ru.gb.pugacheva.webapp.services.ProductService;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.gb.pugacheva.ru/webapp/products";
    private final ProductService productService;

    /*
        Пример запроса: POST http://localhost:8189/market/ws

        <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:p="http://www.gb.pugacheva.ru/webapp/products">
            <soapenv:Header/>
            <soapenv:Body>
                <p:getAllProductsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productService.findAllForSoap().forEach(response.getProducts()::add);
        return response;
    }
}
