package com.BasketService.BasketController;
import com.BasketService.BasketDto.AddProductsDto;
import com.BasketService.BasketDto.GettingBasketData;
import com.BasketService.BasketService.BasketService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
public class BasketController {

    private final BasketService basketService;


    @GetMapping("/get/products")
    public List<GettingBasketData> getProducts(){

        System.out.println("BASKET CONTROLLER CALLED");
        List<GettingBasketData> result = basketService.getBasket();
        System.out.println("SIZE RETURNED = " + result.size());
        return result;


    }


    @PostMapping("/add/products")
    public HttpStatus addData(@RequestBody AddProductsDto addProductsDto){

                  basketService.addElement(addProductsDto);
                  return HttpStatus.CREATED;

    }

    @GetMapping("/delete/product/{id}")
    public HttpStatus deleteData(@PathVariable  String id){
            basketService.deleteProduct(id);
            return HttpStatus.OK;
    }

}
