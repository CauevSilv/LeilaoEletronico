package com.caue.lpII.repository;

import com.caue.lpII.entity.Lote;
import com.caue.lpII.entity.LoteValorTotal;
import com.caue.lpII.entity.dto.LoteValorTotalDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoteRepository extends JpaRepository<com.caue.lpII.entity.Lote, Integer> {

    @Query(value = "SELECT * FROM lote WHERE lance_inicial BETWEEN :min AND :max and ID_LEILAO = :leilaoId", nativeQuery = true)
    List<Lote> findByLanceInicialBetween(@Param("min")Double min,@Param("max") Double max, @Param("leilaoId")Integer leilaoId);


    @Query(value = "SELECT ID_LEILAO FROM LOTE WHERE ID_LOTE = :loteId GROUP BY ID_LEILAO",nativeQuery = true)
    Integer findLeilaoByLoteId(@Param("loteId") Long loteId);


    List<Lote> findByNomeContainingIgnoreCase(String palavra);


    @Query(value = "SELECT * from Lote l where l.ID_LEILAO = :leilaoId", nativeQuery = true)
    List<Lote> findAllByLeilaoId(@Param("leilaoId")Integer leilaoId);

    @Query(value = "SELECT * from Lote l where l.ID_LEILAO = :leilaoId and l.ID_LOTE = :loteId", nativeQuery = true)
    Lote findByLeilaoIdAndLoteId(@Param("leilaoId")Integer leilaoId,@Param("loteId")Integer loteId);

    @Query(value ="select ID_LOTE as id, NOME,DESCRICAO,LANCE_INICIAL,VALOR_TOTAL from LOTE_VALOR_TOTAL where ID_LOTE IN (SELECT LOTE.ID_LOTE FROM LOTE WHERE ID_LEILAO = :idLeilao) AND  VALOR_TOTAL BETWEEN :min and :max", nativeQuery = true)
    List<Object[]> findLotesByLancesTotaisBetween(@Param("min") Double min,
                                                           @Param("max") Double max,
                                                           @Param("idLeilao") Integer idLeilao);
}

