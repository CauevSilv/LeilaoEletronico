package com.caue.lpII.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Immutable;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Immutable
@Table(name = "LOTE_VALOR_TOTAL")
public class LoteValorTotal {
    @Column(name = "ID_LOTE")
    @Id
    private Integer idLote;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "LANCE_INICIAL", precision = 10, scale = 2)
    private BigDecimal lanceInicial;

    @Column(name = "VALOR_TOTAL", precision = 21, scale = 2)
    private BigDecimal valorTotal;

}