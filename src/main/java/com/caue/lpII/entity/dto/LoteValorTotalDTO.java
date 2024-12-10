package com.caue.lpII.entity.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoteValorTotalDTO {
    private Integer idLote;
    private String nome;
    private String descricao;
    private BigDecimal lanceInicial;
    private BigDecimal valorTotal;
}
