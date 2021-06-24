package com.kel3.yfaexpress.model.dto;

import lombok.Data;

@Data
public class PenerimaDto {
    private Integer idPenerima;
    private String namaPenerima;
    private String telpPenerima;
    private Integer provinceIdPenerima;
    private Integer cityIdPenerima;
    private String provinceNamePenerima;
    private String cityNamePenerima;
    private String alamatPenerima;
    private String kodePosPenerima;
}
