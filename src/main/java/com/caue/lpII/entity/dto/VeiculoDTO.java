package com.caue.lpII.entity.dto;

import com.caue.lpII.entity.enums.TiposVeiculos;
import lombok.Data;

@Data
public class VeiculoDTO {
    private Long id;
    private String descricao;
    private Double valorInicial;
    private boolean vendido;
    private String modelo;
    private String marca;
    private TiposVeiculos tipo;
}
