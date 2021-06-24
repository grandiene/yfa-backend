package com.kel3.yfaexpress.model.dto;

import lombok.Data;

@Data
public class PengirimDto {
    private Integer idPengirim;
    private String namaPengirim;
    private String telpPengirim;
    private Integer province_id;
    private Integer city_id;
    private String provinceName;
    private String cityName;
    private String alamatPengirim;
    private String kodePosPengirim;
}
