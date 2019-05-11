package controllers;

import lombok.RequiredArgsConstructor;
import models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductsController {

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable final String id) {
        return Product.builder()
            .id(id)
            .name("Item name " + id)
            .description("Some description")
            .price(BigDecimal.valueOf(12.23D))
            .imageUrl("https://static.cardmarket.com/img/548dd39417c935651fbd98c3ee6d5951/items/1/WAR/372131.jpg")
            .build();
    }

    @GetMapping("/products")
    public List<Product> getProducts() {
        return IntStream.range(0, 30)
            .mapToObj(i -> getProduct(String.valueOf(i)))
            .collect(Collectors.toList());
    }
}
