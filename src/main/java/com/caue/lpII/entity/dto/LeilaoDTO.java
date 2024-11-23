package com.caue.lpII.entity.dto;

import com.caue.lpII.entity.enums.LeilaoStatusTypes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeilaoDTO {
    private Optional<Integer> idLeilao;
    private LocalDateTime dataOcorrencia;
    private LocalDateTime dataVisitacao;
    private String local;
    private String endereco;
    private String cidade;
    private String estado;
    private LeilaoStatusTypes status;
}


