package com.caue.lpII.repository;

import com.caue.lpII.entity.Lote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Integer> {

    // Buscar por tipo de lote (dispositivo ou veículo)
    List<Lote> findByTipo(String tipo);

    // Buscar lotes em uma faixa de lance inicial
    List<Lote> findByLanceInicialBetween(Double min, Double max);

    // Buscar lotes por palavra-chave no nome
    List<Lote> findByNomeContainingIgnoreCase(String keyword);

    // Outros métodos padrão do JpaRepository já incluem save(), delete(), findById(), etc.
}

