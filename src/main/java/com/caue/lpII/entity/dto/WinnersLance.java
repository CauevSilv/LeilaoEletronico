package com.caue.lpII.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WinnersLance {
    private LanceDTO lanceDto;
    private String descricao;
    private Double valor;
    private String clienteName;
    private String tipoProduto;
}
