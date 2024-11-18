package com.caue.lpII.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Optional;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeilaoDTO {
    private Optional<Integer> idLeilao;
    private LocalDate dataOcorrencia;
    private LocalDate dataVisitacao;
    private String local;
    private String endereco;
    private String cidade;
    private String estado;
    private String status;
}


