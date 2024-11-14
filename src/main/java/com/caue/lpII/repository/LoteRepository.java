package com.caue.lpII.repository;

import com.caue.lpII.entity.Lote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Integer> {

    List<Lote> findByTipo(String tipo);

    List<Lote> findByLanceInicialBetween(Double min, Double max);

    List<Lote> findByNomeContainingIgnoreCase(String keyword);

}

