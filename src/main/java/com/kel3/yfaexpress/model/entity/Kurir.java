package com.kel3.yfaexpress.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = Kurir.TABLEKURIR)
public class Kurir {
    public static final String TABLEKURIR = "t_kurir";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator=TABLEKURIR)
    @SequenceGenerator(name = TABLEKURIR, sequenceName = "t_kurir_seq")

    private Integer idKurir;
    private String namaKurir;
    private String noTelpKurir;
    private String file;

}
