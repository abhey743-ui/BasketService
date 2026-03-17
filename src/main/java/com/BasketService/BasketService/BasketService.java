package com.BasketService.BasketService;
import com.BasketService.BasketDto.*;
import com.BasketService.BasketFeign.ProductClient;
import com.BasketService.BasketModel.BasketModel;
import com.BasketService.BasketReposiroty.BasketRepository;
import io.jsonwebtoken.Claims;
import jakarta.ws.rs.core.SecurityContext;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;




@Service
@AllArgsConstructor
public class BasketService {
           private final  ProductClient productClient;
           private final BasketRepository basketRepository;



    public List<GettingBasketData> getBasket() {

        Claims claims = (Claims) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Long userId = Long.valueOf(claims.get("id").toString());

        List<BasketModel> data = basketRepository.findByUserId(userId);


        Set<String> productId = new HashSet<>();
        for (BasketModel b : data) {
            productId.add(b.getProductId());
        }


        ProductIdDto productIdDto = new ProductIdDto();
        productIdDto.setId(productId);
        List<ProductInfoFetchDto> productInformation = productClient.getProductInfo(productIdDto);


        List<GettingBasketData> gettingBasketDataList = new ArrayList<>();

        for (BasketModel basket : data) {
            for (ProductInfoFetchDto product : productInformation) {

                if (product.getProductId().equals(basket.getProductId())) {

                    GettingBasketData productData = GettingBasketData.builder()
                            .qty(basket.getQty())
                            .price(product.getPrice())
                            .productName(product.getProductName())
                            .productId(product.getProductId())
                            .productType(product.getProductType())
                            .images(product.getImage())
                            .build();

                    gettingBasketDataList.add(productData);
                }
            }
        }

        System.out.println("INTERNAL LIST SIZE = " + gettingBasketDataList.size());
        return gettingBasketDataList;
    }

    @Transactional
           public void addElement(AddProductsDto addProductsDto){
                Claims claims = (Claims) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                Long userId = Long.valueOf(claims.get("id").toString());
                   BasketModel basketModel = new BasketModel();
                   basketModel.setUserId(userId);
                   basketModel.setQty(addProductsDto.getQty());
                   basketModel.setProductId(addProductsDto.getId());

                   basketRepository.save(basketModel);

           }


            public void deleteProduct(String id){
                         basketRepository.deleteById(id);

            }
}
