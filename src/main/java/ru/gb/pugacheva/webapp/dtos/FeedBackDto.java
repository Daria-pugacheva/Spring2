package ru.gb.pugacheva.webapp.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import ru.gb.pugacheva.webapp.model.FeedBack;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class FeedBackDto {

    private Long id;
    private Long userId;
    private Long productId;
    private String feedbackText;

    public FeedBackDto(FeedBack feedBack) {
        this.id = feedBack.getId();
        this.userId = feedBack.getUserId();
        this.productId = feedBack.getProductId();
        this.feedbackText = feedBack.getFeedbackText();
    }
}
