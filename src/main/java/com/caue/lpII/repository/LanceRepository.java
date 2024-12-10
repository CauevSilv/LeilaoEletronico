package com.caue.lpII.repository;

import com.caue.lpII.entity.Lance;
import com.caue.lpII.entity.Lote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LanceRepository extends JpaRepository<Lance, Integer> {

    @Query(value = "SELECT * FROM LANCE WHERE ID_LOTE = :idLote ORDER BY ID_LOTE",nativeQuery = true)
    List<Lance> findByLoteId(@Param("idLote")int idLote);


    @Query(value = "SELECT * FROM LANCE WHERE ID_LOTE in (SELECT LOTE.ID_LOTE FROM LOTE WHERE ID_LEILAO = :leilaoId)", nativeQuery = true)
    List<Lance> findAllByLeilaoId(@Param("leilaoId")Integer leilaoId);

    @Query(value = "SELECT ID_LANCE FROM LANCE ORDER BY LANCE.ID_LANCE DESC LIMIT 1",nativeQuery = true)
    Long findLastId();

    @Query(value = "SELECT * FROM Lance WHERE id_lote = :idLote AND valor = (SELECT MAX(valor) FROM Lance WHERE id_lote = :idLote)",nativeQuery = true)
    Lance findLanceByValorGreater(@Param("idLote") Integer idLote);
}
