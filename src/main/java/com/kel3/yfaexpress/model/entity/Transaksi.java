package com.kel3.yfaexpress.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = Transaksi.TABLE_BARANG)
@Data
public class Transaksi {
    public static final String TABLE_BARANG = "t_transaksi";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator=TABLE_BARANG)
    @SequenceGenerator(name = TABLE_BARANG, sequenceName = "t_transaksi_seq")
    private Integer idTransaksi;
    private String namaBarang;
    private Integer jumlahBarang;
    private String kategoriLayanan;
    private Integer ongkosKirim;
    private String estimasi;
    private Integer beratBarang;
    private String statusDelivery;
    private String tanggalTransaksi;
    private String resi;
    private String penerimaPaket;
    private String fotoPenerima;
    private String tanggalSampai;
    private Integer isDelete;

    @OneToOne
    @JoinColumn (name = "id_pengirim")
    private Pengirim pengirim;

    @OneToOne
    @JoinColumn (name = "id_penerima")
    private Penerima penerima;

    @ManyToOne
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    private Useraa useraa;
    @Column(name = "id_user")
    private Long idUser;

    @ManyToOne
    @JoinColumn(name = "id_kurir", insertable = false, updatable = false)
    private Kurir kurir;
    @Column(name = "id_kurir")
    private Integer idKurir;

}