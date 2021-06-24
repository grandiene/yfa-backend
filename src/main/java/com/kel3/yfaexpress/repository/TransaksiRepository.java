package com.kel3.yfaexpress.repository;

import com.kel3.yfaexpress.model.entity.Transaksi;
import com.kel3.yfaexpress.model.entity.Useraa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransaksiRepository extends JpaRepository<Transaksi, Integer> {
    List<Transaksi> findAllByStatusDeliveryIsNotInAndIsDeleteEquals(List<String> status, Integer isDelete);
    List<Transaksi> findAllByUseraa_Email(String email);
    Transaksi findByResiEquals(String resi);
    Transaksi findByIdTransaksi(Integer id);
}
