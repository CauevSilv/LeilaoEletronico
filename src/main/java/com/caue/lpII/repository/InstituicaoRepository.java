package com.caue.lpII.repository;

import com.caue.lpII.entity.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InstituicaoRepository extends JpaRepository<Instituicao, Integer> {

    Optional<Instituicao> findByCnpj(String cnpj);

}
