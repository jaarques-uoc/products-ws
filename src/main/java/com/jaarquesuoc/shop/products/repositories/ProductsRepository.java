package com.jaarquesuoc.shop.products.repositories;

import com.jaarquesuoc.shop.products.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductsRepository extends MongoRepository<Product, String> {

}
