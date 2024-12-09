package com.caue.lpII.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DISPOSITIVO")
public class Dispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "VALOR_INICIAL", precision = 15, scale = 2)
    private BigDecimal valorInicial;

    @Column(name = "VENDIDO")
    private Boolean vendido;

    @Column(name = "LEILAO_ID")
    private Long leilaoId;

    @Size(max = 255)
    @Column(name = "NOME")
    private String nome;

    @Size(max = 255)
    @Column(name = "TIPO")
    private String tipo;

}