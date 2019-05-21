package com.jaarquesuoc.shop.products.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaarquesuoc.shop.products.dtos.InitialisationDto;
import com.jaarquesuoc.shop.products.dtos.ProductDto;
import com.jaarquesuoc.shop.products.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static com.jaarquesuoc.shop.products.dtos.InitialisationDto.InitialisationStatus.OK;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class InitialisationController {

    private final ProductService productService;

    @GetMapping("/init")
    public InitialisationDto initialiseDB() throws IOException {
        productService.cleanDb();

        return InitialisationDto.builder()
            .initialisationStatus(OK)
            .metadata(productService.upsertProducts(buildProducts()))
            .build();
    }

    private List<ProductDto> buildProducts() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        File initialFile = new File("products.json");
        InputStream targetStream = new FileInputStream(initialFile);

        return Arrays.asList(mapper.readValue(targetStream, ProductDto[].class));
    }
}
