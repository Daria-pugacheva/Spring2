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
import ru.gb.pugacheva.webapp.model.FeedBack;
import ru.gb.pugacheva.webapp.model.Product;
import ru.gb.pugacheva.webapp.repositories.FeedBackRepository;
import ru.gb.pugacheva.webapp.repositories.ProductRepository;
import ru.gb.pugacheva.webapp.repositories.specifications.ProductSpecifications;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FeedbackService {
    private final FeedBackRepository feedBackRepository;

    public List<FeedBack> findAll(){
        return feedBackRepository.findAll();
    }

    public FeedBack save (FeedBack feedBack){
        return feedBackRepository.save(feedBack);
    }

    public List <FeedBack> findAllByProductId (Long productId){
       return feedBackRepository.findByProductId(productId);
    }

}
