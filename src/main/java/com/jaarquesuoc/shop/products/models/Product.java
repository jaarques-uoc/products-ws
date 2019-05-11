package com.jaarquesuoc.shop.products.models;

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
