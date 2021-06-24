package com.kel3.yfaexpress.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = Pengirim.TABLE_NAME)
public class Pengirim {
    public static final String TABLE_NAME = "t_pengirim";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = TABLE_NAME)
    @SequenceGenerator(name = TABLE_NAME , sequenceName = "t_pengirim_seq")
    private Integer idPengirim;
    private String namaPengirim;
    private String telpPengirim;

    @Column(name ="kota_pengirim", nullable = false)
    private String cityName;

    @Column(name ="provinsi_pengirim", nullable = false)
    private String provinceName;

    private String alamatPengirim;
    private String kodePosPengirim;
}
