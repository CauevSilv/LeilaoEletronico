package com.caue.lpII.repository;

import com.caue.lpII.entity.Leilao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeilaoRepository extends JpaRepository<Leilao, Integer> {

    // Listar leilões ordenados por data de ocorrência
    List<Leilao> findAllByOrderByDataOcorrenciaAsc();

    // Outros métodos padrão do JpaRepository já incluem save(), delete(), findById(), etc.
}
