package com.kel3.yfaexpress.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = Kurir.TABLEKURIR)
public class Kurir {
    public static final String TABLEKURIR = "kurirs";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator=TABLEKURIR)
    @SequenceGenerator(name = TABLEKURIR, sequenceName = "t_kurir_seq")
    private Integer idKurir;

    @Column(nullable = false)
    private String namaKurir;
    @Column(nullable = false)
    private String noTelpKurir;
    @Column(nullable = false)
    private String nik;
    @Column(nullable = false)
    private String ttl;
    @Column(nullable = false)
    private String alamat;
    @Column(nullable = false)
    private String file;

    private Integer isDelete;


}
