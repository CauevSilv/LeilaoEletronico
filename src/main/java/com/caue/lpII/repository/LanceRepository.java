package com.caue.lpII.repository;

import com.caue.lpII.entity.Lance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanceRepository extends JpaRepository<Lance, Integer> {

    List<Lance> findByLoteId(int idLote);

    List<Lance> findByLoteIdOrderByValorDesc(int idLote);
}
