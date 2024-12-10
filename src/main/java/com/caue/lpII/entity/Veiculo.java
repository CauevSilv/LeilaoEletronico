package com.caue.lpII.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "VEICULO")
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Column(name = "MODELO")
    private String modelo;

    @Size(max = 255)
    @Column(name = "MARCA")
    private String marca;

    @Size(max = 255)
    @Column(name = "TIPO")
    private String tipo;

}