package com.caue.lpII.repository;

import com.caue.lpII.entity.Lance;
import com.caue.lpII.entity.LoteTipo;
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



    @Query(value = "SELECT * FROM Lance WHERE id_lote = :idLote AND valor = (SELECT MAX(valor) FROM Lance WHERE id_lote = :idLote)",nativeQuery = true)
    Lance findLanceByValorGreater(@Param("idLote") Integer idLote);

    @Query(value = """
    SELECT l.* 
    FROM LANCE l
    JOIN LOTE lo ON l.ID_LOTE = lo.ID_LOTE
    JOIN VEICULO v ON lo.ID_LOTE = v.LOTE_ID
    WHERE lo.ID_LEILAO = :idLeilao GROUP BY l.ID_LANCE
    """, nativeQuery = true)
    List<Lance> findByTipoContainingIgnoreCaseVeiculo(@Param("idLeilao") Long idLeilao);


    @Query(value = """
    SELECT l.* 
    FROM LANCE l
    JOIN LOTE lo ON l.ID_LOTE = lo.ID_LOTE
    JOIN DISPOSITIVO v ON lo.ID_LOTE = v.LOTE_ID
    WHERE lo.ID_LEILAO = :idLeilao GROUP BY l.ID_LANCE
    """, nativeQuery = true)
    List<Lance> findByTipoContainingIgnoreCaseDispositivo(@Param("idLeilao")Long idLeilao);
}
