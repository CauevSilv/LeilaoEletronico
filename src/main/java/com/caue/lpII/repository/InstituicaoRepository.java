package com.caue.lpII.repository;

import com.caue.lpII.entity.Instituicao;
import com.caue.lpII.entity.Lote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstituicaoRepository extends JpaRepository<Instituicao, Integer> {

    Optional<Instituicao> findByCnpj(String cnpj);

    @Query(value = "select INSTITUICAOFINANCEIRA.* from INSTITUICAOFINANCEIRA join LEILAO_INSTITUICAO on INSTITUICAOFINANCEIRA.ID_INSTITUICAO =  LEILAO_INSTITUICAO.ID_INSTITUICAO where ID_LEILAO = :leilaoId", nativeQuery = true)
    List<Instituicao> findAllByLeilaoId(@Param("leilaoId")Integer leilaoId);
}
