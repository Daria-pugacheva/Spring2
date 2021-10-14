package ru.gb.pugacheva.webapp.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.MultiValueMap;
import ru.gb.pugacheva.webapp.dtos.ProductDto;
import ru.gb.pugacheva.webapp.exceptions.ResourceNotFoundException;
import ru.gb.pugacheva.webapp.model.Category;
import ru.gb.pugacheva.webapp.model.Product;
import ru.gb.pugacheva.webapp.repositories.ProductRepository;
import ru.gb.pugacheva.webapp.repositories.specifications.ProductSpecifications;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    private static final String FILTER_MIN_PRICE = "min_price";
    private static final String FILTER_MAX_PRICE = "max_price";
    private static final String FILTER_TITLE = "title";


    public Page<Product> findAll(int pageIndex, int pageSize, MultiValueMap <String, String> rqParams){
        return productRepository.findAll(constructSpecification(rqParams),PageRequest.of(pageIndex,pageSize));
    }
//    //Базовый вариант, когда возвращали все товары сразу
//    public List<Product> findAll(){
//        return productRepository.findAll();
//    }

    public Optional<Product> findByID (Long id){
        return productRepository.findById(id);
    }

    public Product save (Product product){
        return productRepository.save(product);
    }

    @Transactional
    public void updateProductFromDto (ProductDto productDto) {
        Product product = findByID(productDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Product id= " + productDto.getId() + " not found"));
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        if (!product.getCategory().getTitle().equals(productDto.getCategoryTitle())) {
            Category category = categoryService.findByTitle(productDto.getCategoryTitle())
                    .orElseThrow(() -> new ResourceNotFoundException
                            ("Category title= " + productDto.getCategoryTitle() + " not found"));
            product.setCategory(category);
        }
    }

    public Optional<Product> findByTitle(String title) {
        return productRepository.findByTitle(title);
    }

    private Specification<Product> constructSpecification(MultiValueMap<String, String> params) {
        Specification<Product> spec = Specification.where(null);
        if (params.containsKey(FILTER_MIN_PRICE) && !params.getFirst(FILTER_MIN_PRICE).isBlank()) {
            int minPrice = Integer.parseInt(params.getFirst(FILTER_MIN_PRICE));
            spec = spec.and(ProductSpecifications.priceGreaterOrEqualsThan(minPrice));
        }
        if (params.containsKey(FILTER_MAX_PRICE) && !params.getFirst(FILTER_MAX_PRICE).isBlank()) {
            int maxPrice = Integer.parseInt(params.getFirst(FILTER_MAX_PRICE));
            spec = spec.and(ProductSpecifications.priceLesserOrEqualsThan(maxPrice));
        }
        if (params.containsKey(FILTER_TITLE) && !params.getFirst(FILTER_TITLE).isBlank()) {
            String title = params.getFirst(FILTER_TITLE);
            spec = spec.and(ProductSpecifications.titleLike(title));
        }
        return spec;
    }

}
