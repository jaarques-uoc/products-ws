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
        List<Product> products = productsRepository.findAll();

        return ProductsMapper.INSTANCE.toProductDtos(products);
    }

    public List<ProductDto> getAllProductDtosById(final List<String> ids) {
        Iterable<Product> productsIterable = productsRepository.findAllById(ids);

        List<Product> products = StreamSupport.stream(productsIterable.spliterator(), false)
            .collect(toList());

        return ProductsMapper.INSTANCE.toProductDtos(products);
    }

    public Optional<ProductDto> getProductDto(final String id) {
        return productsRepository.findById(id)
            .map(ProductsMapper.INSTANCE::toProductDto);
    }

    public ProductDto upsertProductDto(final ProductDto productDto) {
        Product product = ProductsMapper.INSTANCE.toProduct(productDto);
        return ProductsMapper.INSTANCE.toProductDto(productsRepository.save(product));
    }

    public List<ProductDto> upsertProductDtos(final List<ProductDto> productDtos) {
        List<Product> products = ProductsMapper.INSTANCE.toProducts(productDtos);

        List<Product> upsertedProducts = productsRepository.saveAll(products);

        return ProductsMapper.INSTANCE.toProductDtos(upsertedProducts);
    }

    public void cleanDb() {
        productsRepository.deleteAll();
    }
}
