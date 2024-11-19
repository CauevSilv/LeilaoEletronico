package com.caue.lpII.entity.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LeilaoDetalhadoDto {
    private int id;
    private LocalDate dataOcorrencia;
    private LocalDate dataVisita;
    private String endereco;
    private String cidade;
    private String estado;
    private List<LoteDTO> produtos;
    private List<InstituicaoDTO> instituicoesFinanceiras;
    private int totalProdutos;
}
