package com.jaarquesuoc.shop.products.mappers;

import com.jaarquesuoc.shop.products.dtos.ProductDto;
import com.jaarquesuoc.shop.products.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto toProductDto(Product product);

    Product toProduct(ProductDto productDto);
}
