package com.BasketService.BasketDto;
import com.BasketService.BasketModel.BasketModel;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;


@Getter
@Setter
public class ProductMapping {

    Map<String, BasketModel> productInfoFetchDtoMap = new HashMap<>();
}
