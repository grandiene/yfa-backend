package com.kel3.yfaexpress.model.dto;

import lombok.Data;

import java.util.List;

@Data
public class CostDto {
    private String service;
    private String description;
    private List<OngkirDto> cost;
}
