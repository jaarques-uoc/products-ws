package com.jaarquesuoc.shop.products.services;

import com.jaarquesuoc.shop.products.dtos.ProductDto;
import com.jaarquesuoc.shop.products.mappers.ProductsMapper;
import com.jaarquesuoc.shop.products.models.Product;
import com.jaarquesuoc.shop.products.repositories.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductsService {

    private final ProductsRepository productsRepository;

    public List<ProductDto> getAllProductDtos() {
        return productsRepository.findAll()
            .stream()
            .map(ProductsMapper.INSTANCE::toProductDto)
            .collect(toList());
    }

    public List<ProductDto> getAllProductDtosById(final List<String> ids) {
        Iterable<Product> products = productsRepository.findAllById(ids);

        return StreamSupport.stream(products.spliterator(), false)
            .map(ProductsMapper.INSTANCE::toProductDto)
            .collect(toList());
    }

    public Optional<ProductDto> getProductDto(final String id) {
        return productsRepository.findById(id)
            .map(ProductsMapper.INSTANCE::toProductDto);
    }

    public ProductDto upsertProduct(final ProductDto productDto) {
        Product product = ProductsMapper.INSTANCE.toProduct(productDto);
        return ProductsMapper.INSTANCE.toProductDto(productsRepository.save(product));
    }

    public List<ProductDto> upsertProducts(final List<ProductDto> productDtos) {
        List<Product> products = productDtos.stream()
            .map(ProductsMapper.INSTANCE::toProduct)
            .collect(toList());

        return productsRepository.saveAll(products).stream()
            .map(ProductsMapper.INSTANCE::toProductDto)
            .collect(toList());
    }

    public void cleanDb() {
        productsRepository.deleteAll();
    }
}
