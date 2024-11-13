package com.caue.lpII.entity;

import com.caue.lpII.entity.dto.InstituicaoDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Instituicao{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInstituicao;
    private String nome;
    private String cnpj;

    public Instituicao(String nome, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;
    }

    public InstituicaoDTO toDTO() {
        InstituicaoDTO instituicaoDTO = new InstituicaoDTO();
        instituicaoDTO.setNome(this.nome);
        instituicaoDTO.setCnpj(this.cnpj);
        return instituicaoDTO;
    }
}
