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
    private String tipo; // "dispositivo" ou "veículo"
    private String nome;
    private String descricao;
    private Double lanceInicial;
    @ManyToOne(targetEntity = Leilao.class)
    private Leilao leilao; // Relação com a entidade Leilão

    public Lote(String tipo, String nome, String descricao, Double lanceInicial, Leilao leilao) {
    this.tipo = tipo;
    this.nome = nome;
    this.descricao = descricao;
    this.lanceInicial = lanceInicial;
    this.leilao = leilao;
    }

    public LoteDTO toDTO() {
        LoteDTO loteDTO = new LoteDTO();
        loteDTO.setTipo(this.tipo);
        loteDTO.setNome(this.nome);
        loteDTO.setDescricao(this.descricao);
        loteDTO.setLanceInicial(this.lanceInicial);
        loteDTO.setLeilao(this.leilao.toDTO());
        return loteDTO;
    }
}
