package com.caue.lpII.repository;

import com.caue.lpII.entity.Lote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoteRepository extends JpaRepository<Lote, Integer> {

    List<Lote> findByTipo(String tipo);

    @Query(value = "SELECT * FROM lote WHERE lance_inicial BETWEEN :min AND :max and ID_LEILAO = :leilaoId", nativeQuery = true)
    List<Lote> findByLanceInicialBetween(@Param("min")Double min,@Param("max") Double max, @Param("leilaoId")Integer leilaoId);

    @Query(value ="SELECT l.id_lote, l.nome, l.descricao, l.lance_inicial + COALESCE(SUM(c.valor), 0) AS valor_total FROM lote l LEFT JOIN lance c ON l.id_lote = c.id_lote WHERE l.id_leilao = :idLeilao GROUP BY l.id_lote HAVING valor_total BETWEEN :min AND :max;",nativeQuery = true)
    List<Lote> findLotesByLancesTotaisBetween(@Param("min") Double min,
                                              @Param("max") Double max,
                                              @Param("idLeilao") Integer idLeilao);


    List<Lote> findByNomeContainingIgnoreCase(String keyword);

    @Query(value = "SELECT * from Lote l where l.ID_LEILAO = :leilaoId", nativeQuery = true)
    List<Lote> findAllByLeilaoId(@Param("leilaoId")Integer leilaoId);

    @Query(value = "SELECT * from Lote l where l.ID_LEILAO = :leilaoId and l.ID_LOTE = :loteId", nativeQuery = true)
    Lote findByLeilaoIdAndLoteId(@Param("leilaoId")Integer leilaoId,@Param("loteId")Integer loteId);

}

