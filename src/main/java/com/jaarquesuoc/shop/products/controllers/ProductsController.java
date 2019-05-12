package com.jaarquesuoc.shop.products.controllers;

import com.jaarquesuoc.shop.products.dtos.ProductDto;
import com.jaarquesuoc.shop.products.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductsController {

    private final ProductService productService;

    @GetMapping("/products/{id}")
    public ProductDto getProductDto(@PathVariable final String id) {
        return productService.getProductDto(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/products")
    public List<ProductDto> getProductDtos(@RequestParam(value = "ids", required = false) final List<String> ids) {
        return Optional.ofNullable(ids)
            .map(productService::getAllProductDtosById)
            .orElse(productService.getAllProductDtos());
    }

    @PostMapping("/products")
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productService.upsertProduct(productDto);
    }
}
