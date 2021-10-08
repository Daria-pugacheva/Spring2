package ru.gb.pugacheva.webapp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.gb.pugacheva.webapp.model.Category;
import ru.gb.pugacheva.webapp.model.Product;
import ru.gb.pugacheva.webapp.repositories.ProductRepository;
import ru.gb.pugacheva.webapp.services.ProductService;

import java.util.Optional;

@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Test
    public void findByIdTest() {
        Product product = new Product();
        Category category = new Category();
        category.setId(1L);
        category.setTitle("Food");
        product.setId(1L);
        product.setTitle("Milk");
        product.setPrice(10);
        product.setCategory(category);

        Mockito.doReturn(Optional.of(product))
                .when(productRepository)
                .findById(1L);

        Product milk = productService.findByID(1L).get();
        Assertions.assertNotNull(milk);
        Assertions.assertEquals(10, milk.getPrice());
        Mockito.verify(productRepository, Mockito.times(1)).findById(ArgumentMatchers.eq(1L));
    }
}
