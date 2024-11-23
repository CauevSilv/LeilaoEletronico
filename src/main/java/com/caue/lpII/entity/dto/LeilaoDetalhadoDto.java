package com.caue.lpII.entity.dto;

import com.caue.lpII.entity.enums.LeilaoStatusTypes;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LeilaoDetalhadoDto {
    private int id;
    private LocalDateTime dataOcorrencia;
    private LocalDateTime dataVisita;
    private String endereco;
    private String cidade;
    private String estado;
    private List<LoteDTO> produtos;
    private List<InstituicaoDTO> instituicoesFinanceiras;
    private LeilaoStatusTypes status;
}
