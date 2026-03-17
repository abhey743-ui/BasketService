package com.BasketService.BasketReposiroty;
import com.BasketService.BasketModel.BasketModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;



public interface BasketRepository extends MongoRepository<BasketModel, String> {
   List< BasketModel> findByUserId(Long  userId);
}
