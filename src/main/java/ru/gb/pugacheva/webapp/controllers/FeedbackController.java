package ru.gb.pugacheva.webapp.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.pugacheva.webapp.dtos.FeedBackDto;
import ru.gb.pugacheva.webapp.dtos.ProductDto;
import ru.gb.pugacheva.webapp.exceptions.DataValidationException;
import ru.gb.pugacheva.webapp.exceptions.ResourceNotFoundException;
import ru.gb.pugacheva.webapp.model.Category;
import ru.gb.pugacheva.webapp.model.FeedBack;
import ru.gb.pugacheva.webapp.model.Product;
import ru.gb.pugacheva.webapp.model.User;
import ru.gb.pugacheva.webapp.services.CategoryService;
import ru.gb.pugacheva.webapp.services.FeedbackService;
import ru.gb.pugacheva.webapp.services.ProductService;
import ru.gb.pugacheva.webapp.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/feedbacks")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;
    private final UserService userService;

    @GetMapping()
    public List<FeedBackDto> findAll() {
        return feedbackService.findAll().stream().map(FeedBackDto::new).collect(Collectors.toList());
    }

    @GetMapping("/{productId}")
    public List<FeedBackDto>  findAllByProductId (@PathVariable Long productId){
        return feedbackService.findAllByProductId(productId).stream().map(FeedBackDto::new).collect(Collectors.toList());
    }

    @PostMapping("/{productId}")
    public FeedBackDto save(@PathVariable Long productId, @RequestBody FeedBackDto feedBackDto, Principal principal) {
        FeedBack feedBack = new FeedBack();
        User user = userService.findByUsername(principal.getName()).get();
        feedBack.setUserId(user.getId());
        feedBack.setProductId(productId);
        feedBack.setFeedbackText(feedBackDto.getFeedbackText());
        feedbackService.save(feedBack);
        return new FeedBackDto(feedBack);
    }


}
