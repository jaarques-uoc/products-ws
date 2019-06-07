package com.jaarquesuoc.shop.products.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaarquesuoc.shop.products.dtos.InitialisationDto;
import com.jaarquesuoc.shop.products.dtos.ProductDto;
import com.jaarquesuoc.shop.products.services.ProductsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static com.jaarquesuoc.shop.products.dtos.InitialisationDto.InitialisationStatus.OK;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InitialisationController {

    private final ProductsService productsService;

    @GetMapping("/init")
    public InitialisationDto initialiseDB() throws IOException {
        productsService.cleanDb();

        return InitialisationDto.builder()
            .initialisationStatus(OK)
            .metadata(productsService.upsertProductDtos(buildProducts()))
            .build();
    }

    private List<ProductDto> buildProducts() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        InputStream targetStream = new ClassPathResource("products.json").getInputStream();

        return Arrays.asList(mapper.readValue(targetStream, ProductDto[].class));
    }
}
