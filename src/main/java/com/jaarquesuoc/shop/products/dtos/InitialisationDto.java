package com.jaarquesuoc.shop.products.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InitialisationDto {

    public enum InitialisationStatus {OK}

    private InitialisationStatus initialisationStatus;

    private Object metadata;
}
