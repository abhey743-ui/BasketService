package com.BasketService.BasketDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProductInfoFetchDto {

    private String productId;
    private String productName;
    private float price;
    private String  productType;
    private List<String> image;

}
