package com.BasketService.BasketDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@Builder
public class GettingBasketData {

    private String productId;
    private String productName;
    private float price;
    private int qty;
    private String productType;
    private List<String> images;

}
