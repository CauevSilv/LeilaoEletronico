package com.caue.lpII.repository;

import com.caue.lpII.entity.Leilao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeilaoRepository extends JpaRepository<Leilao, Integer> {

    List<Leilao> findAllByOrderByDataOcorrenciaAsc();

}
