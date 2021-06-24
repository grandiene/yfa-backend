package com.kel3.yfaexpress.repository;

import com.kel3.yfaexpress.model.entity.Kurir;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KurirRepository extends JpaRepository<Kurir, Integer> {
    List<Kurir> findAllByIdKurir(Integer idKurir);
}