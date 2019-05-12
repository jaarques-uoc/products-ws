package com.jaarquesuoc.shop.products.dtos;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Product {

    private String id;

    private String name;

    private String description;

    private BigDecimal price;

    private String imageUrl;
}
