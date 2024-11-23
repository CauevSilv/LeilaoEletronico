package com.caue.lpII.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LeilaoDETDTO {
    private Long id;
    private String endereco;
    private String cidade;
    private String estado;
    private String status;
    private List<LoteDTO> lotes;
    private List<InstituicaoDTO> instituicoesFinanceiras;
    private List<LanceDTO> lancesHistorico;
}
