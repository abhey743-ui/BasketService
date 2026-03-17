package com.BasketService.BasketFeign;


import com.BasketService.BasketDto.ProductIdDto;
import com.BasketService.BasketDto.ProductInfoFetchDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "PRODUCTSERVICE")
public interface ProductClient {

    @PostMapping("/internal/get/product/info")
    List< ProductInfoFetchDto> getProductInfo(@RequestBody ProductIdDto productIdDto);

}
