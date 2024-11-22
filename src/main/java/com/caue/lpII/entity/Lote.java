package com.caue.lpII.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LOTE")
public class Lote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LOTE", nullable = false)
    private Integer id;

    @Size(max = 50)
    @NotNull
    @Column(name = "TIPO", nullable = false, length = 50)
    private String tipo;

    @Size(max = 100)
    @NotNull
    @Column(name = "NOME", nullable = false, length = 100)
    private String nome;

    @Lob
    @Column(name = "DESCRICAO")
    private String descricao;

    @NotNull
    @Column(name = "LANCE_INICIAL", nullable = false)
    private Double lanceInicial;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ID_LEILAO", nullable = false)
    private Leilao idLeilao;

}