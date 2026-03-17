package com.BasketService.BasketModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;




@Document("Basket")
@Getter
@Setter
public class BasketModel {

    @Id
    private String id;

    private String productId;

    private Long userId;

    private int qty;
}
