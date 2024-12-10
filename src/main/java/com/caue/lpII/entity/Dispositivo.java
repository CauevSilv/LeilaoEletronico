package com.caue.lpII.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "DISPOSITIVO")
public class Dispositivo {
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;

    @Size(max = 255)
    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "VALOR_INICIAL", precision = 15, scale = 2)
    private BigDecimal valorInicial;

    @Column(name = "VENDIDO")
    private Boolean vendido;

    @JoinColumn(name = "LOTE_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    private Lote loteId;

    @Size(max = 255)
    @Column(name = "NOME")
    private String nome;

    @Size(max = 255)
    @Column(name = "TIPO")
    private String tipo;

}