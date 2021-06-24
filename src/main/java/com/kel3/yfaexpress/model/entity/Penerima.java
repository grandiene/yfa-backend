package com.kel3.yfaexpress.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = Penerima.TABLEPENERIMA)
@Data
public class Penerima {
    public static final String TABLEPENERIMA = "t_penerima";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator=TABLEPENERIMA)
    @SequenceGenerator(name = TABLEPENERIMA, sequenceName = "t_penerima_seq")

    private Integer idPenerima;

    @Column(name ="nama_penerima",nullable = false)
    private String namaPenerima;

    @Column(name ="no_telp_penerima",nullable = false)
    private String telpPenerima;

    @Column(name ="kota_penerima", nullable = false)
    private String cityNamePenerima;

    @Column(name ="provinsi_penerima", nullable = false)
    private String provinceNamePenerima;

    @Column(name ="alamatpenerima",nullable = false)
    private String alamatPenerima;

    @Column(name ="kode_pos_penerima",nullable = false)
    private String kodePosPenerima;

}
