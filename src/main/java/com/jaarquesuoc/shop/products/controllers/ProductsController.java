package com.jaarquesuoc.shop.products.controllers;

import com.jaarquesuoc.shop.products.dtos.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductsController {

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable final String id) {
        return buildProduct(id);
    }

    @GetMapping("/products")
    public List<Product> getProducts(@RequestParam(value = "ids", required = false) final List<String> ids) {
        return Optional.ofNullable(ids)
            .map(Collection::stream)
            .map(idStream -> idStream
                .map(this::buildProduct)
                .collect(toList()))
            .orElse(buildProducts());
    }

    private List<Product> buildProducts() {
        return IntStream.range(0, 30)
            .mapToObj(i -> buildProduct(String.valueOf(i)))
            .collect(toList());
    }

    private Product buildProduct(final String id) {
        return Product.builder()
            .id(id)
            .name("Item name " + id)
            .description("Some description")
            .price(BigDecimal.valueOf(12.23D))
            .imageUrl("https://static.cardmarket.com/img/548dd39417c935651fbd98c3ee6d5951/items/1/WAR/372131.jpg")
            .build();
    }
}
