package com.caue.lpII.repository;

import com.caue.lpII.entity.LoteTipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoteTipoRepository extends JpaRepository<LoteTipo, Integer> {
}