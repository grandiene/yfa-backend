package com.kel3.yfaexpress.repository;

import com.kel3.yfaexpress.model.entity.Useraa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Useraa, Long>{
	Useraa findByEmail(String email);
	Optional<Useraa> findByUsername(String username);
}
