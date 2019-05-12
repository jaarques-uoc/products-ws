package com.jaarquesuoc.shop.products.services;

import com.jaarquesuoc.shop.products.dtos.ProductDto;
import com.jaarquesuoc.shop.products.mappers.ProductMapper;
import com.jaarquesuoc.shop.products.models.Product;
import com.jaarquesuoc.shop.products.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductDto> getAllProductDtos() {
        return productRepository.findAll()
            .stream()
            .map(ProductMapper.INSTANCE::toProductDto)
            .collect(toList());
    }

    public List<ProductDto> getAllProductDtosById(final List<String> ids) {
        Iterable<Product> products = productRepository.findAllById(ids);

        return StreamSupport.stream(products.spliterator(), false)
            .map(ProductMapper.INSTANCE::toProductDto)
            .collect(toList());
    }

    public Optional<ProductDto> getProductDto(final String id) {
        return productRepository.findById(id)
            .map(ProductMapper.INSTANCE::toProductDto);
    }

    public ProductDto upsertProduct(final ProductDto productDto) {
        Product product = ProductMapper.INSTANCE.toProduct(productDto);
        return ProductMapper.INSTANCE.toProductDto(productRepository.save(product));
    }

    public List<ProductDto> upsertProducts(final List<ProductDto> productDtos) {
        List<Product> products = productDtos.stream()
            .map(ProductMapper.INSTANCE::toProduct)
            .collect(toList());

        return productRepository.saveAll(products).stream()
            .map(ProductMapper.INSTANCE::toProductDto)
            .collect(toList());
    }

    public void cleanDb() {
        productRepository.deleteAll();
    }
}
