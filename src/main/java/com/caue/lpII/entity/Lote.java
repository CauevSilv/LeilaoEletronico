package com.caue.lpII.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "LOTE")
public class Lote {
    @Id
    @Column(name = "ID_LOTE", nullable = false)
    private Integer id;

    @Column(name="NOME")
    private String nome;

    @Lob
    @Column(name = "DESCRICAO")
    private String descricao;

    @NotNull
    @Column(name = "LANCE_INICIAL", nullable = false, precision = 10, scale = 2)
    private BigDecimal lanceInicial;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ID_LEILAO", nullable = false)
    private Leilao idLeilao;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.RESTRICT)
    @JoinColumn(name = "ID_LOTE_TIPO")
    private LoteTipo idLoteTipo;

}