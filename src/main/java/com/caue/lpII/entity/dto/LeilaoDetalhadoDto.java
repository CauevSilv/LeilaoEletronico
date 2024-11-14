package com.caue.lpII.entity.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class LeilaoDetalhadoDto {
    private Long id;
    private LocalDateTime dataOcorrencia;
    private LocalDateTime dataVisita;
    private String endereco;
    private String cidade;
    private String estado;
    private List<DispositivoDTO> produtos;
    private List<VeiculoDTO> veiculos;
    private List<InstituicaoFinanceiraDTO> instituicoesFinanceiras;
    private int totalProdutos;
}
