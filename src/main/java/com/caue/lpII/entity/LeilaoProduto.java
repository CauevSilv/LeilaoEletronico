package com.caue.lpII.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class LeilaoProduto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String descricao;

    @Column
    private Double valorInicial;

    @Column
    private boolean vendido;

    @ManyToOne
    private Leilao leilao;
}