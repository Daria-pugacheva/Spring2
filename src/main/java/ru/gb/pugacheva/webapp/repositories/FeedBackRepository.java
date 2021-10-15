package ru.gb.pugacheva.webapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.gb.pugacheva.webapp.model.FeedBack;
import ru.gb.pugacheva.webapp.model.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeedBackRepository extends JpaRepository <FeedBack, Long>{
    List <FeedBack> findByProductId (Long productId);

}
