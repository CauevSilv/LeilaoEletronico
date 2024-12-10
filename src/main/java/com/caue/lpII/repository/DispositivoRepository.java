package com.caue.lpII.repository;

import com.caue.lpII.entity.Dispositivo;
import com.caue.lpII.entity.Leilao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Repository
public interface DispositivoRepository extends JpaRepository<Dispositivo, Long> {
    @Transactional
    @Modifying
    @Query("""
            update Dispositivo d set d.descricao = :descricao, d.valorInicial = :valorInicial, d.vendido = :vendido, d.loteId = :leilaoId, d.nome = :nome, d.tipo = :tipo
            where d.id = :id""")
    void updateDescricaoAndValorInicialAndVendidoAndLeilaoIdAndNomeAndTipoByIdAllIgnoreCase(@Param("descricao") String descricao, @Param("valorInicial") Double valorInicial, @Param("vendido") Boolean vendido, @Param("leilaoId") Integer leilaoId, @Param("nome") String nome, @Param("tipo") String tipo, @Param("id") Long id);
}