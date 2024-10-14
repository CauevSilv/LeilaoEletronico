package com.fatec.leilao.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LanceDTO {
    private Double valor;
    private ClienteDTO cliente;
    private LoteDTO lote;
}

