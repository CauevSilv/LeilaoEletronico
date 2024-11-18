package com.caue.lpII.entity;


import com.caue.lpII.entity.dto.LoteDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Lote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String tipo;
    private String nome;
    private String descricao;
    private Double lanceInicial;
    @ManyToOne(targetEntity = Leilao.class)
    private Leilao leilao;

    public Lote(String tipo, String nome, String descricao, Double lanceInicial, Leilao leilao) {
    this.tipo = tipo;
    this.nome = nome;
    this.descricao = descricao;
    this.lanceInicial = lanceInicial;
    this.leilao = leilao;
    }
}
