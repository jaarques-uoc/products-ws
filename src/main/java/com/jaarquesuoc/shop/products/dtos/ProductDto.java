package com.jaarquesuoc.shop.products.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private String id;

    private String name;

    private String description;

    private BigDecimal price;

    private String imageUrl;
}
