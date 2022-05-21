package ru.gb.pugacheva.webapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.gb.pugacheva.webapp.model.Category;
import ru.gb.pugacheva.webapp.model.Product;
import ru.gb.pugacheva.webapp.services.ProductService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {
        @Autowired
        private MockMvc mvc;

        @MockBean
        private ProductService productService;

        @Test
        public void findAllTest() throws Exception {
            Product product = new Product();
            Category category = new Category();
            category.setId(1L);
            category.setTitle("Food");
            product.setId(1L);
            product.setTitle("Milk");
            product.setPrice(10);
            product.setCategory(category);

            List<Product> allproducts = new ArrayList<>(Arrays.asList(product));
            Page <Product> page = new PageImpl<>(allproducts);

            given(productService.findAll(0,10)).willReturn(page);

            System.out.println("ВНИМАНИЕ !!!!!!!!!!!!!!" + productService.findAll(0,10).getContent() );

            mvc
                    .perform(
                            get("/api/v1/products")
                                    .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.content[0].categoryTitle",
                            is(allproducts.get(0).getCategory().getTitle()))); //по сути, проверяю, что
            //вернувшиеся в ответе от сервиса Product контроллер преобразует в ProductDto и возвращает.
        }
    }


