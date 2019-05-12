package com.jaarquesuoc.shop.products.controllers;

import com.jaarquesuoc.shop.products.dtos.ProductDto;
import com.jaarquesuoc.shop.products.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InitialisationController {

    private final ProductService productService;

    @GetMapping("/init")
    public List<ProductDto> initialiseDB() {
        productService.cleanDb();
        return productService.upsertProducts(buildProducts());
    }

    private List<ProductDto> buildProducts() {
        return IntStream.range(0, 30)
            .mapToObj(i -> buildProduct(String.valueOf(i)))
            .collect(toList());
    }

    private ProductDto buildProduct(final String id) {
        return ProductDto.builder()
            .id(id)
            .name("Item name " + id)
            .description("Some description")
            .price(BigDecimal.valueOf(12.23D))
            .imageUrl("https://static.cardmarket.com/img/548dd39417c935651fbd98c3ee6d5951/items/1/WAR/372131.jpg")
            .build();
    }
}
