package com.caue.lpII.entity.dto;

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
    private ClienteDTO cliente; // Referência ao cliente que fez o lance
    private LoteDTO lote; // Referência ao lote no qual o lance foi feito
}

