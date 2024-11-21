package com.caue.lpII.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.internal.bytebuddy.asm.Advice;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LEILAO")
public class Leilao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_LEILAO", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "DATA_OCORRENCIA", nullable = false)
    private LocalDate dataOcorrencia;

    @NotNull
    @Column(name = "DATA_VISITACAO", nullable = false)
    private LocalDate dataVisitacao;

    @Size(max = 255)
    @NotNull
    @Column(name = "LOCAL", nullable = false)
    private String local;

    @Size(max = 255)
    @Column(name = "ENDERECO")
    private String endereco;

    @Size(max = 100)
    @Column(name = "CIDADE", length = 100)
    private String cidade;

    @Size(max = 2)
    @Column(name = "ESTADO", length = 2)
    private String estado;

    @Size(max = 20)
    @NotNull
    @Column(name = "STATUS", nullable = false, length = 20)
    private String status;

}