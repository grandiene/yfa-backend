package com.kel3.yfaexpress.model.entity;

import lombok.Data;

import javax.persistence.*;

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

    public Integer getIdTransaksi() {
        return idTransaksi;
    }

    public void setIdTransaksi(Integer idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public Integer getJumlahBarang() {
        return jumlahBarang;
    }

    public void setJumlahBarang(Integer jumlahBarang) {
        this.jumlahBarang = jumlahBarang;
    }

    public String getKategoriLayanan() {
        return kategoriLayanan;
    }

    public void setKategoriLayanan(String kategoriLayanan) {
        this.kategoriLayanan = kategoriLayanan;
    }

    public Integer getOngkosKirim() {
        return ongkosKirim;
    }

    public void setOngkosKirim(Integer ongkosKirim) {
        this.ongkosKirim = ongkosKirim;
    }

    public String getEstimasi() {
        return estimasi;
    }

    public void setEstimasi(String estimasi) {
        this.estimasi = estimasi;
    }

    public Integer getBeratBarang() {
        return beratBarang;
    }

    public void setBeratBarang(Integer beratBarang) {
        this.beratBarang = beratBarang;
    }

    public String getStatusDelivery() {
        return statusDelivery;
    }

    public void setStatusDelivery(String statusDelivery) {
        this.statusDelivery = statusDelivery;
    }

    public String getTanggalTransaksi() {
        return tanggalTransaksi;
    }

    public void setTanggalTransaksi(String tanggalTransaksi) {
        this.tanggalTransaksi = tanggalTransaksi;
    }

    public String getResi() {
        return resi;
    }

    public void setResi(String resi) {
        this.resi = resi;
    }

    public String getPenerimaPaket() {
        return penerimaPaket;
    }

    public void setPenerimaPaket(String penerimaPaket) {
        this.penerimaPaket = penerimaPaket;
    }

    public String getFotoPenerima() {
        return fotoPenerima;
    }

    public void setFotoPenerima(String fotoPenerima) {
        this.fotoPenerima = fotoPenerima;
    }

    public String getTanggalSampai() {
        return tanggalSampai;
    }

    public void setTanggalSampai(String tanggalSampai) {
        this.tanggalSampai = tanggalSampai;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public void setPengirim(Pengirim pengirim) {
        this.pengirim = pengirim;
    }

    public void setPenerima(Penerima penerima) {
        this.penerima = penerima;
    }

    public Useraa getUseraa() {
        return useraa;
    }

    public void setUseraa(Useraa useraa) {
        this.useraa = useraa;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public void setKurir(Kurir kurir) {
        this.kurir = kurir;
    }

    public Integer getIdKurir() {
        return idKurir;
    }

    public void setIdKurir(Integer idKurir) {
        this.idKurir = idKurir;
    }

    private String penerimaPaket;

    public Pengirim getPengirim() {
        return pengirim;
    }

    public Penerima getPenerima() {
        return penerima;
    }

    public Kurir getKurir() {
        return kurir;
    }

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