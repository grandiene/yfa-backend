package com.kel3.yfaexpress.repository;

import com.kel3.yfaexpress.model.entity.Penerima;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PenerimaRepository extends JpaRepository<Penerima, Integer> {
    List<Penerima> findAllByIdPenerima(Integer idPenerima);
}
