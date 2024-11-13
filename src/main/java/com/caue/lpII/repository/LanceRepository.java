package com.caue.lpII.repository;

import com.caue.lpII.entity.Lance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanceRepository extends JpaRepository<Lance, Integer> {

    // Buscar lances de um determinado lote
    List<Lance> findByLoteId(int idLote);

    // Buscar lances de um lote ordenados pelo valor
    List<Lance> findByLoteIdOrderByValorDesc(int idLote);

    // Outros métodos padrão do JpaRepository já incluem save(), delete(), findById(), etc.
}
