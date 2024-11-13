package com.caue.lpII.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoteDTO {
    private Integer id;
    private String tipo; // "dispositivo" ou "veículo"
    private String nome;
    private String descricao;
    private Double lanceInicial;
    private LeilaoDTO leilao;

    public LoteDTO(String tipo, String nome, String descricao, Double lanceInicial, LeilaoDTO leilao) {
        this.tipo = tipo;
        this.nome = nome;
        this.descricao = descricao;
        this.lanceInicial = lanceInicial;
        this.leilao = leilao;
    }
}
